package com.example.thirdtry.utils

import android.content.Context
import com.example.thirdtry.BASE_URL
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import kotlin.concurrent.thread
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.UnknownHostException


class NetworkUtils {
    companion object {


        private val NETIP = BASE_URL+"g/"

        /**
         * 判断是否有网络连接
         *
         * @param context
         * @return
         */
        fun isNetworkConnected(context: Context?): Boolean {
            if (context != null) {
                val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val mNetworkInfo = mConnectivityManager.activeNetworkInfo
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable
                }
            }
            return false
        }

        /**
         * 判断WIFI网络是否可用
         *
         * @param context
         * @return
         */
        fun isWifiConnected(context: Context?): Boolean {
            if (context != null) {
                val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                if (mWiFiNetworkInfo != null) {
                    return mWiFiNetworkInfo.isAvailable
                }
            }
            return false
        }

        /**
         * 判断MOBILE网络是否可用
         *
         * @param context
         * @return
         */
        fun isMobileConnected(context: Context?): Boolean {
            if (context != null) {
                val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                if (mMobileNetworkInfo != null) {
                    return mMobileNetworkInfo.isAvailable
                }
            }
            return false
        }

        /**
         * 获取当前网络连接的类型信息
         *
         * @param context
         * @return
         */
        fun getConnectedType(context: Context?): Int {
            if (context != null) {
                val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val mNetworkInfo = mConnectivityManager.activeNetworkInfo
                if (mNetworkInfo != null && mNetworkInfo.isAvailable) {
                    return mNetworkInfo.type
                }
            }
            return -1
        }
        fun isInternetAvailable(): Boolean {
            try {
                val address = InetAddress.getByName(NETIP)
                return !address.equals("")
            } catch (e: UnknownHostException) {
                // Log error
            }

            return false
        }
        /**
         * 连接上一个没有外网连接的WiFi
         * 或者有线就会出现极端的情况
         *
         * @return
         */
        fun ping(): Boolean {
            var result: String? = null
            try {
                val ip = NETIP// ping 的地址，可以换成任何一种可靠的外网
                val p = Runtime.getRuntime().exec("ping -c 3 -w 100 $ip")// ping网址3次
                // 读取ping的内容，可以不加
                val input = p.inputStream
                val a = BufferedReader(InputStreamReader(input))
                val stringBuffer = StringBuffer()

                Log.d("------ping-----", "result content : $stringBuffer")
                // ping的状态
                val status = p.waitFor()
                if (status == 0) {
                    result = "success"
                    return true
                } else {
                    result = "failed"
                    return false
                }
            } catch (e: IOException) {
                result = "IOException"
                return false
            } catch (e: InterruptedException) {
                result = "InterruptedException"
                return false
            } finally {
                Log.d("----result---", "result = " + result!!)
            }
        }
    }
}
