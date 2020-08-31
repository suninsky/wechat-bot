package club.wsn2019.wechatbot.utils

import club.wsn2019.wechatbot.config.WechatBotProperties
import org.springframework.stereotype.Component
import java.net.URL


@Component
class ServerChanUtils(
    private val wechatBotProperties: WechatBotProperties
) {

    fun push(text: String, desp: String) {
        HttpUtils.getForResponse("https://sc.ftqq.com/${wechatBotProperties.serverChanToken}.send?text=$text")
    }

    fun push(text: String) {
        push(text,"")
    }

}

