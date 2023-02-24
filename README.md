itclub集成专题（v1.1.0）<font style="color:green" >开发中</font>

> 为程序员指引明路的超级助手

## 一句话简介

一款超好用的百宝箱，让工具赋予思想，为业务提供方案。

## 模块划分

```
itclub 后端                  （版本管理）                 开发进度
- 
itclub-common                     通用模块                    重构中
  日志打印，xss
itclub-framework                  核心模块                    重构中
  多数据源，分库分表，过滤器，拦截器
itclub-main                       启动模块                    重构中    
  -app                            开发演示
  -demo                           使用演示
itclub-model                      拓展模块             
  --   
  --gen                           代码生成                     20%
  --sms                           验证码                       80%
  --oss                           云存储                      100%
  --pay                           支付                         0%

  
itclub-system                     系统模块            
  --
  --web                           web入口                     待开发
  --sys                           系统管理                     待开发
  --oa                            办公系统                     待开发
  --cas                           单点登录                     待开发
  --permission                    权限控制                     待开发
  --monitor                       监控管理                     待开发
  --quartz                        任务调度                     待开发
  
itclub-data                       数据模块                      
  --
  --itclub-data-redis             redis缓存                    90%      
  --itclub-data-mongodb           nosql                       开发中
  --itclub-data-elasticsearch     搜索引擎                      90%           
  --itclub-data-rabbitmq          消息队列                      100%
  --itclub-data-kafka             消息队列                      90%
  --itclub-data-rocketmq          消息队列                      开发中
  
  
itclub web
选型 vue3+elementui
暂未进行设计
```



### itclub-data

#### itclub-data-redis

​	lua脚本保证数据一致性方案

​    击穿问题解决方案，布隆过滤器

@cacheable 添加过期时间规则

#### itclub-data-rabbitmq

三种订阅模式案例

​	延迟队列，死信队列案例

​	消息接收手动确认案例

​    应用场景分析

#### itclub-data-elasticsearch

​    滚动翻页工具