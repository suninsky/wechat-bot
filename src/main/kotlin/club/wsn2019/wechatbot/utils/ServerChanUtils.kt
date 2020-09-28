package club.wsn2019.wechatbot.utils

import club.wsn2019.wechatbot.config.WechatBotProperties
import org.springframework.stereotype.Component


@Component
class ServerChanUtils(
    private val wechatBotProperties: WechatBotProperties
) {

    fun push(text: String, desp: String) {
        for (token in wechatBotProperties.serverChanTokens) {
            HttpUtils.getForResponse("https://sc.ftqq.com/${token}.send?text=$text")
        }
    }

    fun push(text: String) {
        push(text, "")
    }

}

