/**
 * Created by admin on 2017/9/14.
 */
// $(document).ready(function() {
//     $(".header-arrow").hover(function() {
//         $('.header-click').attr("style","visibility:visible");
//     },function () {
//         $('.header-click').attr("style","visibility:collapse");
//     });
// });
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//一年前的今天的前一天
function oneYearPast()
{
    var time = new Date();
    var year=time.getFullYear()-1;
    var month=time.getMonth()+1;
    var day=time.getDate();
    console.log(month);
    if(month<10){
        month="0"+month;
    }

    if(day>1){
        day = day-1;
    }else{
        month = month-1;
        console.log(month);
        if(month<10){
            month="0"+month;
        }
        if(month==0){
            month = 12;
        }
        day=new Date(year,month,0).getDate();
    }
    var v2=year+'/'+month+'/'+day;
    return v2;
}
// var ip = "172.16.17.200:8080"
var ip = "";

function changeData(value) {
   var da = value.split("/");
    return da[0]+"-"+da[1]+"-"+da[2];
}
