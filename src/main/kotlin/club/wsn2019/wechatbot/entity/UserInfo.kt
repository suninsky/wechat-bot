package club.wsn2019.wechatbot.entity

import org.springframework.data.annotation.Id

/**
 * @author suninsky
 */
data class User(
    @Id val id: String,
    val name: String = "",
    /**
     * contactName to contactId
     */
    val contacts: MutableMap<String, String> = HashMap(),
    /**
     * roomName to roomId
     */
    val rooms: MutableMap<String, String> = HashMap()
)
