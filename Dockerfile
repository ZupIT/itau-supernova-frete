FROM 877979957001.dkr.ecr.sa-east-1.amazonaws.com/java-21-aws:alpine-1.0.0

ENV APP_TARGET target
ENV APP itau-supernova-frete.jar

RUN apk add --no-cache curl

RUN addgroup -g 3000 -S appuser && adduser -u 1000 -S appuser -G appuser
RUN mkdir -p /opt && chown -R appuser:appuser /opt
USER appuser

COPY ${APP_TARGET}/${APP} /opt

CMD java ${JAVA_OPTS} -jar /opt/${APP}
