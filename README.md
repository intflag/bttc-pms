## BTTC-PMS
- 包头师范学院论文指导管理系统（简称BTTC-PMS）,是一个基于SpringBoot + Mybatis的BS架构管理系统。该系统的开发环境为JDK1.8，MySQL5.7，开发工具为IntelliJ IDEA 2018.1.5，使用Maven进行项目构建，使用Git进行版本控制。
- BTTC-PMS运行在基于Centos7的Linux服务器中，采用Nginx做反向代理，同时采用FastDFS作为文件服务器，对上传的论文进行统一管理，由该文件服务器实现访问文件的负载均衡，并且可以对文件进行冗余备份，避免出现由于服务阻塞或文件意外丢失导致的访问失败等现象。
- BTTC-PMS在项目开发初期，针对包头师范学院以及目前国内其他大学，本科生毕业论文管理的真实业务场景，进行了详细的系统设计。该系统开发的目的旨在减轻导师的工作强度、提高导师的批阅效率、降低学生提交论文的复杂程度。
- 系统目前处于内测阶段，欢迎大家进行反馈，我们一定会虚心采纳各位老师和同学们的宝贵意见！
## 项目架构图
![image](https://raw.githubusercontent.com/intflag/bttc-pms/master/images/BTTC-PMS%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84.png)
![image]()
## 运行截图
### 门户首页
![image](https://raw.githubusercontent.com/intflag/bttc-pms/master/images/001-%E9%A6%96%E9%A1%B5.png)
### 公告详情页
![image](https://raw.githubusercontent.com/intflag/bttc-pms/master/images/001-%E8%AF%A6%E6%83%85%E9%A1%B5.png)
### 后台首页
![image](https://raw.githubusercontent.com/intflag/bttc-pms/master/images/002-%E5%90%8E%E5%8F%B0%E9%A6%96%E9%A1%B5.png)
### 师生信息管理
![image](https://raw.githubusercontent.com/intflag/bttc-pms/master/images/003-%E5%B8%88%E7%94%9F%E4%BF%A1%E6%81%AF%E7%AE%A1%E7%90%86%E9%A1%B52.png)
