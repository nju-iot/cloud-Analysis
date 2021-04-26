FROM openjdk:8-jdk-alpine
#基于Java环境
VOLUME /tmp
ADD dataAnalysis.jar dataAnalysis.jar
#上面target/register.jar和你打包的jar包名字一致，第二个register.jar和下面的/register.jar名字保持一致，为了方便我都使用一样的，这条一定要有，不然做的容器开启不了（到处是坑）
ENTRYPOINT ["java","-jar","/dataAnalysis.jar"]