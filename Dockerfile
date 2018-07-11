FROM clojure:lein-2.8.1 as build

WORKDIR /app

COPY project.clj /app
RUN lein deps

COPY . /app

RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" app-standalone.jar
CMD ["java", "-jar", "app-standalone.jar"]
