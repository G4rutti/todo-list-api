# Use uma imagem oficial do OpenJDK (Java 17, por exemplo)
FROM eclipse-temurin:17-jdk-alpine

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo jar gerado para dentro do container
COPY target/*.jar app.jar

# Exponha a porta que a aplicação vai rodar
EXPOSE 8080

# Variável de ambiente para Java otimizado (opcional)
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Comando para rodar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
