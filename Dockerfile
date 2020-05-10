# start with Tomcat
FROM tomcat:8.5-alpine

VOLUME /tmp

# arg name matches the docker maven plugin buildArgs
ARG WAR_FILE
COPY ${WAR_FILE} /usr/local/tomcat/webapps/app.war
RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/app.war" ]