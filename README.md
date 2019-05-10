

###boot项目为一个SpringCloud框架的demo

####目录####

1. 版本信息

- 模块信息

- 环境变量

####版本信息####

1. Spring Boot 版本： 2.1.4.RELEASE
- Spring Boot Admin 服务监控
- Spring Boot Cache 缓存组件
- Spring Cloud 版本：Greenwich.RELEASE  #格林尼治 - 英国伦敦格林尼治区，位于伦敦东南、泰晤士河南岸#
- Spring Cloud Ribbon 负载均衡
- Spring Cloud Hystrix 熔断器  HystrixDashboard 熔断器指示板
- Spring Cloud Turbine Turbine 聚合监控
- CacheCloud    缓存监控 

####模块信息####

- eureka-server 注册中心
- config-server 配置中心
- admin         spring boot admin 服务监控


####设置环境变量####

LOG_HOME:日志根目录

####注意点####
- Feign 启用hystrix支持
  feign.hystrix.enabled = true


####待完成####
- 读写分离
- 权限支持
- 容器管理
 