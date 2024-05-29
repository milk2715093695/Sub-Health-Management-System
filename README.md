# 亚健康管理系统

**注意：**

在 `application.yml` 中的 `MYSQL_USERNAME` 和 `MYSQL_PASSWORD` 是 `MySQL` 数据库的账号和密码，它们需要存储在本地环境变量中。

__请确保你已设置了名为 `MYSQL_USERNAME` 和 `MYSQL_PASSWORD` 的环境变量，并且它们的值对应了你的 `MySQL` 数据库账号和密码。__

如果你没有设置这些环境变量，或者你想直接在配置文件中写入你的账号和密码，你也可以直接修改 `application.yml` 文件的 `MYSQL_USERNAME` 和 `MYSQL_PASSWORD` 的值，使它们对应你自己的 `MySQL` 账号和密码。

此外，此项目依赖于MySQL数据库。在开始使用本项目之前，请确保你已经在你的本地环境中安装并配置好了MySQL数据库。如果你尚未安装MySQL数据库，你可以访问[MySQL官方网站](https://www.mysql.com)下载并安装。在安装过程中，请记住你的MySQL账号和密码，因为你需要在后续的步骤中使用它们。

如果以上步骤没有正确执行，项目将无法正常启动。