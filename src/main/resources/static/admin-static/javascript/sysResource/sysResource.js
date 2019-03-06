//基础配置
var objId = "resourceId";//主键
var addTitle = "新增资源菜单";//新增标题
var editTitle = "修改资源菜单";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/admin/sysResource";//增刪改查請求地址
var pageQueryUrl = "/admin/sysResources";//分页请求地址
var addContentUrl = "/content/dist/views/admin/sysResource/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/admin/sysResource/edit.html";//修改页面请求地址
//扩展属性
var sysResourceByTypeUrl = "/admin/sysResource/type/";//根据菜单类型查询请求地址

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'resname', title: '菜单名称', fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'resurl', title: '菜单URL', fixed: 'left', align: 'center'}
      ,{field:'parentName',templet: '#checkPRes', title: '父级菜单', align: 'center'}
      ,{field:'rescode', title: '授权标识', align: 'center'}
      ,{field:'type', title: '类型', sort: true, align: 'center',templet: '#checkType', unresize: true} //单元格内容水平居中
      ,{field:'icon', title: '图标', align: 'center',templet: '#checkIcon', unresize: true} //单元格内容水平居中
      ,{field:'description', title: '描述', align: 'center'} //单元格内容水平居中
      ,{field:'sort', title: '排序', align: 'center', sort: true} //单元格内容水平居中
      ,{field:'flag', title: '状态',templet: '#checkFlag', unresize: true, sort: true, align: 'center'}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
    ]];
layui.use(['form'], function(){
    var form = layui.form;
    //监听单选框
    form.on('radio(radioType)', function(data){
	  if (data.value == '0') {
		  $("#resurlID").css({"display":"none"});
		  $("#parentIdBox").css({"display":"none"});
		  //$("#rescodeID").css({"display":"none"});
	  } else {
		  $("#resurlID").css({"display":"block"});
		  $("#parentIdBox").css({"display":"block"});
	  }
	  var type;
	  if (data.value == "1") {
		  type = "0";
	  }
	  if (data.value == "2") {
		  type = "1";
	  }
	  $.ajax({
		  type:"GET",
		  url: sysResourceByTypeUrl+type,
		  success:function(resData) {
			  var list = resData.data;
			  var html = "";
			  $("#parentId").html(html);
			  for (var i = 0; i < list.length; i++) {
				  var obj = list[i];
				  html += '<option value="'+obj.resourceId+'">'+obj.resname+'</option>';
			  }
			  $("#parentId").html(html);
			  form.render();
		  }
	  });
	});  
});