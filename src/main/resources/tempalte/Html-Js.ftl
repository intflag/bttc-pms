//基础配置
var objId = "${pkcolumnLowercase}";//主键
var addTitle = "新增${functioncomment}";//新增标题
var editTitle = "修改${functioncomment}";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/app/${classNameLowercase}";//增刪改查請求地址
var pageQueryUrl = "/app/${classNameLowercase}s";//分页请求地址
var addContentUrl = "/content/dist/views/app/${classNameLowercase}/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/${classNameLowercase}/edit.html";//修改页面请求地址
//扩展属性

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      <#list cloums as c>
      ,{field:'${c.COLUMN_NAME}', title: '${c.COLUMN_COMMENT}', align: 'center'}
      </#list>
    ]];