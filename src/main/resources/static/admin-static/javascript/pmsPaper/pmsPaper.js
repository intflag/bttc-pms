//基础配置
var objId = "paperId";//主键
var addTitle = "新增论文提交";//新增标题
var editTitle = "修改论文提交";//修改标题
var dailogWidth = "75%";//模态框宽度
var dailogHeight = "50%";//模态框高度
var crudUrl = "/app/pmsPaper";//增刪改查請求地址
var pageQueryUrl = "/app/pmsPapers";//分页请求地址
var addContentUrl = "/content/dist/views/app/pmsPaper/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/pmsPaper/edit.html";//修改页面请求地址
//扩展属性
var findByUserUrl = "/app/pmsRecord/user";//根据用户查找指导记录
var uploadPaperUrl = "/app/pmsPaper/uploadPaper";

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'field02', title: '计划类型', align: 'center',templet: '#planType'}
      ,{field:'field03', title: '计划名称', align: 'center'}
      ,{field:'paperName', title: '论文名称', align: 'center'}
      ,{field:'flag', title: '状态', align: 'center',templet: '#checkFlag'}
      ,{field:'stuNum', title: '学号', align: 'center'}
      ,{field:'stuName', title: '姓名', align: 'center'}
      ,{field:'teachName', title: '指导教师', align: 'center'}
      ,{field:'fileType', title: '文档类型', align: 'center'}
      ,{field:'field01', title: '文档名称', align: 'center'}
      ,{field:'description', title: '描述', align: 'center'}
      ,{fixed: 'right', align:'center', toolbar: '#barDemo', width:120}
    ]];
layui.use(['element', 'layer','table','form','laydate','upload'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var loadIcon;
    var laydate = layui.laydate;
    var upload = layui.upload;

    form.on('select(recordId)', function(data){
        var recordId = data.value;
        console.log(recordId.length);
        if (recordId.length > 0) {
            $("#recordId2").val(recordId);
        }
    });

    //多文件列表示例
    var fileListView = $('#fileList')
        ,uploadListIns = upload.render({
        elem: '#selectFileBtn'
        ,url: uploadPaperUrl
        ,accept: 'file'
        ,multiple: true
        ,auto: false
        ,bindAction: '#uploadFileBtn'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                fileListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
                var tr = fileListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                //将附件appendixId设置到隐藏域
                console.log(res);
                $("input[name='fileUrl']").val(res.data.src);
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = fileListView.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
    //下载文档
    $("#paperDownloadBtn").on("click",function(){
        var checkStatus = table.checkStatus('objReload')
            ,data = checkStatus.data;
        if (data.length == 1) {
            console.log(data);
            downloadFile(data[0].fileUrl);
        } else {
            layer.msg('请选择一条要下载的文档记录', {icon: 5});
        }
    });
    //监听工具条
    table.on('tool(objT)', function(obj) {
        var data = obj.data;
        if (obj.event === 'download') {
            console.log(data);
            downloadFile(data.fileUrl);
        } else if (obj.event === 'edit') {

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