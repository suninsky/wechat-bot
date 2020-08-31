## 部署
#### 1. 自行编译
```java
git clone https://github.com/suninsky/wechat-bot.git
```
修改application.properties参数

```java
server.port=8090
wechat-bot.token=YOUR_TOKEN
server-chan.token=YOUR_TOKEN
```
第一个是运行端口，可默认。
第二个是wechaty-hostie的token，必选。
第三个是server-chan的token，可选。

#### 2.  直接使用target中的jar包

```java
java -jar wechat-bot-1.0.jar --wechat-bot.token=YOUR_TOKEN  --server-chan.token=YOUR_TOKEN
```

## 使用

扫码登录机器人以后，即可。私聊操控shell。考虑到安全问题，目前只能操作wechat-bot-1.0.jar所在的目录。以及，不支持交互式的命令。如果不小心输入了交互式的命令，将在5秒后返回。

与此同时，还在逐渐开发一个群机器人。

换句话说，私聊是shell，群聊是群管理机器人。