(ns teachers-center-be.routes
  (:require
    [compojure.core :as compojure]
    )
  )

(defn plain
  [text]
  {:status  200
   :headers {"Access-Control-Allow-Origin" "*"}
   :body text})

(defn api-key [request]
  (plain (str "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"))
  )

(compojure/defroutes routes
                     (compojure/GET "/key" request (api-key request)))
