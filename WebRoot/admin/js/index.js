/**
 * Created by VULCAN on 2017/8/28.
 */
/**
 * 格式化数字为00
 * @param number
 * @returns {string}
 */
function getTwoFormat(number) {
    return number > 10 ? number : "0" + number;
};

/**
 * 获取时间
 * @returns {string}
 */
function getTime() {
    var date = new Date();
    var weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
    var year = date.getFullYear();
    var mouth = getTwoFormat(date.getMonth() + 1);
    var day = getTwoFormat(date.getDate());
    //返回date.getDay()返回星期，然后从数组中获取星期的中文
    var week = weeks[date.getDay()];
    var hours = getTwoFormat(date.getHours());
    var minutes = getTwoFormat(date.getMinutes());
    var seconds = getTwoFormat(date.getSeconds());
    return year + "年" + mouth + "月" + day + "日" + "  " + hours + ":" + minutes + ":" + seconds + "  " + week;

};
$(function(){
	
	$('input').attr('autocomplete', 'off');
	
});


$(function () {

    //设置时间
    window.setInterval(function () {
        $("a.a-time").text(getTime());
    }, 1);
});