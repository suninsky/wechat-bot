package club.wsn2019.wechatbot.utils

import com.fasterxml.jackson.core.JsonParseException

data class FundInfo(
    val fundcode: String = "",
    val name: String = "",
    val jzrq: String = "",
    val dwjz: Double = 0.0,
    val gsz: Double = 0.0,
    val gszzl: Double = 0.0,
    val gztime: String = ""
) {
    fun prettyString(): String {
        if (fundcode == "") return "无该基金信息"
        val sign = if (gszzl > 0) "+" else ""
        return "$fundcode-$name [$sign$gszzl%]"
    }
}


object FundUtils {

    private val emptyFundInfo = FundInfo()

    private val matcher = """jsonpgz\((.+)\);""".toRegex()

    fun query(code: String): FundInfo {
        val url = "http://fundgz.1234567.com.cn/js/$code.js"
        return HttpUtils.getForResponse(url)?.body()?.string()?.let { responseBody ->
            matcher.find(responseBody)?.groupValues?.getOrNull(1)?.let {
                try {
                    JSONUtils.getObject(it, FundInfo::class.java)
                } catch (e: JsonParseException) {
                    emptyFundInfo
                }
            } ?: emptyFundInfo
        } ?: emptyFundInfo
    }
}

