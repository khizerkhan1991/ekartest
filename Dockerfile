FROM openjdk:8-jdk

ARG MAVEN_VERSION=3.5.4
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"


VOLUME "$USER_HOME_DIR/.m2"

COPY ./ /code/api

workdir /code/api/


#RUN mvn clean package 

#COPY target/*.jar target/test.jar

#ENTRYPOINT ["java", "-jar", "target/test.jar"]


#RUN  java -jar target/test.jar
#CMD ["java", "-jar", "target/test.jar"]

ENTRYPOINT ["./deploy.sh"]
