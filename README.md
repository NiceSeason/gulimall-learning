# 谷粒商城

![](https://img.shields.io/badge/building-passing-green.svg)![GitHub](https://img.shields.io/badge/license-MIT-yellow.svg)![jdk](https://img.shields.io/static/v1?label=oraclejdk&message=8&color=blue)

谷粒商城项目致力于打造一个完整的大型分布式架构电商平台，采用现阶段流行技术来实现，采用前后端分离编写。

## 项目学习资源

- 项目文档

  [分布式基础篇](docs/谷粒商城—分布式基础.md)

  [分布式高级篇](docs/谷粒商城—分布式高级.md)

- 接口文档：https://easydoc.xyz/s/78237135/ZUqEdvA4/hKJTcbfd

- 视频链接：https://www.bilibili.com/video/BV1np4y1C7Yf?from=search&seid=8989733132604162058

## 项目介绍

谷粒商城项目是一套电商项目，包括前台商城系统以及后台管理系统，基于 SpringCloud、SpringCloud Alibaba、MyBatis Plus实现。前台商城系统包括：用户登录、注册、商品搜索、商品详情、购物车、订单、秒杀活动等模块。后台管理系统包括：系统管理、商品系统、优惠营销、库存系统、订单系统、用户系统、内容管理等七大模块。

## 项目演示

### 前台商品系统

#### 首页

![](https://i.loli.net/2021/02/18/HPezsS6oYxJ5yRI.png)

#### 商品检索

![](https://i.loli.net/2021/02/18/ki2AWeMRIxKqlVm.png)

#### 认证

![](https://i.loli.net/2021/02/18/iO2cCXNGvLsptnh.png)

<img src="https://i.loli.net/2021/02/18/OIt4FbsUwqV8gZL.png" style="zoom:38%;" />

#### 商品详情

![](https://i.loli.net/2021/02/18/B8qjpkwcT5Potzh.png)

#### 购物车

![](https://i.loli.net/2021/02/18/uEYqnQ2Kx7WZy1G.png)

#### 结算页

#### ![](https://i.loli.net/2021/02/18/T3mVbdpfqKsrNJC.png)支付

![](https://i.loli.net/2021/02/18/SvP1oUVmrnx8cET.png)

![](https://i.loli.net/2021/02/18/QqWzVtk1ZwvrURl.png)

![](https://i.loli.net/2021/02/18/jfKRiLH4trSpwYe.png)



### 后台管理系统

#### 登录

![](https://i.loli.net/2021/02/18/6KVEbFZMrgnUet3.png)

#### 商品系统

**分类管理**

![](https://i.loli.net/2021/02/18/gsTcQRKDEWnUzIY.png)

**品牌管理**

![](https://i.loli.net/2021/02/18/FCKNfMTs9Pt3oVj.png)**平台属性**

![ss](https://i.loli.net/2021/02/18/vCSnt1KjOyghB57.png)

**商品管理**

![](https://i.loli.net/2021/02/18/H6YkoCVOLQyfBN7.png)

**发布商品**

![](https://i.loli.net/2021/02/18/I1WtlCAb9hHUwky.png)

#### 其他系统

<div>
     <img src="docs/images/Snipaste_2021-02-18_16-58-44.png" style="zoom:40%;" />
    <img src="docs/images/Snipaste_2021-02-18_16-47-40.png" style="zoom:40%;" />
    <img src="docs/images/Snipaste_2021-02-18_16-57-06.png" style="zoom:50%;" />
    <img src="docs/images/Snipaste_2021-02-18_16-57-43.png" style="zoom:50%;" />
    <img src="docs/images/Snipaste_2021-02-18_16-57-58.png" style="zoom:50%;" />
</div>



## 组织结构

```
gulimall
├── gulimall-common -- 工具类及通用代码
├── renren-generator -- 人人开源项目的代码生成器
├── gulimall-auth-server -- 认证中心（社交登录、OAuth2.0）
├── gulimall-cart -- 购物车服务
├── gulimall-coupon -- 优惠卷服务
├── gulimall-gateway -- 统一配置网关
├── gulimall-order -- 订单服务
├── gulimall-product -- 商品服务
├── gulimall-search -- 检索服务
├── gulimall-seckill -- 秒杀服务
├── gulimall-third-party -- 第三方服务（对象存储、短信）
├── gulimall-ware -- 仓储服务
└── gulimall-member -- 会员服务
```

## 技术选型

### 后端技术

|        技术        |           说明           |                      官网                       |
| :----------------: | :----------------------: | :---------------------------------------------: |
|     SpringBoot     |       容器+MVC框架       |     https://spring.io/projects/spring-boot      |
|    SpringCloud     |        微服务架构        |     https://spring.io/projects/spring-cloud     |
| SpringCloudAlibaba |        一系列组件        | https://spring.io/projects/spring-cloud-alibaba |
|    MyBatis-Plus    |         ORM框架          |             https://mp.baomidou.com             |
|  renren-generator  | 人人开源项目的代码生成器 |   https://gitee.com/renrenio/renren-generator   |
|   Elasticsearch    |         搜索引擎         |    https://github.com/elastic/elasticsearch     |
|      RabbitMQ      |         消息队列         |            https://www.rabbitmq.com             |
|   Springsession    |        分布式缓存        |    https://projects.spring.io/spring-session    |
|      Redisson      |         分布式锁         |      https://github.com/redisson/redisson       |
|       Docker       |       应用容器引擎       |             https://www.docker.com              |
|        OSS         |        对象云存储        |  https://github.com/aliyun/aliyun-oss-java-sdk  |

### 前端技术

|   技术    |    说明    |           官网            |
| :-------: | :--------: | :-----------------------: |
|    Vue    |  前端框架  |     https://vuejs.org     |
|  Element  | 前端UI框架 | https://element.eleme.io  |
| thymeleaf |  模板引擎  | https://www.thymeleaf.org |
|  node.js  | 服务端的js |   https://nodejs.org/en   |

## 架构图

### 系统架构图

![](https://i.loli.net/2021/02/18/zMrSWaAfbqYoF4t.png)

### 业务架构图

![](https://i.loli.net/2021/02/18/yBjlqvsCgpVkENc.png)

## 环境搭建

### 开发工具

|     工具      |        说明         |                      官网                       |
| :-----------: | :-----------------: | :---------------------------------------------: |
|     IDEA      |    开发Java程序     |     https://www.jetbrains.com/idea/download     |
| RedisDesktop  | redis客户端连接工具 |        https://redisdesktop.com/download        |
|  SwitchHosts  |    本地host管理     |       https://oldj.github.io/SwitchHosts        |
|    X-shell    |  Linux远程连接工具  | http://www.netsarang.com/download/software.html |
|    Navicat    |   数据库连接工具    |       http://www.formysql.com/xiazai.html       |
| PowerDesigner |   数据库设计工具    |             http://powerdesigner.de             |
|    Postman    |   API接口调试工具   |             https://www.postman.com             |
|    Jmeter     |    性能压测工具     |            https://jmeter.apache.org            |
|    Typora     |   Markdown编辑器    |                https://typora.io                |

### 开发环境

|     工具      | 版本号 |                             下载                             |
| :-----------: | :----: | :----------------------------------------------------------: |
|      JDK      |  1.8   | https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html |
|     Mysql     |  5.7   |                    https://www.mysql.com                     |
|     Redis     | Redis  |                  https://redis.io/download                   |
| Elasticsearch | 7.6.2  |               https://www.elastic.co/downloads               |
|    Kibana     | 7.6.2  |               https://www.elastic.co/cn/kibana               |
|   RabbitMQ    | 3.8.5  |            http://www.rabbitmq.com/download.html             |
|     Nginx     | 1.1.6  |              http://nginx.org/en/download.html               |

注意：以上的除了jdk都是采用docker方式进行安装，详细安装步骤可参考百度!!!

### 搭建步骤

> Windows环境部署

- 修改本机的host文件，映射域名端口至Nginx地址

```
192.168.56.102	gulimall.com
192.168.56.102	search.gulimall.com
192.168.56.102  item.gulimall.com
192.168.56.102  auth.gulimall.com
192.168.56.102  cart.gulimall.com
192.168.56.102  order.gulimall.com
192.168.56.102  member.gulimall.com
192.168.56.102  seckill.gulimall.com
以上ip换成自己Linux的ip地址
```

- 修改Linux中Nginx的配置文件

```shell
1、在nginx.conf中添加负载均衡的配置   
upstream gulimall{
	# 网关的地址
	server 192.168.56.1:88;
}    
2、在gulimall.conf中添加如下配置
server {
	# 监听以下域名地址的80端口
    listen       80;
    server_name  gulimall.com  *.gulimall.com hjl.mynatapp.cc;

    #charset koi8-r;
    #access_log  /var/log/nginx/log/host.access.log  main;

    #配置静态资源分离
    location /static/ {
        root   /usr/share/nginx/html;
    }

    #支付异步回调的一个配置
    location /payed/ {
        proxy_set_header Host order.gulimall.com;        #不让请求头丢失
        proxy_pass http://gulimall;
    }

    location / {
        #root   /usr/share/nginx/html;
        #index  index.html index.htm;
        proxy_set_header Host $host;        #不让请求头丢失
        proxy_pass http://gulimall;
    }
```

或者直接用项目nginx模块替换本机nginx配置目录文件

- 克隆前端项目 `renren-fast-vue` 以 `npm run dev` 方式去运行
- 克隆整个后端项目 `gulimall` ，并导入 IDEA 中完成编译



