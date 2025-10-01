# 基础镜像：采用 OpenJDK 21 的 slim 版本（体积更小）
FROM openjdk:21-slim

# 设置工作目录 这个目录是在容器内的 需要docker exec
WORKDIR /app

# 复制 Spring Boot 打包后的 Jar 文件到容器中（注意：Jar 文件名需与实际一致）
# 若使用 Maven 打包，默认路径为 target/xxx.jar；Gradle 为 build/libs/xxx.jar
COPY target/webdemo-1.0-SNAPSHOT.jar app.jar

# 暴露应用端口（需与 Spring Boot 配置的 server.port 一致，默认 8080）
EXPOSE 8080

# 启动命令：使用 exec 格式，确保容器退出时能正确传递信号
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# 可选：添加 JVM 优化参数（根据应用需求调整）
# ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/app/app.jar"]

