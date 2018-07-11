(ns usaspending-etl.csv-reader
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [clojure.data.json :as json]
            [clojure.core.reducers :as r]))

(defn read-csv [reader]
  (csv/read-csv reader))

(defn csv->map [data]
  (r/map #(zipmap (first data) %) (rest data)))

(defn map->json [data]
  (r/map json/write-str data))

(defn write-json [writer json]
  (doseq [line json] (.write writer line)))

(defn csv->json []
  (with-open [reader (io/reader "data/subawards.csv")
              writer (io/writer "data/subawards.json" :append true)]
    (->> (read-csv reader)
         (csv->map)
         (map->json)
         (into [])
         (write-json writer))))
