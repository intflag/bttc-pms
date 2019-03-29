//基础配置
var objId = "paperId";//主键
var addTitle = "新增论文提交";//新增标题
var editTitle = "修改论文提交";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/app/pmsPaper";//增刪改查請求地址
var pageQueryUrl = "/app/pmsPapers";//分页请求地址
var addContentUrl = "/content/dist/views/app/pmsPaper/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/pmsPaper/edit.html";//修改页面请求地址
//扩展属性

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'recordId', title: '论文指导记录', align: 'center'}
      ,{field:'paperName', title: '论文名称', align: 'center'}
      ,{field:'stuNum', title: '学生学号', align: 'center'}
      ,{field:'stuName', title: '学生名称', align: 'center'}
      ,{field:'teachId', title: '指导教师ID', align: 'center'}
      ,{field:'teachName', title: '指导教师名称', align: 'center'}
      ,{field:'fileType', title: '文件类型', align: 'center'}
      ,{field:'fileUrl', title: '文件位置', align: 'center'}
      ,{field:'summary', title: '摘要', align: 'center'}
      ,{field:'keywords', title: '论文关键字', align: 'center'}
      ,{field:'flag', title: '标记', align: 'center'}
      ,{field:'description', title: '描述', align: 'center'}
      ,{field:'field01', title: '', align: 'center'}
      ,{field:'field02', title: '', align: 'center'}
      ,{field:'field03', title: '', align: 'center'}
    ]];