package com.example.thirdtry.model

data class Scheme(
    var commodities: MutableList<Commodity>,
    var tips: MutableList<Tip>,
    var todo: MutableList<Todo>
)

data class Commodity(
    var goods_desc: String,
    var goods_front_image: String,
    var id: Int,
    var name: String
)

data class Tip(
    var content: String,
    var id: Int,
    var typ: String
)

class Todo(
    var content: String,
    var id: Int,
    var typ: String
) {
    fun type(typ: String): String {
        return when (typ) {
            "sqz" -> "经过后台分析，您的症状是湿气重"
            "little_hair" -> "经过后台分析，您的症状是脱发"
            "poor_sleep" -> "经过后台分析，您的症状是睡眠质量差"
            "low_dkl" -> "经过后台分析，您的症状是免疫力低下"

            else -> "经过后台分析，您的症状我也不知道"
        }
    }
}