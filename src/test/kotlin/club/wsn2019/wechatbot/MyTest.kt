package club.wsn2019.wechatbot

import club.wsn2019.wechatbot.utils.ServerChanUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import javax.annotation.Resource

@RunWith(SpringRunner::class)
@SpringBootTest
@WebAppConfiguration
class MyTest {

    @Resource
    lateinit var serverChanUtils: ServerChanUtils

    @Test
    fun test() {

    }
}