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
//扩展属性

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'groupName', title: '组织名称', align: 'center'}
      ,{field:'pid', title: '父ID', align: 'center'}
      ,{field:'flag', title: '标记', align: 'center'}
      ,{field:'description', title: '描述', align: 'center'}
    ]];