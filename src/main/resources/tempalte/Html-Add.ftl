<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/admin-static/layui/css/layui.css">
</head>
<body>
<div class="layui-field-box" style="margin-top: 10px;">
<form id="addForm" class="layui-form" action="" onsubmit="return false;">
    <#list cloums as c>
	<div class="layui-form-item">
		<label class="layui-form-label">${c.COLUMN_COMMENT}</label>
		<div class="layui-input-block">
			<input type="text" name="${c.COLUMN_NAME}" value=""
				lay-verify="required" placeholder="请输入${c.COLUMN_COMMENT}" autocomplete="off"
				class="layui-input">
		</div>
	</div>
	</#list>
    <div class="page-footer" style="position: fixed;bottom: 0;margin-bottom: 45px;">
        <div class="btn-list">
            <div class="btnlist" style="position: fixed;right: 0;margin-right: 10px;">
                <button class="layui-btn" lay-submit lay-filter="saveObj"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                <button class="layui-btn openWindow"><i class="fa fa-undo">&nbsp;</i>返回</button>
            </div>
        </div>
    </div>
</form>
</div>
</body>
<script type="text/javascript" src="/admin-static/javascript/jquery.min.js"></script>
<script type="text/javascript" src="/admin-static/layui/layui.js"></script>
<script type="text/javascript" src="/admin-static/javascript/common/common-list.js"></script>
<script type="text/javascript" src="/admin-static/javascript/${classNameLowercase}/${classNameLowercase}.js"></script>
<script type="text/javascript">

</script>
</html>