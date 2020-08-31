package club.wsn2019.wechatbot.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

object CommandUtils {
    fun execute(command: String): String {
        return try {
            val process = Runtime.getRuntime().exec(command)
            if (!process.waitFor(5, TimeUnit.SECONDS)) {
                process.destroy()
                return "出错，调用超时或因为调用了交互命令"
            }
            val input = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            var result = ""
            while (input.readLine().also { line = it } != null) {
                result += line + "\n"
            }
            input.close()
            if (result.isBlank()) result = "没有任何输出"
            result
        } catch (e: IOException) {
            e.message!! + "\nusage: command [args]"
        }
    }

    fun run(command: String): String {
        return when {
            command.startsWith("/fund") -> {
                val code = """/fund\s+(\d+)""".toRegex().find(command)?.groupValues?.get(1)
                if (code != null) FundUtils.query(code).prettyString() else "无效的基金代码"
            }
            command.startsWith("/execute") -> {
                execute(command.substring("/execute".length))
            }
            command.startsWith("/help") -> {
                """/fund [基金编号] - 查看基金实时涨跌
                                                        |/execute [命令] - 执行shell命令
                                                        |/help 查看使用说明
                                                    """.trimMargin()
            }
            else -> {
                "/help 查看使用说明"
            }
        }
    }
}