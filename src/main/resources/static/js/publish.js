function selectTags(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();

    //如果不存在,添加标签
    if (previous.split(',').indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function showSelectTag() {
   $("#select_tag").show();

}