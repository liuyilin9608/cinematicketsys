(function () {
    function getTwoLenNumStr(num) {
        if (num < 10)
            return "0" + num;
        else
            return num;
    }
    
    window.formatDate = function (millisTime) {
        var time = new Date(millisTime);
        return time.getFullYear() + "/" + getTwoLenNumStr(time.getMonth() + 1) + "/" +
            getTwoLenNumStr(time.getDate()) + " " + getTwoLenNumStr(time.getHours()) + ":" + getTwoLenNumStr(time.getMinutes());
    };
    window.confirmAction = function (msg) {
        return confirm(msg)
    };
    window.search = function (baseUrl) {
        var kw = document.getElementsByName("keyword")[0].value;
        if (kw === "") {
            window.location.href = baseUrl;
        } else {
            window.location.href = baseUrl + "?keyword=" + encodeURIComponent(encodeURIComponent(kw));
        }        
    };
    window.encodeAllURI =function () {
        var aNodeList = document.getElementsByTagName("a");
        if (aNodeList.length > 0) {
            for (var i = 0; i < aNodeList.length; i++) {
                var href = aNodeList[i].href;
                var index = href.indexOf("?");
                if (index > 0 && index < href.length) {
                    var front = href.substring(0, index + 1);
                    var paramsPart = href.substring(index + 1);
                    paramsPart = encodeURIComponent(paramsPart).replace("%3D", "=").replace("%26", "&").replace("%23", "#");
                    aNodeList[i].href = front + paramsPart;
                }               
            }
        }
    };
})();