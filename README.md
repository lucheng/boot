

###boot项目为一个SpringCloud框架的demo

####目录####

- 版本信息

- 模块信息

- 环境变量

####版本信息####

- Spring Boot 版本： 2.1.4.RELEASE
- Spring Cloud 版本：Greenwich.RELEASE  #格林尼治 - 英国伦敦格林尼治区，位于伦敦东南、泰晤士河南岸#

####模块信息####

- eureka-server 为注册中心
- config-server为配置中心


####设置环境变量####

LOG_HOME:日志根目录

####注意点####
- Feign 启用hystrix支持
  feign.hystrix.enabled = true