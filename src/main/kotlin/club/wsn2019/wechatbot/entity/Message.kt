package club.wsn2019.wechatbot.entity

enum class MessageType {
    PRIVATE,
    ROOM
}

data class Message(val type: MessageType, val destination: String, val content: String)

