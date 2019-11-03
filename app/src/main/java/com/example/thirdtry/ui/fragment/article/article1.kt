package com.example.thirdtry.ui.fragment.article

data class article1(
    var code: Int,
    var message: String,
    var result: Result
)

data class Result(
    var comment: String,
    var down: String,
    var forward: String,
    var header: String,
    var images: String,
    var name: String,
    var passtime: String,
    var sid: String,
    var text: String,
    var thumbnail: String,
    var top_comments_content: Any,
    var top_comments_header: Any,
    var top_comments_name: Any,
    var top_comments_uid: Any,
    var top_comments_voiceuri: Any,
    var type: String,
    var uid: String,
    var up: String,
    var video: String
)
