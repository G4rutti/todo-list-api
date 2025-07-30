# =========================================================================
# ESTÁGIO 1: Build (Construção da Aplicação)
# Usamos uma imagem oficial do Maven que já vem com o Java (JDK) 17.
# O nome "build" é um apelido para este estágio.
# =========================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Define o diretório de trabalho padrão dentro do container.
WORKDIR /app

# Copia apenas o pom.xml primeiro. Esta é uma otimização de cache.
# Se as dependências no pom.xml não mudarem, o Docker reutiliza
# as dependências já baixadas, tornando builds futuros muito mais rápidos.
COPY pom.xml .

# Baixa todas as dependências do projeto e as armazena em cache.
RUN mvn dependency:go-offline

# Agora copia todo o resto do código-fonte do seu projeto.
COPY src ./src

# Executa o comando para compilar o projeto, gerar o pacote .jar
# e pular os testes (pois não são necessários no ambiente de build).
RUN mvn clean install -DskipTests


# =========================================================================
# ESTÁGIO 2: Run (Execução da Aplicação)
# Agora usamos uma imagem muito mais leve, que contém apenas o Java
# para rodar a aplicação (JRE), e não todas as ferramentas de build.
# Isso torna sua imagem final muito menor e mais segura.
# =========================================================================
FROM eclipse-temurin:17-jre-focal

# Define o diretório de trabalho padrão.
WORKDIR /app

# --- ATENÇÃO AQUI! ---
# Copia o arquivo .jar que foi gerado no estágio 'build' para a imagem final.
# Verifique na sua pasta 'target' localmente qual o nome exato do seu .jar
# e ajuste o nome do arquivo na linha abaixo se for diferente.
# LINHA CORRIGIDA (CORRETA PARA O SEU PROJETO)
COPY --from=build /app/target/simplify-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta em que sua aplicação Spring Boot roda.
# O padrão é 8080, que é a porta que o Render e outras plataformas usam.
EXPOSE 8080

# Comando final que será executado quando o container iniciar.
# Ele simplesmente executa o arquivo .jar da sua aplicação.
ENTRYPOINT ["java", "-jar", "app.jar"]