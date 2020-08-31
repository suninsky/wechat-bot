package club.wsn2019.wechatbot.controller

import club.wsn2019.wechatbot.entity.FailureResult
import club.wsn2019.wechatbot.entity.IResult
import club.wsn2019.wechatbot.entity.Message
import club.wsn2019.wechatbot.entity.SuccessResult
import club.wsn2019.wechatbot.service.BotService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BotController(
    private val botService: BotService
) {

    @PostMapping("/sendMessage")
    fun send(@RequestBody message: Message): IResult {
        return try {
            botService.send(message)
            SuccessResult()
        } catch (e: Exception) {
            FailureResult(e.message)
        }
    }
}