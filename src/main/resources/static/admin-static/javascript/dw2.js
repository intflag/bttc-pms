//基础配置
var pageQueryUrl = "/admin/sysUsers";//分页请求地址
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var addTitle = "新增用户";//新增标题
var addContentUrl = "/admin/sysUser/add";//新增页面请求地址
var editTitle = "修改用户";//新增标题
var editContentUrl = "/admin/sysUser/edit";//修改页面请求地址
var crudUrl = "/admin/sysUser";//增刪改查請求地址
var objId = "userId";//主键
//扩展属性
var sysRoleListUrl = "/admin/sysRoleList";
var sysRoleUserKeysUrl = "/admin/sysRole/sysRoleUserKeys";

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
    ,cols: [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'username', title: '登录名',fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'nickname', title: '昵称',fixed: 'left', align: 'center'}
      ,{field:'gender', title: '性别',templet: '#checkGender', unresize: true, align: 'center', sort: true}
      ,{field:'email', title: '邮箱', align: 'center'} //单元格内容水平居中
      ,{field:'telephone', title: '电话', align: 'center'} //单元格内容水平居中
      ,{field:'flag', title: '状态', align: 'center',templet: '#checkFlag', unresize: true, sort: true}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
    ]]
    ,id : 'objReload'
    ,done: function(res, curr, count){
    	//关闭加载图标
		layer.close(loadIcon);  
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
	  layer.open({
          id:"addObj",
          type: 2,
          title: addTitle,
          shadeClose: false,
          shade: [0.3, '#000'],
          maxmin: true, //开启最大化最小化按钮
          area: [dailogWidth, dailogHeight],
          content: addContentUrl,
          end : function() {
			  //执行重载
	      	  table.reload('objReload');
		  }
      });
  });
  
  //监听保存
  form.on('submit(saveObj)', function (data) {
	$.ajax({
		type:"POST",
		url:crudUrl,
		data:$("#addForm").serialize(),
		success:function(resData) {
			if (resData.status === 200) {
				layer.msg(resData.msg, {icon: 1});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭   
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
				  layer.msg(resData.msg, {icon: 1});
				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				  parent.layer.close(index); //再执行关闭   
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
	  			layer.msg('删除：'+ objIds);
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
  //刷新
  $("#refreshBtn").on("click",function(){
	  location.href = location.href;
  });
  
  /*var $ = layui.$, active = {
    searchBtn: function() {
    	
    }
  };*/

  // +----------------------------------------------------------------------
  // | 弹出层
  // +----------------------------------------------------------------------
  /*$(".dw-dailog").click(function () {
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    var dw_title = $(this).attr("dw-title");//弹出层标题，必填
    var dw_width = $(this).attr("dw-width");//弹出层宽度，如80%或500px；如果没有默认为屏幕宽度的50%
    var dw_height = $(this).attr("dw-height");//弹出层高度，如50%或500px；如果没有默认为屏幕高度的50%
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    if (dw_title == undefined) {
      layer.msg("请给button加上dw-title属性");
      return false;
    }
    if (dw_width == undefined) dw_width = '50%';
    if (dw_height == undefined) dw_height = '50%';
    layer.open({
      type: 2,
      title: dw_title,
      shadeClose: true,
      shade: 0.8,
      area: [dw_width, dw_height],
      content: dw_url,
      cancel: function (index, layero) {
        $(".dw-refresh").trigger('click');
        return false;
      }
    });
  });
  // +----------------------------------------------------------------------
  // | 批量删除
  // +----------------------------------------------------------------------
  $(".dw-batch-delete").click(function () {
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    var chk_value = [];
    $('input[name="id"]:checked').each(function () {
      chk_value.push($(this).val());
    });
    if (chk_value.length == 0) {
      layer.msg("请选择要删除的数据!", { anim: 1 });
      return false;
    }
    layer.confirm('确定要删除选中的数据吗?', { icon: 3, title: '提示' }, function (index) {
      $.ajax(dw_url, { id: chk_value }, function (obj) {
        if (obj.code == 1) {
          layer.msg(obj.msg, { anim: 1 });
          $(".dw-refresh").trigger('click');
        } else {
          layer.msg(obj.msg, { anim: 1 });
        }
      });
      layer.close(index);
    });
  });
  // +----------------------------------------------------------------------
  // | 删除
  // +----------------------------------------------------------------------
  $(".dw-delete").click(function () {
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    var dw_title = $(this).attr("dw-title");//删除数据标识，如姓名，默认为'该数据'
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    if (dw_title == undefined) {
      dw_title = '该数据';
    }
    layer.confirm('确定要删除[' + dw_title + ']吗?', { icon: 3, title: '提示' }, function (index) {
      $.ajax(dw_url, {}, function (obj) {
        if (obj.code == 1) {
          layer.msg(obj.msg, { anim: 1 });
          $(".dw-refresh").trigger('click');
        } else {
          layer.msg(obj.msg, { anim: 1 });
        }
      });
      layer.close(index);
    });
  });
  // +----------------------------------------------------------------------
  // | 刷新
  // +----------------------------------------------------------------------
  $(".dw-refresh").click(function () {
    location.href = location.href;
  });*/
});