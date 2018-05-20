#!/bin/sh

echo "Start application.."
java -jar -XX:MaxMetaspaceSize=128m  --spring.config.location=classpath:/applitcation.properties,classpath:/production.properties -Xmx1024m target/job-1.0-SNAPSHOT.jar