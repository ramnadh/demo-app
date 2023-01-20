FROM tomcat

#COPY ./ /

RUN rm -rf $CATALINA_HOME/webapps/*
COPY demo-app.war $CATALINA_HOME/webapps/