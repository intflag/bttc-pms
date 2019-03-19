//基础配置
var objId = "groupId";//主键
var addTitle = "新增机构";//新增标题
var editTitle = "修改机构";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/app/pmsGroup";//增刪改查請求地址
var pageQueryUrl = "/app/pmsGroups";//分页请求地址
var addContentUrl = "/content/dist/views/app/pmsGroup/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/pmsGroup/edit.html";//修改页面请求地址

var userImportTemplateUrl = "/app/pmsGroup/userImportTemplate/";//用户导入模板
//扩展属性

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'groupName', title: '组织名称', align: 'center'}
      ,{field:'pid', title: '父ID', align: 'center'}
      ,{field:'flag', title: '标记', align: 'center'}
      ,{field:'description', title: '描述', align: 'center'}
    ]];

layui.use(['element', 'layer','table','form'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var loadIcon;
    //loadIcon = layer.load(2, {time: 10*1000});//加载中

    //分页
    table.render({
        elem: '#objTable'
        ,page:true
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
        if (nodes.length > 1) {
            layer.msg('只能在一个部门下添加子部门', {icon: 5});
        } else {
            console.log(nodes[0]);
            if (nodes[0] != undefined) {
                var parentId = nodes[0].id;
                var parentName = nodes[0].name;
            }
            layer.open({
                id:"addObj",
                type: 2,
                title: addTitle,
                shadeClose: false,
                shade: [0.3, '#000'],
                maxmin: true, //开启最大化最小化按钮
                area: [dailogWidth, dailogHeight],
                content: addContentUrl+"?parentId="+parentId+"&parentName="+escape(parentName),
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
        var nodes=tree.getSelectedNodes();
        if (nodes.length == 1) {
            var editObjId = nodes[0].id;
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
                    loadTree();
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
        var nodes=tree.getSelectedNodes();
        if (nodes.length > 0) {
            var array = new Array();
            for (var i = 0; i < nodes.length; i++) {
                array.push(nodes[i].id);
            }
            /*layui.each(data, function(index, obj) {
                for (property in obj)  {
                    if (objId == property) {
                        var dataValue = obj[property];
                        array.push(dataValue);
                        return false;
                    }
                }
            });*/
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
                        loadTree();
                    }
                });
            });
        } else {
            layer.msg('至少选择一条记录', {icon: 5});
        }
    });
    //刷新
    $("#refreshBtn").on("click",function(){
        location.href = location.href;
    });
    //下载导出模板
    $("#downloadBtn").on("click",function(){
        //var treeObj=$.fn.zTree.getZTreeObj("groupTree");
        var index = layer.confirm('确认要下载导入模板吗？', {
            skin: 'layui-layer-molv',
            btn: ['确认','取消'] //按钮
        }, function(){
            window.location.href=userImportTemplateUrl;
            layer.close(index);
        });

    });
});

/*
layui.use(['element', 'layer', 'table', 'form'], function() {
    var element = layui.element;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;

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

    $.ajax({
        type:"GET",
        url:allGroupUrl,
        success:function(resData) {
            if (resData.status === 200) {
                var res = resData.data;
                console.log(res);
                $.fn.zTree.init($("#groupTree"), setting, res);
            } else {
                //layer.msg(resData.msg, {icon: 5});
            }
        }
    });


    function onClick(event, treeId, treeNode, clickFlag) {
        console.log(event);
        console.log(treeId);
        console.log(treeNode);
        console.log(clickFlag);
    }

});*/
