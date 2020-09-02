package club.wsn2019.wechatbot.service

import club.wsn2019.wechatbot.entity.Message
import club.wsn2019.wechatbot.entity.MessageType
import club.wsn2019.wechatbot.entity.User
import io.github.wechaty.Wechaty
import io.github.wechaty.schemas.ContactQueryFilter
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
                // 这里用过滤器搜索会有可能搜不到，因为wechaty库的原因
                // https://wechaty.js.org/v/zh/faq#endless-talking-1
                // 2.12提到了这个bug
                // 测试群的群号直接加载
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