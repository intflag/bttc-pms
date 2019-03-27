//基础配置
var objId = "groupId";//主键
var objUserId = "userId";//主键
var addTitle = "新增机构";//新增标题
var addUsersTitle = "新增用户";//新增标题
var addUserTitle = "导入用户信息";//新增标题
var editTitle = "修改机构";//修改标题
var editUserTitle = "修改用户";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/app/pmsGroup";//增刪改查請求地址
var crudUserUrl = "/admin/sysUser/user";//增刪改查請求地址
var pageQueryUrl = "/admin/sysUsers/group";//分页请求地址
var addContentUrl = "/content/dist/views/app/pmsGroup/add.html";//新增页面请求地址
var addUserContentUrl = "/content/dist/views/app/pmsGroup/addUsers.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/pmsGroup/edit.html";//修改页面请求地址
var editUserContentUrl = "/content/dist/views/app/pmsGroup/editUser.html";//修改页面请求地址
var importUserInfoUrl = "/content/dist/views/app/pmsGroup/addUser.html";//导入用户请求地址
var saveImportUserUrl = "/admin/sysUser/importUser";//导入用户保存

var userImportTemplateUrl = "/app/pmsGroup/userImportTemplate/";//用户导入模板
//扩展属性
var allGroupUrl = "/app/pmsGroupList";//所有组织

//分页属性
/*var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'groupName', title: '组织名称', align: 'center'}
      ,{field:'pid', title: '父ID', align: 'center'}
      ,{field:'flag', title: '标记', align: 'center'}
      ,{field:'description', title: '描述', align: 'center'}
    ]];*/
var pageField = [[
    {type:'checkbox', fixed: 'left'}
    ,{field:'username', title: '登录名',fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
    ,{field:'nickname', title: '昵称',fixed: 'left', align: 'center'}
    ,{field:'gender', title: '性别',templet: '#checkGender', unresize: true, align: 'center', sort: true}
    ,{field:'email', title: '邮箱', align: 'center'} //单元格内容水平居中
    ,{field:'telephone', title: '电话', align: 'center'} //单元格内容水平居中
    ,{field:'wechatId', title: '微信号', align: 'center'} //单元格内容水平居中
    ,{field:'qqId', title: 'QQ号', align: 'center'} //单元格内容水平居中
    ,{field:'groupName', title: '所在组织', minWidth: 120, align: 'center'} //单元格内容水平居中
    ,{field:'flag', title: '状态', align: 'center',templet: '#checkFlag', unresize: true, sort: true}
    ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
    ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
]];

layui.use(['element', 'layer','table','form'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var loadIcon;
    //loadIcon = layer.load(2, {time: 10*1000});//加载中

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
                groupId: (undefined != selectGroupId && 'undefined' != selectGroupId)?selectGroupId:null,
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

    //新增用户
    $("#addUserBtn").on("click",function(){
        //var treeObj=$.fn.zTree.getZTreeObj("groupTree");
        console.log(tree);
        var nodes=tree.getSelectedNodes();
        console.log(nodes);
        if (nodes.length != 1) {
            layer.msg('只能在一个部门下添加用户', {icon: 5});
        } else {
            console.log(nodes[0]);
            if (nodes[0] != undefined) {
                var groupId = nodes[0].id;
                var groupName = nodes[0].name;
                var pId = nodes[0].pId;
            }
            layer.open({
                id:"addUserObj",
                type: 2,
                title: addUsersTitle,
                shadeClose: false,
                shade: [0.3, '#000'],
                maxmin: true, //开启最大化最小化按钮
                area: [dailogWidth, dailogHeight],
                content: addUserContentUrl+"?groupId="+groupId+"&pId="+pId+"&groupName="+escape(groupName),
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
    //监听保存用户
    form.on('submit(saveUsersObj)', function (data) {
        $.ajax({
            type:"POST",
            url:crudUserUrl,
            data:$("#addUsersForm").serialize(),
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
    //监听更新用户
    form.on('submit(updateUserObj)', function (data) {
        $.ajax({
            type:"PUT",
            url:crudUserUrl,
            data:$("#editUserForm").serialize(),
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
    //修改
    $("#editUserBtn").on("click",function(){
        var checkStatus = table.checkStatus('objReload')
            ,data = checkStatus.data;
        if (data.length == 1) {
            var editObjId;
            layui.each(data, function(index, obj) {
                for (property in obj)  {
                    if (objUserId == property) {
                        var dataValue = obj[property];
                        editObjId = dataValue;
                        return false;
                    }
                }
            });
            layer.open({
                id:"editUserObj",
                type: 2,
                title: editUserTitle,
                shadeClose: false,
                shade: [0.3, '#000'],
                maxmin: true, //开启最大化最小化按钮
                area: [dailogWidth, dailogHeight],
                content: editUserContentUrl+"?editObjId="+editObjId,
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
    //删除
    //删除
    $("#deleteBatchUserBtn").on("click",function(){
        var checkStatus = table.checkStatus('objReload')
            ,data = checkStatus.data;
        if (data.length > 0) {
            var array = new Array();
            layui.each(data, function(index, obj) {
                for (property in obj)  {
                    if (objUserId == property) {
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
                    url:crudUserUrl+"/"+objIds,
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
    //导入信息
    $("#importBtn").on("click",function(){
        var checkStatus = table.checkStatus('objReload')
            ,data = checkStatus.data;
        var nodes=tree.getSelectedNodes();
        if (nodes.length == 1) {
            console.log(nodes[0]);
            var id = nodes[0].id;
            var pId = nodes[0].pId;
            layer.open({
                id:"importUserObj",
                type: 2,
                title: addUserTitle,
                shadeClose: false,
                shade: [0.3, '#000'],
                maxmin: true, //开启最大化最小化按钮
                area: [dailogWidth, dailogHeight],
                content: importUserInfoUrl+"?id="+id+"&pId="+pId,
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
            layer.msg('请选择一个组织部门进行导入', {icon: 5});
        }
    });
    //监听保存导入信息
    form.on('submit(saveUserObj)', function (data) {
        $.ajax({
            type:"POST",
            url:saveImportUserUrl,
            data:$("#addUserForm").serialize(),
            success:function(resData) {
                if (resData.status === 200) {
                    //询问框
                    var data = resData.data;
                    var msg = resData.msg;
                    var totalCount = data.totalCount;
                    var errorCount = data.errorCount;
                    var errNameCount = data.errNameCount;
                    var errNameExist = data.errNameExist;
                    var existList = data.existList;

                    var tipStr = msg+"<br>"+"总导入个数："+totalCount+"<br>"+"失败个数："+errorCount+"<br>"+"学号或工号错误个数："+errNameCount+"<br>"+"用户已经存在个数："+errNameExist+"<br>"+"已经存在用户："+existList;

                    layer.confirm(tipStr, {
                        btn: ['结束导入','重新导入'] //按钮
                    }, function(){
                        layer.msg('导入完成', {icon: 1,time:1200},function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        });
                    }, function(){

                    });
                } else {
                    layer.msg(resData.msg, {icon: 5});
                }
            }
        });
        return false;//注释掉这行代码后，表单将会以普通方式提交表单，否则以ajax方式提交表单
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
