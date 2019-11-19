package com.example.thirdtry.utils

import com.example.thirdtry.BASE_URL
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class NetworkUtils {
    fun isOnline():Boolean{
        try {
            val url = URL(BASE_URL)
            val stream = url.openStream()
            return true
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }
}