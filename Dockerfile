# ==========================================
# Etapa 1: Build da Aplicação
# ==========================================
FROM maven:3.8.8-amazoncorretto-17 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro para aproveitar o cache de dependências do Docker
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine
WORKDIR /app

# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

# Porta padrão
EXPOSE 8080

ENTRYPOINT ["java", "-XX:+UseG1GC", "-jar", "app.jar"]