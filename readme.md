# 主要实现atomikos数据库分布式事物。
## atomikos介绍
Atomikos TransactionsEssentials 是一个为Java平台提供增值服务的并且开源类事务管理器，它是一个非常快速的嵌入式事务管理器，这就意味着，不需要另外启动一个单独的事务管理器进程

Atomikos也包涵商业软件访问地址为https://www.atomikos.com/

springboot官方文档也提出支持Atomikos，并给出例子，具体请参考https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-jta.html 

开源版本中的一些功能：
l.全面崩溃 / 重启恢复

2.兼容标准的SUN公司JTA API

3.嵌套事务

4.为XA和非XA提供内置的JDBC适配器

## 场景
调用REST API分别向两个DB的user表写入同样的数据，2个服务如果都成功，则分别两个数据库都有数据。否则其中一个出错，则写入两个数据库的数据全部回滚

例子中用docker建立一个数据库，采用两个不同用户名的方式模拟写入两个库（与两个db实例效果相同）,其中API insertTwoDBs为同时写入成功，insertTwoDBsWithError中一个操作会模拟触发异常，然后数据全部回滚

## 准备工作
application.properties里配置，使用的是oracle数据库，这里需要建立两个库，然后在两个库里面分别执行users.sql创建user表

数据库测试可以使用docker版，运行命令为 docker run -d -it --name <oracle-db> store/oracle/database-enterprise:12.2.0.1
使用sqlplus sys/Oradoc_db1@ORCLCDB as sysdba登录db，并创建用户


## 测试
启动App.java之后，在postman里访问IndexController里的接口
## 接口列表
localhost:8082/insertDB1?name=xxxx&age=xxx    此为向数据库1写数据

localhost:8082/insertDB2？name=xxxx&age=xxx    此为向数据库2写数据

localhost:8082/insertTwoDBs?name=xxxx&age=xxx    此为同时写入数据

localhost:8082//insertTwoDBsWithError?name=xxxx&age=xxx   此接口为模拟写入有问题两个库同时回退数据（其中一个写库操作中写了一个i=1/0或i=3/0的操作，此会触发异常）

## druid说明
在浏览器里直接输入：
http://localhost:8082/druid/index.html 可以打开druid的监控页面

但是由于本项目使用了atomikos的DataSource，所以这个druid的监控数据基本不能用，因为基本监控不到。
