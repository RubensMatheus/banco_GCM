# Etapa 1: Build da aplicação
FROM maven:3.8.4-openjdk-17 AS build

# Diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo pom.xml e o código-fonte para o contêiner
COPY pom.xml .
COPY src ./src

# Executa o build da aplicação usando Maven
RUN mvn clean package
#-DskipTests

# Etapa 2: Imagem final
FROM openjdk:17-jdk-slim

# Diretório de trabalho no contêiner
WORKDIR /app

# Copia o jar gerado na etapa de build para o contêiner
COPY --from=build /app/target/testeDoker-0.0.1-SNAPSHOT.jar /app/your-application.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "your-application.jar"]
