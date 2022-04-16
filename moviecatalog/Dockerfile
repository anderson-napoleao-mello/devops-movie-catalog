#stage 1
FROM openjdk:11-slim as phase1
LABEL maintainer="Anderson Napoleão <anderson.mello@al.infnet.edu.br>"
ARG JAR_FILE_NAME
COPY ${JAR_FILE_NAME} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#stage 2
FROM openjdk:11-slim
VOLUME /tmp
ARG DEPENDENCY=/target/dependency
COPY --from=phase1 ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=phase1 ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=phase1 ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","br.edu.infnet.al.moviecatalog.MoviecatalogApplication"]