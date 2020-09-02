package club.wsn2019.wechatbot.service

import club.wsn2019.wechatbot.entity.Message
import club.wsn2019.wechatbot.entity.MessageType
import club.wsn2019.wechatbot.entity.User
import club.wsn2019.wechatbot.utils.ServerChanUtils
import io.github.wechaty.Wechaty
import io.github.wechaty.schemas.ContactQueryFilter
import io.github.wechaty.schemas.RoomQueryFilter
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

/**
 * @author suninsky
 */
@Service
class BotService(
    private val wechaty: Wechaty,
    private val mongoTemplate: MongoTemplate,
    private val serverChanUtils: ServerChanUtils
) {

    fun send(message: Message) {
        when (message.type) {
            MessageType.PRIVATE -> {
                val me = mongoTemplate.findById(wechaty.userSelf().id, User::class.java)!!
                me.contacts[message.destination]?.also {
                    val contactQueryFilter = ContactQueryFilter()
                    contactQueryFilter.name = message.destination
                    wechaty.contactManager.find(contactQueryFilter)?.id
                }?.let {
                    val user = wechaty.contactManager.load(it)
                    user.say(message.content)
                }
            }

            MessageType.ROOM -> {
                val me = mongoTemplate.findById(wechaty.userSelf().id, User::class.java)!!
                me.rooms[message.destination]?.also {
                    val roomQueryFilter = RoomQueryFilter()
                    roomQueryFilter.topic = message.destination
                    wechaty.roomManager.find(roomQueryFilter)?.id
                }?.let {
                    val room = wechaty.roomManager.load(it)
                    serverChanUtils.push(room.getTopic().get())
                }
                val room = wechaty.roomManager.load("22890895834@chatroom")
                room.say(message.content)
            }
        }
    }

    fun createRoom(roomName: String) {

    }


    fun deleteRoom(roomName: String) {

    }
}