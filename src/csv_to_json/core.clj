(ns csv-to-json.core
  (:gen-class)
  (:require [csv-to-json.csv-reader :refer [csv-to-json]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (if (first args)
    (do
      (println (str "Processing " (first args) "..."))
      (csv-to-json (first args)))
    (do
      (println "Please enter a file to parse")
      (let [input (read-line)]
        (do (println (str "Processing " input "..."))
            (csv-to-json input))))))
        
          
