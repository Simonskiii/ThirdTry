package com.example.thirdtry.model

data class Good(
    var add_time: String,
    var category: Category,
    var click_num: Int,
    var fav_num: Int,
    var goods_brief: String,
    var goods_desc: String,
    var goods_front_image: String,
    var goods_num: Int,
    var goods_sn: String,
    var id: Int,
    var images: List<Image>,
    var is_hot: Boolean,
    var is_new: Boolean,
    var market_price: Double,
    var name: String,
    var ship_free: Boolean,
    var shop_price: Double,
    var sold_num: Int
)

data class Category(
    var add_time: String,
    var category_type: Int,
    var code: String,
    var desc: String,
    var id: Int,
    var is_tab: Boolean,
    var name: String,
    var parent_category: Int,
    var sub_cat: List<Any>
)

data class Image(
    var image: String
)