/*
* 提交回复
*/
function writeBack() {
    var questionId = $("#question_id").val();
    var content = $("#content_id").val();
    comment2target(questionId, 1, content)
}

function comment2target(targetId, type, content) {
    if (!content) {
        layer.msg("回复评论不能为空! ! !");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            window.location.reload();
            if (response.code == 200) {
                $("#commment_section").hide();
            } else {
                if (response.code == 2003) {
                    var conf = confirm(response.message);
                    if (conf) {
                        window.open("https://github.com/login/oauth/authorize?client_id=a250f9e5919fc985cc91&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    layer.msg(response.message);
                }
            }
        },
        dataType: "JSON"
    })
}

function comment(that) {
    var commentId = that.getAttribute("data-id");
    var content = $("#input_" + commentId).val();
    comment2target(commentId, 2, content)
}

/*
* 展示二级评论
*/
function choseComment(that) {
    //获取评论id
    var id = that.getAttribute("data-id");
    //获取二级评论id
    var commentId = $("#comment_" + id);
    var subCommenContainer = $("#comment_" + id);

    if (subCommenContainer.children().length != 1) {
        //添加Class属性
        commentId.toggleClass("in");
        $(that).toggleClass("active");
    } else {
        $.getJSON("/comment/" + id, function (data) {
            $.each(data.data.reverse(), function (index, comment) {

                var mediaLeftElement = $('<div/>', {
                    "class": "media-left",

                }).append($('<img/>', {
                    "class": "media-object img-rounded",
                    "src": comment.user.avatarUrl

                }));

                var mediaBodyElement = $('<div/>', {
                    "class": "media-body",

                }).append($('<h5/>', {
                    "class": "media-heading",
                    "html": comment.user.name

                })).append($('<div/>', {
                    "html": comment.content

                })).append($('<div/>', {
                    "class": "menu",
                }).append($('<span/>', {
                    "class": "pull-right",
                    "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                })));

                var mediaElement = $('<div/>', {
                    "class": "media",

                }).append(mediaLeftElement).append(mediaBodyElement);

                var commentElement = $('<div/>', {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                }).append(mediaElement);

                subCommenContainer.prepend(commentElement);
            });
            //添加Class属性
            commentId.toggleClass("in");
            $(that).toggleClass("active");
        });
    }

}