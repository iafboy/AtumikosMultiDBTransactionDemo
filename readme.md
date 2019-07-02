# 主要实现atomikos数据库分布式事物。
## atomikos介绍
Atomikos TransactionsEssentials 是一个为Java平台提供增值服务的并且开源类事务管理器，它是一个非常快速的嵌入式事务管理器，这就意味着，不需要另外启动一个单独的事务管理器进程
开源版本中的一些功能：

l.全面崩溃 / 重启恢复

2.兼容标准的SUN公司JTA API

3.嵌套事务

4.为XA和非XA提供内置的JDBC适配器

## 准备工作
application.properties里配置，使用的是oracle数据库，这里需要建立两个库，然后在两个库里面分IE执行users.sql创建user表
## 测试
启动App.java之后，在postman里访问IndexController里的接口
## 接口列表
localhost:8082/insertDB1?name=xxxx&age=xxx    此为向数据库1写数据

localhost:8082/insertDB2？name=xxxx&age=xxx    此为向数据库2写数据

localhost:8082/insertTwoDBs?name=xxxx&age=xxx    此为同时写入数据

localhost:8082//insertTwoDBsWithError?name=xxxx&age=xxx   此接口为模拟写入有问题两个库同时回退数据（其中一个写库会发生异常）

## druid说明
在浏览器里直接输入：
http://localhost:8082/druid/index.html 可以打开druid的监控页面

但是由于本项目使用了atomikos的DataSource，所以这个druid的监控数据基本不能用，因为基本监控不到。