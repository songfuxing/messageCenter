 ## 架构设计：
 - （1）消息-服务器解耦。client端产生消息，server端处理消息，messageCenter用于解耦、缓存、分发消息
 - （2）可扩展。新的消息类型需要创建：message，client，server，并把message类型和server实例作为k-v注册到消息中心，即可使用
 -  (3) 限流设计: 使用令牌桶限制邮件服务器的发送频率

