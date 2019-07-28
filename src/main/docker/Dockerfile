FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS=""

ENV SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/fepb_api"
ENV SPRING_DATASOURCE_USERNAME="fepb_api"
ENV SPRING_DATASOURCE_PASSWORD="fepb_api"

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh jhipster
WORKDIR /home/jhipster

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown jhipster:jhipster entrypoint.sh
USER jhipster

ADD *.war app.war

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

