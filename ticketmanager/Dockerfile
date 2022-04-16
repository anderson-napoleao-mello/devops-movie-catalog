#stage 1
#Start with a base image containing Java runtime
FROM openjdk:11-slim as build
# Add Maintainer Info
LABEL maintainer="Anderson Napoleão <anderson.mello@al.infnet.edu.br>"
# The application's jar file
ARG JAR_FILE_NAME
# Add the application's jar to the container
COPY ${JAR_FILE_NAME} app.jar
#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)
#stage 2
#Same Java runtime
FROM openjdk:11-slim
#Add volume pointing to /tmp
VOLUME /tmp
#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","br.edu.infnet.al.ticketmanager.TicketmanagerApplication"]