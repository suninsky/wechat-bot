package club.wsn2019.wechatbot.service

import io.github.wechaty.Wechaty
import io.github.wechaty.user.Contact
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TimedService(
    private val botService: BotService,
    private val fundService: FundService,
    private val wechaty: Wechaty
) {

    @Scheduled(cron = "0 0,30 14 * * ?")
    fun cronTask1() {

    }

    companion object {
        private val log = LoggerFactory.getLogger(Contact::class.java)
    }

}

