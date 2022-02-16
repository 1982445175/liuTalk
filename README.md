# liuTalk
下载后修改配置文件中的todi后再Controller中的url中添加入流机器人的webhook的token，将项目使用maven打包，将jar包上传至服务器后在存放jar包的目录下创建start.sh讲一下内容复制到start.sh执行即可
#!/bin/sh
export JAVA_HOME=/home/palo-boxer2/jdk1.8.0_131
export PATH=$JAVA_HOME/bin:$PATH
APPLICATION=/home/palo-boxer2/monitor/liuTalk/liuTalk.jar
JAVA=$JAVA_HOME/bin/java
nohup java -jar $APPLICATION &
