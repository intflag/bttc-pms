//基础配置
var objId = "roleId";//主键
var addTitle = "新增角色";//新增标题
var editTitle = "修改角色";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/admin/sysRole";//增刪改查請求地址
var pageQueryUrl = "/admin/sysRoles";//分页请求地址
var addContentUrl = "/content/dist/views/admin/sysRole/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/admin/sysRole/edit.html";//修改页面请求地址
//扩展属性
var resourceListUrl = "/admin/sysRole/resources";//资源菜单列表请求地址
var sysRoleResKeysUrl = "/admin/sysRole/sysRoleResKeys";//插叙角色拥有的权限请求地址
//分页属性
var pageField = [[
	  {type:'checkbox', fixed: 'left'}
      ,{field:'rolename', title: '角色名称', fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'description', title: '角色描述', align: 'center'} //单元格内容水平居中
      ,{field:'sort', title: '排序', align: 'center', sort: true} //单元格内容水平居中
      ,{field:'flag', title: '状态',templet: '#checkFlag', unresize: true, sort: true, align: 'center'}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
    ]];

layui.use(['form'], function(){
	var form = layui.form;
	//监听新增提交                   
    form.on('submit(saveRoleObj)', function(data){
    	//根据zTree的ID获取zTree对象
		var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
		//获取选中的ID
		var nodes = treeObj.getCheckedNodes(true);
		
		//遍历选中节点
		var array = new Array();
		for (var i = 0; i < nodes.length; i++) {
			array.push(nodes[i].resourceId);
		}
		var resourceIds = array.join(",");
		//为资源隐藏域赋值
		data.field.resourceIds = resourceIds;
		console.info(data);
		$.ajax({
			type:"POST",
			url:crudUrl,
			data: data.field,
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
        return false;
    });
    //监听更新
    form.on('submit(updateRoleObj)', function (data) {
    	//根据zTree的ID获取zTree对象
		var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
		//获取选中的ID
		var nodes = treeObj.getCheckedNodes(true);
		
		//遍历选中节点
		var array = new Array();
		for (var i = 0; i < nodes.length; i++) {
			array.push(nodes[i].resourceId);
		}
		var resourceIds = array.join(",");
		//为资源隐藏域赋值
		data.field.resourceIds = resourceIds;
  	  $.ajax({
  		  type:"PUT",
  		  url:crudUrl,
  		  data: data.field,
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
});