/** layuiAdmin-v1.0.0-beta6 LPPL License By http://www.layui.com/admin/ */
;layui.define(function (e) {
    var i = (layui.$, layui.layer, layui.laytpl, layui.setter, layui.view, layui.admin);
    i.events.logout = function () {
    	layer.confirm('真的要退出系统吗?', {
			  skin: 'layui-layer-molv',
			  btn: ['确定','取消'] //按钮
			}, function(){
				i.req({
		            url: "/admin/sysUser/logout", type: "get", data: {}, done: function (resData) {
		            	if (resData.status === 200) {
		            		location.href = "/login.html";
		            	}
		            }
		        })
						
			}
		);
    }, e("common", {})
});