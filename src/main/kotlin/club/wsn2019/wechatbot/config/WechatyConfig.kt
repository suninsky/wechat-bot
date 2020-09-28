package club.wsn2019.wechatbot.config

import club.wsn2019.wechatbot.entity.User
import club.wsn2019.wechatbot.utils.CommandUtils
import club.wsn2019.wechatbot.utils.ServerChanUtils
import io.github.wechaty.*
import io.github.wechaty.eventEmitter.Listener
import io.github.wechaty.io.github.wechaty.schemas.EventEnum
import io.github.wechaty.schemas.ContactQueryFilter
import io.github.wechaty.schemas.RoomQueryFilter
import io.github.wechaty.schemas.ScanStatus
import io.github.wechaty.user.*
import io.github.wechaty.utils.QrcodeUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import java.util.*

@EnableConfigurationProperties(WechatBotProperties::class)
@Configuration
open class WechatyConfig(
    private val wechatBotProperties: WechatBotProperties,
    private val serverChanUtils: ServerChanUtils,
    private val mongoTemplate: MongoTemplate
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    open fun wechaty(): Wechaty {
        val bot = Wechaty.instance(wechatBotProperties.wechatyToken)
        with(bot) {
            onScan(object : ScanListener {
                override fun handler(qrcode: String?, statusScanStatus: ScanStatus, data: String?) {
                    if (qrcode == null || qrcode.isEmpty()) {
                        log.info("二维码获取失败")
                        return
                    }
                    println(qrcode.let { QrcodeUtils.getQr(it) })
                    val url = "https://wechaty.github.io/qrcode/$qrcode"
                    log.info("Online Image: $url")
                    serverChanUtils.push("微信机器人又挂掉啦~", url)
                }
            })
            onMessage(object : MessageListener {
                override fun handler(message: Message) {
                    val from = message.from()
                    val room = message.room()
                    val text = message.text()
                    val id = userSelf().id
                    val user = mongoTemplate.findById(id, User::class.java) ?: User(id)
                    if (room == null) { // shell功能私聊限定
                        from?.let {
                            if (it.name() == "微信团队") { // 忽略微信团队的消息
                                return
                            }
                            user.contacts[from.name()] = id
                            it.say(CommandUtils.run(text))
                        }
                    } else {
                        user.rooms[room.getTopic().get()] = room.id
                    }
                    mongoTemplate.save(user)
                }
            })
            onLogin(object : LoginListener {
                override fun handler(self: ContactSelf) {
                    serverChanUtils.push("登录成功~")
                }
            })
            on(EventEnum.READY, object : Listener {
                override fun handler(vararg any: Any) {
                    log.info("all data ready")
                    serverChanUtils.push("all data ready")
                    val id = userSelf().id
                    val user = mongoTemplate.findById(id, User::class.java) ?: User(id)
                    contactManager.findAll(ContactQueryFilter()).forEach {
                        user.contacts[it.name()] = it.id
                    }
                    roomManager.findAll(RoomQueryFilter()).forEach {
                        user.rooms[it.getTopic().get()] = it.id
                    }
                    mongoTemplate.save(user)
                }
            })

            on(EventEnum.FRIENDSHIP, object : Listener {
                override fun handler(vararg any: Any) {
                    val friendship = any[0] as Friendship
                    friendship.accept()
                    log.info(friendship.hello())
                    log.info(friendship.toJson())
                }
            })

            onRoomTopic(object : RoomTopicListener {
                override fun handler(room: Room, newTopic: String, oldTopic: String, changer: Contact, date: Date) {
                    if (!changer.self()) {
                        room.setTopic("测试群")
                        room.say("不要修改群名", changer)
                    }
                }
            })
            start(false)
        }
        return bot
    }

}

