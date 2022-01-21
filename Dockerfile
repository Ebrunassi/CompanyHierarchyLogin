FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD target/company-hierarchy-login-0.0.1-SNAPSHOT.jar company-hierarchy-login.jar
EXPOSE 8080
RUN bash -c 'touch /company-hierarchy-login.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/.urandom","-jar","/company-hierarchy-login.jar"]