c
# 자바 이미지
FROM amazoncorretto:17

WORKDIR /app

COPY ./ /app/

ENV APP_NAME=discodeit
ENV PROJECT_VERSION=1.2-M8
ENV JVM_OPTS=""

EXPOSE 80

RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

ENV APP_NAME=discodeit
ENV PROJECT_VERSION=1.2-M8
ENV JVM_OPTS=""

ENTRYPOINT ["sh", "-c", "java ${JVM_OPTS} -jar build/libs/${APP_NAME}-${PROJECT_VERSION}.jar"]
