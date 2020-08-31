package club.wsn2019.wechatbot.entity

import org.springframework.data.annotation.Id

/**
 * @author suninsky
 */
data class User(
    @Id val id: String,
    val name: String,
    val contactList: List<String>,
    val roomList: List<String>
)
