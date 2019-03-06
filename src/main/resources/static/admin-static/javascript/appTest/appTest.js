//基础配置
var objId = "appId";//主键
var addTitle = "新增测试";//新增标题
var editTitle = "修改测试";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/app/appTest";//增刪改查請求地址
var pageQueryUrl = "/app/appTests";//分页请求地址
var addContentUrl = "/content/dist/views/app/appTest/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/appTest/edit.html";//修改页面请求地址
//扩展属性

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'appName', title: '姓名', align: 'center'}
      ,{field:'appPwd', title: '密码', align: 'center'}
      ,{field:'description', title: '描述', align: 'center'}
    ]];