FROM clojure:lein-2.8.1 as build

WORKDIR /app

COPY project.clj /app
RUN lein deps

COPY . /app

RUN lein uberjar

CMD ["java", "-jar", "/app/target/uberjar/app-standalone.jar"]
