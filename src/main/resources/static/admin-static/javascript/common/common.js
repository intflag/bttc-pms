var contextPath = "";

//doT.js
//将数据（dataObject）绑定到指定元素（element）的后代元素
function dataBindToElement(element,dataObject){
	for (property in dataObject)  
	  {  
		var dataValue = dataObject[property];
		if (property == "birthday") {
			dataValue = getDatesStr(dataValue);
		}
		if(!dataValue){
			dataValue = "";
		}
		var $node = element.find("[identity='"+property+"']");
		if($node.prop("nodeName") === "INPUT" 
			|| $node.prop("nodeName") === "TEXTAREA" 
				|| $node.prop("nodeName") ==="SELECT"){
			$node.val(dataValue);
		}
		else{
			$node.text(dataValue);
		}
	  }
}

function getDataFromElement(element){
	var data = {};
	var $nodes = $(element).find("[identity]");
	$.each($nodes,function(i,n){
		var $n = $(n);
		if($n.prop("nodeName") === "INPUT" 
			|| $n.prop("nodeName") === "TEXTAREA" 
				|| $n.prop("nodeName") ==="SELECT"){
			
			data[$n.attr("identity")] = $n.val();
			
		}
		else{
			data[$n.attr("identity")] = $n.text();
		}
	});
	return data;
}

function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 

//分页变量
var keyWords = "";
var currPage = 1;
var pageSize = 5;

//设置分页
function setPagination(keyWords,currPage,pageSize,totalPage) {
	var pageHtml = "";
	$("#pagination").html(pageHtml);
	//判断是否是首页
	if (currPage == 1) {
		pageHtml += '<li class="disabled">';
		pageHtml += '<a href="javascript:void(0)" aria-label="Previous">';
		pageHtml += '<span aria-hidden="true">&laquo;</span>';
		pageHtml += '</a></li>';
	} else {
		pageHtml += '<li>';
		pageHtml += '<a href="javascript:void(0)" aria-label="Previous" onclick="pageQuery(\''+keyWords+'\','+(currPage-1)+','+pageSize+')">';
        pageHtml += '<span aria-hidden="true">&laquo;</span>';    
        pageHtml += '</a></li>';
	}
	//展示所有页码
	var begin = currPage-5>0?currPage-5:1;
	var end = currPage+4>totalPage?totalPage:currPage+4;
	for (var i = begin;i <= end; i++) {
		//判断是否是当前页
		if (i == currPage) {
			pageHtml += '<li class="active"><a href="javascript:void(0)">'+i+'</a></li>';
		} else {
			pageHtml += '<li><a href="javascript:void(0)" onclick="pageQuery(\''+keyWords+'\','+i+','+pageSize+')">'+i+'</a></li>';
		}
	}
	//判断是否是最后一页
	if (totalPage == currPage) {
		pageHtml += '<li class="disabled">';
		pageHtml += '<a href="javascript:void(0)" aria-label="Next">';
		pageHtml += '<span aria-hidden="true">&raquo;</span>';
		pageHtml += '</a></li>';
	} else {
		pageHtml += '<li>';
		pageHtml += '<a href="javascript:void(0)" aria-label="Next" onclick="pageQuery(\''+keyWords+'\','+(currPage+1)+','+pageSize+')">';
        pageHtml += '<span aria-hidden="true">&raquo;</span>';    
        pageHtml += '</a></li>';
	}
	$("#pagination").html(pageHtml);
}
//设置表单数据
function setFormData(formId,dataObject) {
	for (property in dataObject) {
		var dataValue = dataObject[property];
		if (!dataValue) {
			dataValue = "";
		}
		if (property == "birthday") {
			dataValue = getDatesStr(dataValue);
		}
		var $node = $("#" + formId).find("[name='" + property + "']");
		if ($node.prop("nodeName") === "INPUT"
				|| $node.prop("nodeName") === "TEXTAREA"
				|| $node.prop("nodeName") === "SELECT") {
			if ($node.length === 2 && property == "gender") {
				$node.each(function(i,n){
					if ($(this).prop("value") == dataValue) {
						$(this).prop("checked","checked");
					}
				});
			} else if ($node.length === 3 && property == "type") {
                $node.each(function(i,n){
                    if ($(this).prop("value") == dataValue) {
                        $(this).prop("checked","checked");
                    }
                });
            } else if ($node.length === 3 && property == "planType") {
                $node.each(function(i,n){
                    if ($(this).prop("value") == dataValue) {
                        $(this).prop("checked","checked");
                    }
                });
            }  else if ($node.length === 3 && property == "userType") {
                $node.each(function(i,n){
                    if ($(this).prop("value") == dataValue) {
                        $(this).prop("checked","checked");
                    }
                });
            }   else if ($node.length === 2 && property == "userType") {
                $node.each(function(i,n){
                    if ($(this).prop("value") == dataValue) {
                        $(this).prop("checked","checked");
                    }
                });
            } else {
				$node.val(dataValue);
			}
		} else {
			$node.text(dataValue);
		}
	}
}
/*全局方法	- 时间转换*/
function getDatesStr(m) {
	var now =  new Date(m);
    var year = now.getFullYear();
    var month =(now.getMonth() + 1).toString();
    var day = (now.getDate()).toString();
    if (month.length == 1) {
        month = "0" + month;
    }
    if (day.length == 1) {
        day = "0" + day;
    }
    var dateTime = year +"-"+ month +"-"+  day;
    return dateTime;
}

/**
 * layui中的日期转日期字符串
 * @param m
 * @returns {string}
 */
function layuiDate2Str(m) {
    return m.year+"-"+m.month+"-"+m.date;
}
//获取url中的参数
function getUrlParam(name) {
 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
 var r = window.location.search.substr(1).match(reg); //匹配目标参数
 if (r != null) return unescape(r[2]); return null; //返回参数值
}

//询问是否下载文件，墨绿色风格
function downloadFile(res) {
    //询问框
    var index = layer.confirm('确认要下载该文档吗？', {
        skin: 'layui-layer-molv', //样式类名
        btn: ['下载', '取消'] //按钮
    }, function() {
        location.href = res;
        layer.close(index);
    });
}