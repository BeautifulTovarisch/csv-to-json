(ns csv-to-json.csv-reader
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [cheshire.core :refer [generate-stream
                                   generate-string] :as json ]))

(defn format-post [documents]
  (json/generate-string { :docs documents }))

(defn read-csv [reader]
  (csv/read-csv reader))

(defn row->map [data]
  (map #(zipmap (first data) %) (rest data)))

(defn rows->json [data]
  (map json/generate-string data))

(defn write-json [dest json]
  (generate-stream json (io/writer dest)))

(defn csv->json
  ([source]
   (csv->json source (clojure.string/replace source #".csv" ".json")))

  ([source dest]
  (with-open [reader (io/reader source)
              writer (io/writer dest)]
    (->> (read-csv reader)
         (row->map)
         (rows->json)
         (format-post)
         (write-json dest)))))
