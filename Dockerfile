#Define da imagem do maven na versão 3.9.3 como inicio do processo de compitalação
FROM maven:3.9.3 AS build
#Copia os arquivos do diretório /src para dentro do container no diretório /app/src
COPY /src /app/src
#Copia o arquivo pom.xml para dentro do container no diretório /app
COPY /pom.xml /app
#Compila o projeto usando o arquivo pom.xml dentro do container. Realizar a limpeza do projeto.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

#Define a imagem do OpenJDK, com a tag 17-alpine
FROM openjdk:17-alpine
#Expõe a porta 8080 para container
EXPOSE 8080
#Copia todos os arquivos .jar referenciado no alias de build e coloca no pasta raiz do container
COPY --from=build /app/target/*.jar /app.jar
#responsável por execução do arquivo .jar
ENTRYPOINT ["java","-jar","/app.jar"]

#OBS.
#Necessário ter o docker instalado em seu computador
#Comando usado para criar uma imagem como base nas informações do Dockerfile
#docker build -t miqueias/apilistgame:1.0 .

#Executar a imagem criada expondo a porta 8080
#docker run -p 8080:8080 miqueias/apilistgame:1.0