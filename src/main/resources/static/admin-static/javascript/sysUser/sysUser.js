//基础配置
var objId = "userId";//主键
var addTitle = "新增用户";//新增标题
var editTitle = "修改用户";//新增标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/admin/sysUser";//增刪改查請求地址
var pageQueryUrl = "/admin/sysUsers";//分页请求地址
var addContentUrl = "/content/dist/views/admin/sysUser/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/admin/sysUser/edit.html";//修改页面请求地址
//扩展属性
var sysRoleListUrl = "/admin/sysRoleList";//查询所有角色地址
var sysRoleUserKeysUrl = "/admin/sysRole/sysRoleUserKeys";//查询角色用户关联地址

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'username', title: '登录名',fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'nickname', title: '昵称',fixed: 'left', align: 'center'}
      ,{field:'gender', title: '性别',templet: '#checkGender', unresize: true, align: 'center', sort: true}
      ,{field:'email', title: '邮箱', align: 'center'} //单元格内容水平居中
      ,{field:'telephone', title: '电话', align: 'center'} //单元格内容水平居中
      ,{field:'flag', title: '状态', align: 'center',templet: '#checkFlag', unresize: true, sort: true}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
    ]];