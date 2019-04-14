//基础配置
var objId = "planId";//主键
var addTitle = "新增论文计划";//新增标题
var editTitle = "修改论文计划";//修改标题
var dailogWidth = "100%";//模态框宽度
var dailogHeight = "100%";//模态框高度
var crudUrl = "/app/pmsPlan";//增刪改查請求地址
var pageQueryUrl = "/app/pmsPlans";//分页请求地址
var addContentUrl = "/content/dist/views/app/pmsPlan/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/pmsPlan/edit.html";//修改页面请求地址
//扩展属性
var allGroupUrl = "/app/pmsGroupList";//所有组织
var crudGroupUrl = "/app/pmsGroup";//所有组织
var packDocUrl = "/app/pmsPaper/packDoc/";

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'planName', title: '计划名称', align: 'center',fixed: 'left',minWidth:'300'}
      ,{field:'groupName', title: '组织名称', align: 'center'}
      ,{field:'nickname', title: '计划创建人', align: 'center'}
      ,{field:'planType', title: '计划类型', align: 'center',templet: '#planType'}
      ,{field:'submitDateStr', title: '开始时间', align: 'center'}
      ,{field:'endDateStr', title: '结束时间', align: 'center'}
      ,{field:'planCount', title: '计划人数', align: 'center'}
      ,{field:'realityCount', title: '实际完成人数', align: 'center'}
      ,{field:'flag', title: '计划状态', align: 'center',templet: '#checkFlag'}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
      ,{field:'description', title: '描述', align: 'center'}
    ]];

layui.use(['element', 'layer','table','form','laydate'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var loadIcon;
    var laydate = layui.laydate;
    //日期范围
    //日期只读
    /*laydate.render({
        elem: '#date'
        ,trigger: 'click'
        ,range: true
    });*/
    //限定可选日期
    var now = getDatesStr(new Date().getTime());
    console.log(now);
    var ins22 = laydate.render({
        elem: '#date'
        ,min: now
        ,range: true
        ,ready: function(){
            ins22.hint('日期可选值设定在 <br> '+now+' 以后');
        },done: function(value, date, endDate){
            console.log(value); //得到日期生成的值，如：2017-08-18
            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
            $("#submitDate").val(layuiDate2Str(date));
            $("#endDate").val(layuiDate2Str(endDate));
        }
    });

    form.on('radio(planType)', function(data){
        console.log(data.elem); //得到radio原始DOM对象
        console.log(data.value); //被点击的radio的value值
        var type = data.value;
        var planName = $("#groupName").val()+"-";
        if ("1" === type) {
            planName+= "开题报告提交计划";
        }
        if ("2" === type) {
            planName+= "论文初稿提交计划";
        }
        if ("3" === type) {
            planName+= "论文终稿提交计划";
        }
        $("#planName").val(planName);
    });

    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };

    var tree;
    var selectNodes;
    var selectGroupId;

    loadTree();

    function loadTree() {
        $.ajax({
            type:"GET",
            url:allGroupUrl,
            success:function(resData) {
                if (resData.status === 200) {
                    var res = resData.data;
                    console.log(res);
                    console.log("tree:"+tree);
                    tree = $.fn.zTree.init($("#groupTree"), setting, res);
                    console.log(tree);

                    selectNodes=tree.getSelectedNodes();

                    if (selectNodes.length == 1) {
                        console.log(selectNodes[0]);
                        selectGroupId = selectNodes[0].id;
                    }

                    //分页
                    table.render({
                        elem: '#objTable'
                        ,page:true
                        ,where: {
                            groupId: (undefined != selectGroupId && 'undefined' != selectGroupId)?selectGroupId:null
                        }
                        ,height:400
                        ,url:pageQueryUrl
                        ,request: {
                            pageName: 'currPage' //页码的参数名称，默认：page
                            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        }
                        ,response: {
                            countName: 'totalCount' //数据总数的字段名称，默认：count
                            ,statusCode: 200
                            ,dataName: 'list' //数据列表的字段名称，默认：data
                        }
                        ,cellMinWidth: 120 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                        ,cols: pageField
                        ,id : 'objReload'
                        ,done: function(res, curr, count){
                            //关闭加载图标
                            layer.close(loadIcon);
                            //重新渲染元素
                            element.init();
                        }
                    });
                } else {
                    //layer.msg(resData.msg, {icon: 5});
                }
            }
        });
    }


    function onClick(event, treeId, treeNode, clickFlag) {
        console.log(event);
        console.log(treeId);
        console.log(treeNode);
        console.log(clickFlag);
        loadIcon = layer.load(2, {time: 10*1000});//加载中
        var keyWord = $('#keyWord');

        selectNodes=tree.getSelectedNodes();

        if (selectNodes.length == 1) {
            console.log(selectNodes[0]);
            selectGroupId = selectNodes[0].id;
        }
        //执行重载
        table.reload('objReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                groupId: (undefined != selectGroupId && 'undefined' != selectGroupId)?selectGroupId:null,
                keyWords: keyWord.val()
            }
        });
    }

    //关闭iframe层
    $(".openWindow").on("click",function(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });

    //搜索
    $("#searchBtn").on("click",function(){
        loadIcon = layer.load(2, {time: 10*1000});//加载中
        var keyWord = $('#keyWord');
        //执行重载
        table.reload('objReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                keyWords: keyWord.val()
            }
        });
    });

    //新增
    $("#addBtn").on("click",function(){
        //var treeObj=$.fn.zTree.getZTreeObj("groupTree");
        console.log(tree);
        var nodes=tree.getSelectedNodes();
        console.log(nodes);
        if (nodes.length != 1) {
            layer.msg('只能在一个部门下添加计划', {icon: 5});
        } else {
            console.log(nodes[0]);
            if (nodes[0] != undefined) {
                var groupId = nodes[0].id;
                var pId = nodes[0].pId;
                var groupName = nodes[0].name;
            }
            layer.open({
                id:"addObj",
                type: 2,
                title: addTitle,
                shadeClose: false,
                shade: [0.3, '#000'],
                maxmin: true, //开启最大化最小化按钮
                area: [dailogWidth, dailogHeight],
                content: addContentUrl+"?groupId="+groupId+"&pId="+pId+"&groupName="+escape(groupName),
                end : function() {
                    //执行重载
                    table.reload('objReload');
                    loadTree();
                    loadIcon = layer.load(2, {time: 10*1000});//加载中
                }
            });
        }

    });

    //监听保存
    form.on('submit(saveObj)', function (data) {
        $.ajax({
            type:"POST",
            url:crudUrl,
            data:$("#addForm").serialize(),
            success:function(resData) {
                if (resData.status === 200) {
                    layer.msg(resData.msg, {icon: 1,time:1200},function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else {
                    layer.msg(resData.msg, {icon: 5});
                }
            }
        });
        return false;//注释掉这行代码后，表单将会以普通方式提交表单，否则以ajax方式提交表单
    });
    //监听更新
    form.on('submit(updateObj)', function (data) {
        $.ajax({
            type:"PUT",
            url:crudUrl,
            data:$("#editForm").serialize(),
            success:function(resData) {
                if (resData.status === 200) {
                    layer.msg(resData.msg, {icon: 1,time:1200},function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else {
                    layer.msg(resData.msg, {icon: 5});
                }
            }
        });
        return false;//注释掉这行代码后，表单将会以普通方式提交表单，否则以ajax方式提交表单
    });

    //修改
    $("#editBtn").on("click",function(){
        var checkStatus = table.checkStatus('objReload')
            ,data = checkStatus.data;
        if (data.length == 1) {
            var editObjId;
            layui.each(data, function(index, obj) {
                for (property in obj)  {
                    if (objId == property) {
                        var dataValue = obj[property];
                        editObjId = dataValue;
                        return false;
                    }
                }
            });
            layer.open({
                id:"editObj",
                type: 2,
                title: editTitle,
                shadeClose: false,
                shade: [0.3, '#000'],
                maxmin: true, //开启最大化最小化按钮
                area: [dailogWidth, dailogHeight],
                content: editContentUrl+"?editObjId="+editObjId,
                success : function() {

                },
                end : function() {
                    //执行重载
                    table.reload('objReload');
                    loadIcon = layer.load(2, {time: 10*1000});//加载中
                }
            });
        } else {
            layer.msg('请选择一条记录进行修改', {icon: 5});
        }
    });
    //删除
    $("#deleteBatchBtn").on("click",function(){
        var checkStatus = table.checkStatus('objReload')
            ,data = checkStatus.data;
        if (data.length > 0) {
            var array = new Array();
            layui.each(data, function(index, obj) {
                for (property in obj)  {
                    if (objId == property) {
                        var dataValue = obj[property];
                        array.push(dataValue);
                        return false;
                    }
                }
            });
            var objIds = array.join(",");
            layer.confirm('确认要删除吗？', {
                skin: 'layui-layer-molv',
                btn: ['确认','取消'] //按钮
            }, function(){
                //layer.msg('删除：'+ objIds);
                $.ajax({
                    type:"DELETE",
                    url:crudUrl+"/"+objIds,
                    success:function(resData) {
                        if (resData.status === 200) {
                            layer.msg(resData.msg, {icon: 1});
                        } else {
                            layer.msg(resData.msg, {icon: 5});
                        }
                        //执行重载
                        table.reload('objReload');
                    }
                });
            });
        } else {
            layer.msg('至少选择一条记录', {icon: 5});
        }
    });
    //打包
    $("#packDocBtn").on("click",function(){
        var checkStatus = table.checkStatus('objReload')
            ,data = checkStatus.data;
        if (data.length == 1) {
            var planId = data[0].planId;
            var planName = data[0].planName;
            var index = layer.confirm('确认要对'+planName+'下的文档进行打包吗？', {
                skin: 'layui-layer-molv',
                btn: ['确认','取消'] //按钮
            }, function(){
                location.href = packDocUrl+"/"+planId;
                layer.close(index);
                /*$.ajax({
                    type:"GET",
                    url:packDocUrl+"/"+planId,
                    success:function(resData) {
                        if (resData.status === 200) {
                            layer.msg(resData.msg, {icon: 1});
                        } else {
                            layer.msg(resData.msg, {icon: 5});
                        }
                    }
                });*/
            });
        } else {
            layer.msg('请选择一条计划进行打包', {icon: 5});
        }
    });
    //刷新
    $("#refreshBtn").on("click",function(){
        location.href = location.href;
    });

});