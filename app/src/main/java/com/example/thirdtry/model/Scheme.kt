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
        val list = typ.split(" ")
        val stringBuffer = StringBuffer()
        stringBuffer.append("经过后台分析，您的症状为：")
        for(i in list){
            when (i){
                "sqz" -> stringBuffer.append("湿气重" + " ")
                "poor_sleep" -> stringBuffer.append("睡眠质量差" + " ")
                "low_dkl" -> stringBuffer.append("抵抗力低下" + " ")
                "little_hair" -> stringBuffer.append("脱发" + " ")
            }
        }
        return stringBuffer.toString().trim()
    }
}