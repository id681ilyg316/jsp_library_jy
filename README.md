## 本项目实现的最终作用是基于JSP图书借阅管理系统
## 分为2个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 图书信息增删改查
 - 图书借阅审核
 - 图书借阅统计
 - 图书类别增删改查
 - 用户增删改查
 - 管理员登录
 - 还书审核
### 第2个角色为用户角色，实现了如下功能：
 - 借阅后显示审核中
 - 图书馆首页
 - 用户主页
 - 用户登录
## 数据库设计如下：
# 数据库设计文档

**数据库名：** library_jy

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [book](#book) | 图书信息表 |
| [bookadmin](#bookadmin) |  |
| [bookcase](#bookcase) |  |
| [borrow](#borrow) |  |
| [reader](#reader) |  |
| [returnbook](#returnbook) |  |

**表名：** <a id="book">book</a>

**说明：** 图书信息表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  3   | author |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 作者  |
|  4   | publish |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | pages |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  6   | price |   float   | 11 |   2    |    Y     |  N   |   NULL    | 价格  |
|  7   | bookcaseid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | abled |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="bookadmin">bookadmin</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | username |   varchar   | 15 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 15 |   0    |    Y     |  N   |   NULL    | 密码  |

**表名：** <a id="bookcase">bookcase</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增主键  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |

**表名：** <a id="borrow">borrow</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | bookid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | readerid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  4   | borrowtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | returntime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | adminid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | state |   int   | 10 |   0    |    Y     |  N   |   NULL    | 状态  |

**表名：** <a id="reader">reader</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增主键  |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  5   | tel |   varchar   | 11 |   0    |    Y     |  N   |   NULL    | 电话  |
|  6   | cardid |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | gender |   varchar   | 1 |   0    |    Y     |  N   |   NULL    | 性别  |

**表名：** <a id="returnbook">returnbook</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | bookid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | readerid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  4   | returntime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | adminid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  6   | state |   int   | 10 |   0    |    Y     |  N   |   NULL    | 状态  |

**运行不出来可以微信 javape 我的公众号：源码码头**
