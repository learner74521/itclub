itclub集成专题（v1.1.0）<font style="color:green" >开发中</font>

> 为程序员指引明路的超级助手

## 一句话简介

一款超好用的百宝箱，让工具赋予思想，为业务提供方案。

## 模块划分

```
itclub 后端                      （模板简介）                  开发进度
- 
itclub-common                     通用工具模块                 100%
 
itclub-framework                  框架核心模块                 开发中
  --
  --itclub-framework-datasource   多数据源
  --itclub-framework-mybatisplus  数据操作
  --itclub-framework-security     安全框架
  --itclub-framework-sharding     数据分表 （二选一）
  --itclub-framework-mycat        数据分表 （二选一）
  --itclub-framework-log          日志打印 
  --itclub-framework-swagger      规范工具   

itclub-main                       项目启动模块                 开发中   
  -- 
  -app                            开发演示
  -demo                           使用演示

itclub-model                      拓展样本模块             
  --   
  --itclub-model-gen              代码生成器                    20%
  --itclub-model-sms              短信服务                      80%
  --itclub-model-oss              阿里云存储                    100%
  --itclub-model-minio            私有云存储                    待开发
  --itclub-model-pay              支付服务                      待开发

itclub-system                     系统模块            
  --
  --itclub-system-sys             系统管理                     待开发
  --itclub-system-oa              办公系统                     待开发
  --itclub-system-cas             认证服务                     待开发
  --itclub-system-permission      权限控制                     待开发
  --itclub-system-monitor         监控管理                     待开发
  --itclub-system-quartz          任务调度                     待开发
  
itclub-data                       数据集成模块                      
  --
  --itclub-data-redis             redis缓存                    90%      
  --itclub-data-mongodb           nosql                       开发中
  --itclub-data-elasticsearch     搜索引擎                     90%           
  --itclub-data-rabbitmq          消息队列                     100%
  --itclub-data-kafka             消息队列                     90%
  --itclub-data-rocketmq          消息队列                     开发中
  --itclub-data-activiti          工作流插件                   100%
  --itclub-data-flowable          工作流插件                   开发中
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