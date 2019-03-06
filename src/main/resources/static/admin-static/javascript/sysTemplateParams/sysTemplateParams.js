//基础配置
var objId = "templateId";//主键
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/admin/sysTemplateParams";//增刪改查請求地址
var pageQueryUrl = "/admin/sysTemplateParamss";//分页请求地址
//扩展属性
var selectDataBaseTables = "/admin/sysTemplateParams/dataBaseTables";
var selectFieldByTableName = "/admin/sysTemplateParams/fieldByTableName";

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'tablename', title: '数据库表名', fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'functioncomment', title: '功能说明', fixed: 'left', align: 'center'}
      ,{field:'author', title: '作者', fixed: 'left', align: 'center'}
      ,{field:'classnames', title: '类名', align: 'center'}
      ,{field:'filepath', title: '生成路径', align: 'center'} //单元格内容水平居中
      ,{field:'pkcolumn', title: '主键', align: 'center'} //单元格内容水平居中
      ,{field:'actionurl', title: '模块请求地址', align: 'center'} //单元格内容水平居中
      /*,{field:'addfun', title: '添加方法', align: 'center'} //单元格内容水平居中
      ,{field:'updatefun', title: '修改方法', align: 'center'} //单元格内容水平居中
      ,{field:'selectfun', title: '查找方法', align: 'center'} //单元格内容水平居中
      ,{field:'deletefun', title: '删除方法', align: 'center'} //单元格内容水平居中*/
      ,{field:'addmenu', title: '是否生成菜单',templet: '#addmenu', unresize: true, sort: true, align: 'center'}
      /*,{field:'sort', title: '排序', align: 'center', sort: true} //单元格内容水平居中*/
      ,{field:'flag', title: '状态',templet: '#checkFlag', unresize: true, sort: true, align: 'center'}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
    ]];
$(function(){
	//查询数据库所有表
	$.ajax({
		  type:"GET",
		  url: selectDataBaseTables,
		  success:function(resData) {
			  var list = resData.data;
			  var html = "";
			  $("#tableNameBox").html(html);
			  for (var i = 0; i < list.length; i++) {
				  var obj = list[i];
				  html += '<option value="'+obj.TABLENAME+'">'+obj.TABLENAME+'</option>';
			  }
			  $("#tableNameBox").html(html);
			  if (list.length > 0) {
				  var tName = list[0].TABLENAME;
				  $.ajax({
			  		  type:"GET",
			  		  url: selectFieldByTableName+"/"+tName,
			  		  success:function(resData) {
			  			  var list = resData.data;
			  			  var html = "";
			  			  $("#pkColumnBox").html(html);
			  			  for (var i = 0; i < list.length; i++) {
			  				  var obj = list[i];
			  				  html += '<option value="'+obj.COLUMN_NAME+'">'+obj.COLUMN_NAME+' ['+obj.COLUMN_COMMENT+']</option>';
			  			  }
			  			  $("#pkColumnBox").html(html);
			  			  //重新渲染
			  			  layui.use(['form'], function () {
			  			  	var form = layui.form;
			  				form.render();
			  			  });
			  		  }
			  	  });
			  }
			  //重新渲染
			  layui.use(['form'], function () {
			  	var form = layui.form;
				form.render();
			  });
		  }
	  });
	//监听生成代码按钮
	$("#generatorCodeBtn").on("click",function(){
		alert("3333");
		debugger
		$.ajax({
			type:"POST",
			url:crudUrl,
			data:$("#addTemplateParamsForm").serialize(),
			success:function(resData) {
				if (resData.status === 200) {
					layer.msg(resData.msg, {icon: 1,time:1200},function(){
					});
				} else {
					layer.msg(resData.msg, {icon: 5});
				}
			}
		});
	});
});
layui.use(['form'], function(){
	var form = layui.form;
	//监听下拉
    form.on('select(selectTableName)', function(data){
    	var tableName=data.value;
    	$.ajax({
	  		  type:"GET",
	  		  url: selectFieldByTableName+"/"+tableName,
	  		  success:function(resData) {
	  			  var list = resData.data;
	  			  var html = "";
	  			  $("#pkColumnBox").html(html);
	  			  for (var i = 0; i < list.length; i++) {
	  				  var obj = list[i];
	  				  html += '<option value="'+obj.COLUMN_NAME+'">'+obj.COLUMN_NAME+' ['+obj.COLUMN_COMMENT+']</option>';
	  			  }
	  			  $("#pkColumnBox").html(html);
	  			  //重新渲染
	  			  layui.use(['form'], function () {
	  			  	var form = layui.form;
	  				form.render();
	  			  });
	  		  }
	  	  });
    	return false;
    });
    //监听保存
    form.on('submit(saveTemplateParamsObj)', function (data) {
  	$.ajax({
  		type:"POST",
  		url:crudUrl,
  		data:$("#addTemplateParamsForm").serialize(),
  		success:function(resData) {
  			if (resData.status === 200) {
  				layer.msg("生成代码成功", {icon: 1});
  			} else {
  				layer.msg(resData.msg, {icon: 5});
  			}
  		}
  	});
      return false;//注释掉这行代码后，表单将会以普通方式提交表单，否则以ajax方式提交表单
    });
});