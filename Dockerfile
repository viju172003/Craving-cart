
FROM tomcat:10.1-jdk21-temurin

COPY src/main/webapp/ /usr/local/tomcat/webapps/ROOT/
COPY src/main/java/ /tmp/cravingcart-src/

RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes \
    && find /tmp/cravingcart-src -name "*.java" -print0 \
       | xargs -0 javac -encoding UTF-8 \
         -cp "/usr/local/tomcat/lib/*" \
         -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

EXPOSE 8080
