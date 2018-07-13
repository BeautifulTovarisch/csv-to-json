(ns csv-to-json.csv-reader
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [cheshire.core :refer [generate-stream
                                   generate-string] :as json ]))

;; read-csv :: io/reader -> Sequence
(defn read-csv [reader]
  (csv/read-csv reader))

;; row-to-map :: Sequence -> Sequence( Map )
(defn row-to-map [data]
  (map #(zipmap (first data) %) (rest data)))

;; rows-to-json :: Sequence( Map ) -> Sequence( String )
(defn rows-to-json [data]
  (map json/generate-string data))

;; write-json ( String, Sequence( String ) ) -> BufferedWriter
;; Writes JSON object to disk lazily
(defn write-json [dest json]
  (generate-stream json (io/writer dest)))

(defn csv-to-json
  ([source]
   (csv-to-json source (clojure.string/replace source #".csv" ".json")))

  ([source dest]
  (with-open [reader (io/reader source)
              writer (io/writer dest)]
    (->> (read-csv reader)
         (row-to-map)
         (rows-to-json)
         (write-json dest)))))
