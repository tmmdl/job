FROM openjdk:8-jdk-alpine

#RUN apk -U add openrc 
#RUN apk -U add haveged 
RUN apk add maven --update-cache --repository http://dl-4.alpinelinux.org/alpine/edge/community/ --allow-untrusted  && rm -rf /var/cache/apk/*

WORKDIR /app
COPY src /app/src
COPY *.sh /app
COPY pom.xml /app/pom.xml
RUN mvn package
RUN touch file1
ENTRYPOINT ["/app/entry.sh"]

