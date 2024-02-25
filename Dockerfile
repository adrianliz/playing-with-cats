FROM amazoncorretto:21-alpine as builder
WORKDIR /app
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties .
COPY gradlew .
COPY gradle ./gradle
COPY src ./src
RUN ./gradlew bootJar

FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
CMD java -Dnetworkaddress.cache.ttl=60 -Dfile.encoding=UTF-8 -Xmx300m \
         -Xss512k -XX:CICompilerCount=2 -XX:+UseContainerSupport \
         -jar /app/app.jar
