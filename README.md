
## 平台简介

        公寓管理系统有比传统管理手段方便、快捷、智能等多种优点，对于现在还没有使用公寓管理系统的物业公司来说可能还是比较迷茫和茫然，
    今天就从功能方向	给大家介绍一下公寓管理系统，公寓的管理手段相对于其他能相对简单，从“人”“房屋”“资金”进行管理。


## 系统模块
~~~
com.yuelan     
├── yuelan-ui              // 前端框架 [80]
├── yuelan-gateway         // 网关模块 [8080]
├── yuelan-auth            // 认证中心 [9200]
├── yuelan-api             // 接口模块
│       └── yuelan-api-system                          // 系统接口
├── yuelan-common          // 通用模块
│       └── yuelan-common-core                         // 核心模块
│       └── yuelan-common-datascope                    // 权限范围
│       └── yuelan-common-datasource                   // 多数据源
│       └── yuelan-common-log                          // 日志记录
│       └── yuelan-common-redis                        // 缓存服务
│       └── yuelan-common-seata                        // 分布式事务
│       └── yuelan-common-security                     // 安全模块
│       └── yuelan-common-swagger                      // 系统接口
├── yuelan-modules         // 业务模块   
│       └── yuelan-system                              // 系统模块 [9201]
│       └── yuelan-apartment                           // 房源管理 [443]
│       └── yuelan-gen                                 // 代码生成 [9202]
│       └── yuelan-job                                 // 定时任务 [9203]
│       └── yuelan-file                                // 文件服务 [9300]
├── yuelan-visual          // 图形化管理模块
│       └── yuelan-visual-monitor                      // 监控中心 [9100]
├──pom.xml                // 公共依赖
~~~

#####  一：公寓管理端的功能主要从“人、房、钱”的维度帮助公寓进行高效管理。

        比如销售管理，可以查看线索来源、销售跟进情况、最终是否成交，了解整个成交周期，并进行分析。所有的租客信息，也能通过租客管理模块，快速了解。
    房态管理可以管理所有的房源，直观了解房源出租状态、出租周期、空置时间、智能硬件连接情况等。以及流水管理、账务管理。
    可以让公寓清晰了解每一笔流水信息，按应收、已收、应付、已付等信息形成科学的账务表格，以及可视化报表，让公寓对自身账务完全掌握。


#### 1、用户管理

1. 房东注册与登录：房东可以注册账号并登录系统。

2. 用户权限管理：不同房东应有不同的操作权限（如超级房东、普通房东等）。

3. 密码找回：提供密码找回功能。


#### 2、房源管理

1. 房源添加：房东可以添加房源信息。

2. 房源编辑：房东可以修改已添加的房源信息。

3. 房源删除：房东可以删除不再出租的房源。

4. 房源查询：房东可以按条件查询房源信息。


#### 3、房租管理

1. 房租添加：房东可以添加房租信息，包括房间类型、面积、价格、位置、图片等。

2. 房租编辑：房东可以修改已添加的房租信息。

3. 房租删除：房东可以删除不再出租的房租。

4. 房租查询：房东可以按条件查询房租信息。


#### 4、房客管理

1. 租户信息录入：房东可以录入租户的基本信息（如姓名、联系方式、身份证号、性别、紧急联系人、入住日期（预定或实际）、房型选择等）。

2. 租户信息查看与编辑：房东可以查看并编辑租户信息。

3. 租户入住与退房管理：记录租户的入住和退房时间。系统可以根据入住和退房时间自动计算租金。


#### 5、租赁合同管理

1. 合同模板管理：房东可以创建、编辑和删除租赁合同模板。

2. 合同生成与签订：基于合同模板和租户信息，自动生成租赁合同并允许房东和租户在线签订。

3. 合同查看与下载：房东和租户都可以查看已签订的合同，并允许下载合同文件。


#### 6、租金管理

1. 租金设置：房东可以为每个房源设置租金金额和缴纳方式。

2. 租金收取记录：记录租金的收取情况，包括已收、未收和逾期等状态。

3. 租金提醒：系统应自动提醒房东和租户即将到来的租金缴纳日期。


#### 7、通知与公告

1. 通知发布：房东可以发布通知或公告，如维修通知、活动通知等。

2. 通知查看：租户可以查看房东发布的通知和公告。


#### 8、统计与报表

1. 房源出租率统计：统计当前房源的出租率。

2. 租金收入统计：统计指定时间段的租金收入情况。

3. 数据报表导出：允许房东导出统计报表为Excel或PDF文件。


#### 9、系统设置

1. 基本设置：设置系统名称、LOGO、联系方式等基本信息。

2. 数据备份与恢复：提供数据备份和恢复功能，确保数据安全。

