package club.wsn2019.wechatbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * @author suninsky
 */
@SpringBootApplication
@EnableScheduling
open class WechatBotApplication

fun main(args: Array<String>) {
    runApplication<WechatBotApplication>(*args)
}
