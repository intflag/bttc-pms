//基础配置
var objId = "blogId";//主键
var addTitle = "新增公告管理";//新增标题
var editTitle = "修改公告管理";//修改标题
var dailogWidth = "80%";//模态框宽度
var dailogHeight = "80%";//模态框高度
var crudUrl = "/app/pmsBlog";//增刪改查請求地址
var pageQueryUrl = "/app/pmsBlogs";//分页请求地址
var addContentUrl = "/content/dist/views/app/pmsBlog/add.html";//新增页面请求地址
var editContentUrl = "/content/dist/views/app/pmsBlog/edit.html";//修改页面请求地址
var uploadUrl = "/app/appendixUpload"; //附件上传地址
//扩展属性

//分页属性
var pageField = [[
   	  {type:'checkbox', fixed: 'left'}
    ,{field:'blogTitle', title: '公告主题', align: 'center'}
    ,{field:'publisher', title: '发布者', align: 'center'}
    ,{field:'flag', title: '状态', align: 'center',templet: '#checkFlag', unresize: true, sort: true}
    ,{field:'description', title: '详细内容', align: 'center'}
    ,{field:'summary', title: '摘要', align: 'center'}
    ]];

layui.use(['layedit','upload'], function(){
    var layedit = layui.layedit;
    var $ = layui.jquery
        ,upload = layui.upload;

    /*layedit.set({
        uploadImage: {
            url: uploadUrl //接口url
            ,type: 'post' //默认post
        }
    });
    var fwb = layedit.build('blogDesc'); //建立编辑器*/
    /*form.verify({
        blogDesc: function(value){
            layedit.sync(fwb);
        }
    });*/

    //多文件列表示例
    var fileListView = $('#fileList')
        ,uploadListIns = upload.render({
        elem: '#selectFileBtn'
        ,url: uploadUrl
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
                $("#appendixIds").append('<input name="appendixIds" value="'+res.data.appendixId+'">');
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
});