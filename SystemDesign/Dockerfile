From maven As builder

WORKDIR '/app'
COPY . .
RUN  mvn clean install -f pom.xml

From  openjdk:19
COPY --from=builder /app/target/SystemDesign-0.0.1.jar /usr/src/SystemDesign.jar
EXPOSE 8080


CMD ["java","-jar","/usr/src/SystemDesign.jar"]