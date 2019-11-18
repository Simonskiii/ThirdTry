//package com.example.thirdtry.utils
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
//import com.example.thirdtry.App
//import kotlin.reflect.KProperty
//
//class Preference<T>(val name: String, val default: T) {
//
//    private val prefs: SharedPreferences by lazy {
//        App.instance!!.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
//    }
//
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
//        return getSharePreferences(name, default)
//    }
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
//        putSharePreferences(name, value)
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    private fun getSharePreferences(name: String, default: T): T = with(prefs) {
//        val res: Any = when (default) {
//            is Long -> getLong(name, default)
//            is Int -> getInt(name, default)
//            is Boolean -> getBoolean(name, default)
//            is Float -> getFloat(name, default)
//            is String -> getString(name, default)
//            else -> throw IllegalArgumentException("This type of data cannot be saved!")
//        }
//        return res as T
//    }
//
//    @SuppressLint("CommitPrefEdits")
//    private fun putSharePreferences(name: String, value: T) = with(prefs.edit()) {
//        when (value) {
//            is Long -> putLong(name, value)
//            is String -> putString(name, value)
//            is Int -> putInt(name, value)
//            is Boolean -> putBoolean(name, value)
//            is Float -> putFloat(name, value)
//            else -> throw IllegalArgumentException("This type of data cannot be saved!")
//        }.apply()
//    }
//}