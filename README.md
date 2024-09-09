# 1.招投标管理系统


## 一、系统介绍
招投标管理系统是一个简化的招投标系统，为企业进行招投标活动提供信息化的支持，优化招投标流程，减轻招投标管理人员工作负担，提高工作效力发挥很大作用。
系统分为五大模块：公告管理，投标管理，评标管理，系统管理，系统维护。

招投标的基本流程：

- 第一步：由招标员（招标员由系统管理员分配）发起招标公告；
- 第二步：由投标员（投标员由系统管理员分配）发起投标；
- 第三步：招标员分配若干个评标员用户；
- 第四步：由分配的评标员评标投标单位的投标是否通过，其中有一个评标员评标通过则次招标结束，否则其他评标员可继续评标；
- 第五步：招标员发布中标公告；


系统默认有四个角色：管理员，招标专员，投标专员，评标专员

- 管理员（admin/admin）：拥有最大权限，可进行用户创建，资源管理，权限分配等操作
- 招标专员(zby/zby)：招标员账号由管理员分配，可以发起投标公告
- 投标专员（tby/tby）:投标员账号由管理员分配，可以浏览发布的招标公告，并对有意向的招标活动进行投标
- 评标专员（pby/pby）：评标员账号由管理员分配，可以对已经结束投标的活动进行评标
## 二、角色运行图
### 管理员
![管理员](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/r-1-%E7%AE%A1%E7%90%86%E5%91%98.png)
### 招标专员
![招标专员](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/r-2-%E6%8B%9B%E6%A0%87%E5%91%98.png)
### 投标专员
![投标专员](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/r-3-%E6%8A%95%E6%A0%87%E5%91%98.png)
### 评标专员
![评标专员](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/r-4-%E8%AF%84%E6%A0%87%E5%91%98.png)

## 三、所有功能介绍
### 0.登录
- 登录地址：http://localhost:8080/bid/admin/login
- 账号密码：admin/admin

![登录](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/0-1-%E7%99%BB%E5%BD%95.png)
#### 首页
![首页](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/0-2-%E9%A6%96%E9%A1%B5.png)
#### 修改密码
![修改密码](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/0-4-%E4%BF%AE%E6%94%B9%E5%AF%86%E7%A0%81.png)
#### 修改主题
![修改主题](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/0-5-%E4%BF%AE%E6%94%B9%E4%B8%BB%E9%A2%98.png)

### 1.公告管理
招标员根据项目需要发布招标公告信息。
![公告管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/1-1-%E5%85%AC%E5%91%8A-%E5%88%97%E8%A1%A8.png)
![公告管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/1-1-%E5%85%AC%E5%91%8A-%E5%A2%9E%E5%8A%A0.png)

### 2.投标管理
投标员可查看系统目前所有招标信息，并对自己感兴趣的招标活动进行投标
![投标管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/2-1-%E6%8A%95%E6%A0%87-%E5%88%97%E8%A1%A8.png)
![投标管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/2-1-%E6%8A%95%E6%A0%87-%E7%BC%96%E8%BE%91.png)

### 3.评标管理
评标员对分配给自己的招标活动进行评标
![评标管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/3-1-%E8%AF%84%E6%A0%87-%E5%88%97%E8%A1%A8%20.png)
![评标管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/3-1-%E8%AF%84%E6%A0%87-%E7%BC%96%E8%BE%91.png)

### 4.系统管理
包括两个子模块：菜单管理和字典管理
#### （1）菜单管理
管理员对系统的菜单按钮及对应图标等信息进行管理
![菜单管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/5-2-%E8%8F%9C%E5%8D%95%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![菜单管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/5-2-%E8%8F%9C%E5%8D%95%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

#### （2）字典管理
管理员对系统常量进行管理
![字典管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/5-1-%E5%AD%97%E5%85%B8%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![字典管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/5-1-%E5%AD%97%E5%85%B8%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

### 5.系统维护
系统管理子模块:用户管理，机构管理，角色管理，日志查询。
#### （1）用户管理
管理员对系统的招标员，评标员，投标员进行动态管理
![用户管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/6-1-%E7%94%A8%E6%88%B7-%E5%88%97%E8%A1%A8.png)
![用户管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/6-1-%E7%94%A8%E6%88%B7-%E5%A2%9E%E5%8A%A0.png)

#### （2）角色管理
对角色进行管理，可新增角色，也可变更已有角色所管理的菜单及按钮
![角色管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/6-2-%E8%A7%92%E8%89%B2-%E5%88%97%E8%A1%A8.png)
![角色管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/6-2-%E8%A7%92%E8%89%B2-%E5%A2%9E%E5%8A%A0.png)

#### （3）机构管理
管理员对系统中新增或有表更的机构进行信息维护
![机构管理-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/6-3-%E6%9C%BA%E6%9E%84-%E5%88%97%E8%A1%A8.png)
![机构管理-增加](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/6-3-%E6%9C%BA%E6%9E%84-%E5%A2%9E%E5%8A%A0.png)

#### （4）日志查询
管理员查看系统日志，发现系统异常上报运维人员
![日志查询-列表](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/6-4-%E6%97%A5%E5%BF%97-%E5%88%97%E8%A1%A8.png)

## 四、软件架构

基础环境：
1. JDK:1.8
2. MySQL:5.7
3. Maven:3.0
4. Tomcat:8
使用框架：

1. 视图框架：Spring MVC 4.2
2. ORM框架：MyBatis
3. 数据库连接池：Druid 1.1
4. 安全框架：Apache Shiro 1.4
5. 日志：SLF4J 1.7、Log4j
6. 前端框架：JSP,Layui,ztree,jquery

## 五、安装教程
1. 导入mysql脚本,数据库名称：bid_system
2. 修改数据库配置：

![修改数据](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/0-6-%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE.png)
3. 通过tomcat启动项目

![启动项目](https://raw.githubusercontent.com/doudoutangs/bid_system/2e3df0f26d8a405936ed0673b5a367339ebb9631/%E6%8B%9B%E6%8A%95%E6%A0%87%E7%B3%BB%E7%BB%9F/0-7-tomcat%E9%85%8D%E7%BD%AE.png)
4. 访问：http://localhost:8080/bid/admin/login（账号admin/admin）



## 六、说明
0. QQ:553039957
1. gitcode主页： https://gitcode.com/user/tbb414 (国内推荐，速度快)
2. github主页：https://github.com/doudoutangs
## 七、其他项目
1. [招投标管理系统](https://gitcode.com/tbb414/bid_system/overview)
2. [OA系统](https://gitcode.com/tbb414/oa_system/overview)
3. [薪资管理系统](https://gitcode.com/tbb414/salary_system/overview)
4. [人事管理系统](https://gitcode.com/tbb414/person_system/overview)
5. [绩效考核系统](https://gitcode.com/tbb414/assess_system/overview)
6. [就业管理系统](https://gitcode.com/tbb414/eta_system/overview)
7. [图书管理系统](https://gitcode.com/tbb414/library_system/overview)
