package club.wsn2019.wechatbot.config;


import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suninsky
 */
@ConfigurationProperties(
        prefix = "wechat-bot"
)
public class WechatBotProperties {

    @NotNull
    private String wechatyToken = "";
    @NotNull
    private String serverChanToken = "";
    @NotNull
    private List<String> ignoreIds = new ArrayList<>();

    @NotNull
    public String getWechatyToken() {
        return wechatyToken;
    }

    public void setWechatyToken(@NotNull String wechatyToken) {
        this.wechatyToken = wechatyToken;
    }

    @NotNull
    public String getServerChanToken() {
        return serverChanToken;
    }

    public void setServerChanToken(@NotNull String serverChanToken) {
        this.serverChanToken = serverChanToken;
    }

    @NotNull
    public List<String> getIgnoreIds() {
        return ignoreIds;
    }

    public void setIgnoreIds(@NotNull List<String> ignoreIds) {
        this.ignoreIds = ignoreIds;
    }
}
