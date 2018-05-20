#!/bin/sh

echo "11 Start application.."
java -jar  -XX:MaxMetaspaceSize=128m   -Xmx1024m target/job-1.0-SNAPSHOT.jar --spring.config.location=classpath:/application.properties,classpath:/production.properties