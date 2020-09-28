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
    private List<String> serverChanTokens = new ArrayList<>();
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
    public List<String> getServerChanTokens() {
        return serverChanTokens;
    }

    public void setServerChanTokens(@NotNull List<String> serverChanTokens) {
        this.serverChanTokens = serverChanTokens;
    }

    @NotNull
    public List<String> getIgnoreIds() {
        return ignoreIds;
    }

    public void setIgnoreIds(@NotNull List<String> ignoreIds) {
        this.ignoreIds = ignoreIds;
    }
}
