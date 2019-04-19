//基础配置
var objId = "recordId";//主键
var addTitle = "新增指导记录";//新增标题
var editTitle = "修改指导记录";//修改标题
var dailogWidth = "50%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/app/pmsRecord";//增刪改查請求地址
var pageQueryUrl = "/app/pmsRecords";//分页请求地址
var addContentUrl = "/content/dist/views/app/pmsRecord/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/pmsRecord/edit.html";//修改页面请求地址
//扩展属性
var findUserByGroupUrl = "/admin/sysUser/group/";
var findPlanByUser = "/app/pmsPlan/user";
var findPlanById = "/app/pmsPlan/";
var findUserById = "/admin/sysUser/user/";

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'planType', title: '计划类型', align: 'center',templet: '#planType'}
      ,{field:'planName', title: '计划名称', align: 'center'}
      ,{field:'username', title: '学生学号', align: 'center'}
      ,{field:'nickname', title: '学生姓名', align: 'center'}
      ,{field:'teachNick', title: '指导教师', align: 'center'}
      ,{field:'paperName', title: '论文名称', align: 'center'}
      ,{field:'flag', title: '标记', align: 'center',templet: '#checkFlag'}
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

    form.on('select(planId)', function(data){
        console.log(data.elem); //得到select原始DOM对象
        console.log(data.value); //得到被选中的值
        console.log(data.othis); //得到美化后的DOM对象
        var planId = data.value;
        console.log(planId.length);
        if (planId.length > 0) {
            $.ajax({
                type: "GET",
                url: findPlanById+planId,
                success: function (resData) {
                    if (resData.status === 200) {
                        var groupId = resData.data.userId;
                        if (groupId != null && groupId.length > 0) {
                            $("input[name='planType']").val(resData.data.planType);
                            $("input[name='planName']").val(resData.data.planName);
                            $.ajax({
                                type: "GET",
                                url: findUserByGroupUrl+groupId,
                                success: function (resData) {
                                    if (resData.status === 200) {
                                        $("#teachId").html("");
                                        $("#teachId").append('<option value="" selected="">请选择指导教师</option>');
                                        var teachers = resData.data;
                                        for (var i =0;i < teachers.length;i++) {
                                            var p = teachers[i];
                                            $("#teachId").append('<option value="'+p.userId+'">'+p.nickname+' 【'+p.groupName+'】</option>');
                                        }
                                        form.render();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    });
    form.on('select(teachId)', function(data){
        var userId = data.value;
        console.log(userId.length);
        if (userId.length > 0) {
            $.ajax({
                type: "GET",
                url: findUserById+userId,
                success: function (resData) {
                    if (resData.status === 200) {
                        $("input[name='teachUser']").val(resData.data.username);
                        $("input[name='teachNick']").val(resData.data.nickname);
                    }
                }
            });
        }
    });
    //验证权限
    $.ajax({
        type:"GET",
        url:"/admin/sysUser/currentUser",
        success:function(resData) {
            if (resData.status === 200) {
                var user = resData.data;
                if (user.userType !== "1") {
                    $("#editBtn").show();
                    // $("#deleteBatchBtn").show();
                }
            }
        }
    });
});