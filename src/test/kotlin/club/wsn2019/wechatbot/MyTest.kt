package club.wsn2019.wechatbot

import club.wsn2019.wechatbot.config.WechatBotProperties
import club.wsn2019.wechatbot.service.BotService
import club.wsn2019.wechatbot.service.FundService
import io.github.wechaty.Wechaty
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import javax.annotation.Resource

@RunWith(SpringRunner::class)
@SpringBootTest
@WebAppConfiguration
class MyTest {
    @Resource
    private lateinit var mongoTemplate: MongoTemplate

    @Resource
    lateinit var wechaty: Wechaty

    @Resource
    lateinit var fundService: FundService

    @Resource
    lateinit var botService: BotService

    @Resource
    lateinit var wechatBotProperties: WechatBotProperties


    @Test
    fun test() {
//        while (true) {
//            try {
//                wechaty.userSelf()
//                break
//            } catch (e: Exception) {
//
//            }
//        }
//        val me = wechaty.userSelf()
//        val user = User(me.id,
//                me.name(),
//                wechaty.contactManager.findAll(ContactQueryFilter()).map {
//                    contact -> contact.id },
//                wechaty.roomManager.findAll(RoomQueryFilter()).map {
//                    room -> room.id
//                },
//                listOf("008087", "320007"))
//        mongoTemplate.save(user)
//
//        val t = fundService.getFundInfoList("123")
//        println(t)
//        println("OK")
        //botService.send(Message(MessageType.PRIVATE,"",fundService.getFundInfoList("wxid_0jlplewodd1m22")))

        println(wechatBotProperties.wechatyToken)
    }
}