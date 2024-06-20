# 亚健康管理系统

---

## 项目介绍
这里是中山大学23级人工智能学院的Java大作业项目——亚健康管理系统。

我们打造的亚健康管理系统是一种综合性的健康管理平台，旨在帮助个体识别和改善亚健康状态，进而提升整体健康水平和生活质量。

项目以Java（Spring Boot架构）、html、javascript、css（前端三件套）为核心搭建语言，用Web的形式来展现我们的亚健康管理平台，拥有健康测试、健康评估、健康方案、AI医生等功能，帮助用户了解自身健康情况并提供指导性意见。

[//]: # (可再补充)

---

## 系统功能
+ **主页：亚健康情况介绍**
> + 精美的前端动态网页设计
> + 提供亚健康状态的定义、常见症状和成因分析。
> + 分享亚健康状态的影响和预防措施。
+ **注册界面：**
> + 提供用户友好的注册流程，包括检测密码强度、验证两次输入密码是否一致
> + 合理保护用户的隐私数据
+ **登陆界面：**
> + 采用“用户名+密码”的登录形式，支持用户快速登录查看自己的健康数据
+ **健康测试**

  提供以下维度的测试，帮助用户全面了解自身健康状况：

> + **体质测试**：评估用户的体质类型，并给出相应建议。
> + **心理测试**：分析用户的心理状态，如压力水平和情绪健康等。
> + **风险测试**：识别用户潜在的健康风险因素。

+ **健康评估**

>+ 根据健康测试结果，系统将综合分析并为用户生成一个全方位的健康情况报告。
>+ 该报告包含用户的健康分数，并对用户当前的健康状况给出详细的分析。

+ **健康方案**

>+ 根据用户的健康分数和评估报告，系统将定制一套针对用户当前状况的健康改善方案。
>+ 该方案涵盖饮食、运动、作息、心理、就医等五方面的调整建议。

+ **AI医生**
> + 我们搭建了AI医生，能够依据现有的文献和数据，对用户健康相关的问题做出合理的回答，帮助用户多维度的了解健康相关知识。
> + 更加私人化的服务，能够具体问题具体分析。

+ **健康报告下载**

>用户可以将个人的健康报告以Pdf格式生成，可以供用户下载并保存自己专属的健康管理报告或者在线查看之。

---

## 主要依赖技术

在我们的亚健康管理系统项目中，项目依赖的主要技术及其作用如下：

---

#### **Java Spring Boot**：

Spring Boot 是一个基于 Java 的开放源代码应用程序框架，旨在简化创建从无到有就直接可运行产量水平的基于 Spring 的应用程序。它提供了一种开箱即用的开发方式，避免了很多项目建立和配置的繁琐步骤。在我们的项目中，我们使用了Spring Boot架构作为后端服务的主体框架。

---

#### **HTML, JavaScript, CSS**：

我们的项目前端使用了HTML, JavaScript 和 CSS 三项基本的网页开发语言。HTML 负责网页的基本结构，CSS 提供网页的视觉表现形式，而 JavaScript 则负责网页与用户的互动性。

---

#### **MySQL及H2数据库**：

项目中使用的数据库有MySQL和H2。 MySQL数据库是一种关系型数据库，用来存储项目中的数据并且通过 SQL 语句进行操作；H2 数据库是一种在内存中运行的 Java SQL 数据库，主要用于快速测试和平台兼容性验证。

---

#### **Spring Data JPA**：

Spring Data JPA 是 Spring 提供的一种简化数据存取层（即DAO层）开发的模块，简化了很大部分持久层（即DAO层）的代码。在我们的项目中，我们在 DAO 层使用了 Spring Data JPA 来简化数据库操作。

---

#### **Apache POI**：

Apache POI是一款开源的Java库，主要用于读取和创建 Office Open XML 格式的文件，包括 doc，xls，ppt 等，它给我们在实现 docx 转 pdf 功能的时候提供了很大的便利。

---

## 项目启动说明

**注意（如果你希望在本地运行后端代码）：**

---

### 如果你希望使用MySQL数据库（或者你不希望下载MySQL）

---

在 `application.yml` 中的 `MYSQL_USERNAME` 和 `MYSQL_PASSWORD` 是 `MySQL` 数据库的账号和密码，它们需要存储在本地环境变量中。

__请确保你已设置了名为 `MYSQL_USERNAME` 和 `MYSQL_PASSWORD` 的环境变量，并且它们的值对应了你的 `MySQL` 数据库账号和密码。__

如果你没有设置这些环境变量，或者你想直接在配置文件中写入你的账号和密码，你也可以直接修改 `application.yml` 文件的 `${MYSQL_USERNAME}` 和 `${MYSQL_PASSWORD}` 的值，使它们对应你自己的 `MySQL` 账号和密码。

此外，此项目依赖于MySQL数据库。在开始使用本项目之前，请确保你已经在你的本地环境中安装并配置好了MySQL数据库。如果你尚未安装MySQL数据库，你可以访问[MySQL官方网站](https://www.mysql.com)下载并安装。在安装过程中，请记住你的MySQL账号和密码，因为你需要在后续的步骤中使用它们。

---

### 如果你希望使用H2数据库

请前往`application.yml`文件（位于`src/main/resources`目录）

你将会看到如下代码

```application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/myapp?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
    username: ${MYSQL_USERNAME}
    password: ${MYSQL_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

#spring:
#  datasource:
#    url: jdbc:h2:mem:testdb
#    driverClassName: org.h2.Driver
#    username: sa
#    password: password
#  jpa:
#    database-platform: org.hibernate.dialect.H2Dialect
```

将它改成

```application.yml
#spring:
#  datasource:
#    url: jdbc:mysql://localhost:3306/myapp?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
#    username: ${MYSQL_USERNAME}
#    password: ${MYSQL_PASSWORD}
#  jpa:
#    hibernate:
#      ddl-auto: update
#    show-sql: true

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
```

即切换为H2数据库

---

如果以上步骤没有正确执行，项目将无法正常启动。

---

---

### 项目正常启动后可能遇到的问题

---

**关于docx转pdf功能：**

此项目的某些功能依赖于LibreOffice软件，特别是docx文件转pdf文件的功能。（请查看`DocxToPdfService.java`文件，位于`/src/main/java/com/app/Service`目录下）

在开始使用本项目之前，请确保你已经在你的本地环境中安装并配置好了LibreOffice。如果你尚未安装LibreOffice，你可以访问[LibreOffice官方网站](https://www.libreoffice.org)下载并安装。

此外，你需要设置一个名为`SOFFICE_HOME`的环境变量，其值为你的LibreOffice的安装目录。你可以在你的操作系统的环境变量设置中添加这个环境变量，或者你也可以在你的IDE的运行配置中（Spring Boot的运行配置中）设置这个环境变量。

当然，你也可以像上面一样，直接把`DocxToPdfService.java`中的`soffice`变量设置为到你的LibreOffice的路径。

请不要忽略这一点，否则关于docx转pdf的功能将无法使用（对其他功能无影响）

---

---

### 如何正常使用AI咨询功能

---

由于调用AI咨询的相关API需要一定费用，为了防止浪费，测试阶段若使用AI咨询功能默认调用测试函数，AI回复的结果为固定的测试文本

如果你希望使用正常的AI咨询功能，只需要去`application.yml`文件中，将`active: test`改成`active: prod`，即可正常使用

前提是你需要一个[coze](https://www.coze.com)的账号以及你自己的token令牌，并将运行时的环境变量TOKEN与BOT_ID设置为自己的token与bot_id