FROM tomcat as base
WORKDIR /src
EXPOSE 8080

FROM base AS build
COPY ./WebContent ./src
WORKDIR ./src
RUN jar cvf demo-app.war *

FROM build AS final
RUN cp demo-app.war $CATALINA_HOME/webapps/
