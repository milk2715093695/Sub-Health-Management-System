# 亚健康管理系统

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