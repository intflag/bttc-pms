function CUtil() {
    this.ValidateNull = _ValidateNull;
    this.ValidateNumber = _ValidateNumber;
    this.ValidateWord = _ValidateWord;
    this.ValidateEmail = _ValidateEmail;
    this.Trims = _Trim;

    function _ValidateNull(str) {
        return (!str||str.length===0);
    }

    function _ValidateNumber(str) {
        if(_ValidateNull(str)) return false;
        for(var i = 0 ;i<str.length;i++){
            if(str[i]<'0'||str[i]>'9'){
                return false;
            }
        }
        return true;
    }

    function _ValidateWord(str) {
        if(_ValidateNull(str)) return false;
        for(var i = 0 ;i<str.length;i++){
            if(!(str[i]>='a'&&str[i]<='z'||str[i]>='A'&&str[i]<='Z')){
                return false;
            }
        }
        return true;
    }
    function _ValidateEmail(str) {
        return (_ValidateNumber(str)||_ValidateWord(str)||str==='.'||str==='@'||str==='_');
    }

    function _Trim(str) {
        if(_ValidateNull(str)) return str;
        var i = 0;
        var j = str.length;
        while(i<j){
            if(str[i] ===" ")
                i++;
            if(str[j-1] ===" "&&i<j)
                j--;
            if(str[i]!==" "&&str[j-1]!==" ")
                break;
        }
        return str.substring(i,j);
    }
}

function TUtil() {
    var ck = new CUtil();
    this.leepYear = _leepYear;
    this.getMday = _getMday;
    this.getDate = _getDate;
    this.getDayByDate = _getDay;
    this.getTime = _getTime;

    function _leepYear(year) {
        return (year % 4 === 0 && year % 100 !== 0 || year % 400 === 0);
    }

    function _getMday(year) {
        var dd = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        if(_leepYear(year))  {
            dd[1] = 29;
        }
        return dd;
    }

    function _getDate(str){
        str = ck.Trims(str);
        if(!(ck.ValidateNull(str)||str.length<8||ck.ValidateNumber(str[4]))){
            var l = ck.ValidateNumber(str[7])?6:7;
            if(str[4]===str[l]&&str.length<l+4){
                var year = str.substring(0, 4);
                var mm = str.substring(5, l);
                var day = str.substring(l+1, str.length);
                if (ck.ValidateNumber(year)&&ck.ValidateNumber(mm)&&ck.ValidateNumber(day)) {
                    var dd = _getMday(year);
                    if (!(mm < 1 || mm >12 || day < 1 || day > dd[mm-1] )) {
                        return new Date(year,mm,day);
                    }
                }
            }
        }
        return null;
    }

    function _getDay(time_s,time_d) {
        if(ck.ValidateNull(time_s)||ck.ValidateNull(time_d))
            return null;
        return Math.floor((time_d.getTime()-time_s.getTime())/(1000*60*60*24));
    }

    function _getTime(time) {
        var date =  new Date(time);
        var y = date.getFullYear();
        var m = "0"+(date.getMonth()+1);
        var d = "0"+date.getDate();
        return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
    }
}

function Tools() {
    var tu = new TUtil();
    var ck = new CUtil();
    this.dataBindToElement = _dataBindToElement;
    this.ArrToString = _ArrToString;
    this.getUrlParam = _getUrlParam;

    function _dataBindToElement(element,dataObject){
        for (property in dataObject)
        {
            var data;
            var $node;
            //得到数据
            if(ck.ValidateNull(dataObject[property])){
                $node = element.find("[data-bean='"+property+"']");
                if(ck.ValidateNull($node))
                    $node = element.find("[data-name='"+property+"']");
                data = "无";
            }else if(typeof dataObject[property] === "object"){
                $node = element.find("[data-bean='"+property+"']");
                data = dataObject[property][$node.data("name")];
            }else{
                $node = element.find("[data-name='"+property+"']:not([data-bean])");
                data = dataObject[property];
            }
            //添加数据
            if($node.data("type") === "time")
                data = tu.getTime(data);
            if($node.prop("type") === "radio"){
                element.find("[value='"+data+"']").prop( "checked", true );
            }else if($node.prop("type") === "date"){
                $node.attr('value',data);
            }else if($node.prop("nodeName") === "INPUT"
                || $node.prop("nodeName") === "TEXTAREA"
                || $node.prop("nodeName") ==="SELECT"
                || $node.prop("nodeName") ==="BUTTON"){
                $node.val(data);
            }else {
                $node.text(data);
            }
        }
    }

    function _ArrToString(data) {
        if (!ck.ValidateNull(data)&&typeof data === "object"){
            var str="";
            for(var i=0;i<data.length;i++){
                if(i!==0){
                    str+=","
                }
                str+=data[i];
            }
            return str;
        } else{
            return data;
        }
    }

    function _getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (!ck.ValidateNull(r))
            return unescape(r[2]);
        return null;
    }
}

