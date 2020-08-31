package club.wsn2019.wechatbot.utils

import io.github.wechaty.utils.JsonUtils
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object HttpUtils {

    fun <T> getForObject(url: String, clazz: Class<T>, default: T): T {
        return try {
            val response = getForResponse(url)
            JSONUtils.getObject(response?.body()?.string(), clazz) ?: default
        } catch (e: IOException) {
            default
        }
    }

    fun getForResponse(url: String): Response? {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        return try {
            okHttpClient.newCall(request).execute()
        } catch (e: IOException) {
            null
        }
    }

}

object JSONUtils {

    fun <T> getObject(jsonStr: String?, clazz: Class<T>): T? {
        if (jsonStr == null) return null
        return try {
            JsonUtils.mapper.readValue(jsonStr, clazz)
        } catch (e: IOException) {
            null
        }
    }

}