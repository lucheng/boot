

###boot项目为一个SpringCloud框架的demo

####目录####

1. 版本信息

- 模块信息

- 环境变量

####版本信息####

1. Spring Boot 版本： 2.1.4.RELEASE
1. Spring Boot Admin 服务监控
1. Spring Boot Cache 缓存组件
1. Spring Cloud 版本：Greenwich.RELEASE  #格林尼治 - 英国伦敦格林尼治区，位于伦敦东南、泰晤士河南岸#
1. Spring Cloud Ribbon 负载均衡
1. Spring Cloud Hystrix 熔断器  HystrixDashboard 熔断器指示板
1. Spring Cloud Turbine Turbine 聚合监控
1. CacheCloud    缓存监控 
1. Spring Cloud Gateway  api网关

####模块信息####

- eureka-server 注册中心
- config-server 配置中心
- monitor       监控模块
    - monitor-admin        spring boot admin 服务监控
    - monitor-turbine      spring cloud turbine Hystrix聚合监控
- trace         跟踪
    - trace-zipkin-server
    - trace-zipkin-server-stream

####设置环境变量####

LOG_HOME:日志根目录

####注意点####
- Feign 启用hystrix支持
  feign.hystrix.enabled = true


####待完成####
- Spring Cloud Sleuth
- Shardingsphere 读写分离
- Auth2.0 权限支持
- Docker 容器管理
 