package club.wsn2019.wechatbot.service

import club.wsn2019.wechatbot.entity.Message
import club.wsn2019.wechatbot.entity.MessageType
import club.wsn2019.wechatbot.entity.User
import club.wsn2019.wechatbot.utils.ServerChanUtils
import io.github.wechaty.Wechaty
import io.github.wechaty.schemas.ContactQueryFilter
import io.github.wechaty.schemas.RoomQueryFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

/**
 * @author suninsky
 */
@Service
class BotService(
    private val wechaty: Wechaty,
    private val mongoTemplate: MongoTemplate
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun send(message: Message) {
        val me = mongoTemplate.findById(wechaty.userSelf().id, User::class.java) ?: throw RuntimeException("机器人尚未初始化")
        when (message.type) {
            MessageType.PRIVATE -> {
                val contactId = me.contacts[message.destination]?.also {
                    val contactQueryFilter = ContactQueryFilter()
                    contactQueryFilter.name = message.destination
                    wechaty.contactManager.find(contactQueryFilter)?.id
                } ?: throw RuntimeException("请先发送一条私聊消息给机器人")
                val user = wechaty.contactManager.load(contactId)
                user.say(message.content)
            }

            MessageType.ROOM -> {
                val roomId = me.rooms[message.destination]?.also {
                    val roomQueryFilter = RoomQueryFilter()
                    roomQueryFilter.topic = message.destination
                    log.info(message.destination)
                    wechaty.roomManager.find(roomQueryFilter)?.id
                } ?: throw RuntimeException("请先发送一条群消息给机器人")
                val room = wechaty.roomManager.load(roomId)
                log.info(room.getTopic().get())
                room.say(message.content)
            }
        }
    }

    fun createRoom(roomName: String) {

    }


    fun deleteRoom(roomName: String) {

    }
}