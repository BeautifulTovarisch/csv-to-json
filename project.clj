(defproject usaspending-etl "0.1.0-SNAPSHOT"
  :uberjar-name "app-standalone.jar"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cheshire "5.8.0"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.4"]]
                 
  :main ^:skip-aot csv-to-json.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
