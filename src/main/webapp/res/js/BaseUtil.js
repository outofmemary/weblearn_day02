var RequestUtil = function () {
    function getDataSync(url, data) {
        var result;
        $.ajax({
            async:false,
            url: url,
            data: data,
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                result = res;
            }
        });
        return result;
    }

    function postDataSync(url, data) {
        var result;
        $.ajax({
            async:false,
            url: url,
            data: data,
            type: 'POST',
            dataType: 'json',
            success: function (res) {
                result = res;
            }
        });
        return result;
    }

    return {
        getDataSync:getDataSync,
        postDataSync:postDataSync,
    }

}()