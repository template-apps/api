ARG JRE_IMAGE_URI=amazoncorretto:20.0.2-alpine
FROM $JRE_IMAGE_URI
MAINTAINER Sachin Goyal <sachin.goyal.se@gmail.com>
VOLUME /opt
ARG TARGETPLATFORM
ARG SERVICE=service

ADD build/libs/$SERVICE.jar /opt/service/service.jar

ENTRYPOINT ["java","-jar","/opt/service/service.jar"]
