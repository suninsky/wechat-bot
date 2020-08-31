package club.wsn2019.wechatbot.service

import club.wsn2019.wechatbot.entity.Message
import club.wsn2019.wechatbot.entity.MessageType
import io.github.wechaty.Wechaty
import org.springframework.stereotype.Service

/**
 * @author suninsky
 */
@Service
class BotService(
    private val bot: Wechaty
) {

    fun send(message: Message) {
        when (message.type) {
            MessageType.PRIVATE -> {
//                val contactQueryFilter = ContactQueryFilter()
//                contactQueryFilter.name = message.destination
//                val user = bot.contactManager.find(contactQueryFilter)
                val user = bot.contactManager.load("panhao8")
                user.say(message.content)
            }

            MessageType.ROOM -> {
                // 这里用过滤器搜索会有可能搜不到，因为wechaty库的原因
                // https://wechaty.js.org/v/zh/faq#endless-talking-1
                // 2.12提到了这个bug
                // 测试群的群号直接加载
                val room = bot.roomManager.load("22890895834@chatroom")
                room.say(message.content)
            }
        }

    }
}