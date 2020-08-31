package club.wsn2019.wechatbot.utils

import club.wsn2019.wechatbot.config.WechatBotProperties
import org.springframework.stereotype.Component
import java.net.URL


@Component
class ServerChanUtils(
    private val wechatBotProperties: WechatBotProperties
) {

    fun push(text: String, desp: String) {
        println("gg: " + wechatBotProperties.serverChanToken)
        URL("https://sc.ftqq.com/$wechatBotProperties.serverChanToken.send?text=$text&desp=$desp").openStream()
    }

    fun push(text: String) {
        URL("https://sc.ftqq.com/$wechatBotProperties.serverChanToken.send?text=$text").openStream()
        println("gg: " + wechatBotProperties.serverChanToken)
    }

}