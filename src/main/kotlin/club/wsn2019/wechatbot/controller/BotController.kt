package club.wsn2019.wechatbot.controller

import club.wsn2019.wechatbot.entity.FailureResult
import club.wsn2019.wechatbot.entity.IResult
import club.wsn2019.wechatbot.entity.Message
import club.wsn2019.wechatbot.entity.SuccessResult
import club.wsn2019.wechatbot.service.BotService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BotController(
    private val botService: BotService
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/sendMessage")
    fun sendMessage(@RequestBody message: Message): IResult {

        return try {
            botService.send(message)
            SuccessResult()
        } catch (e: Exception) {
            log.error("", e)
            FailureResult(e.message)
        }
    }

    @PostMapping("/createRoom")
    fun createRoom(@RequestBody roomName: String): IResult {
        return try {
            //botService
            SuccessResult()
        } catch (e: Exception) {
            FailureResult(e.message)
        }
    }

    @PostMapping("/deleteRoom")
    fun deleteRoom(@RequestBody roomName: String): IResult {
        return try {
            //botService.send(message)
            SuccessResult()
        } catch (e: Exception) {
            FailureResult(e.message)
        }
    }


}