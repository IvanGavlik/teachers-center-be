(ns teachers-center-be.routes
  (:require
    [compojure.core :as compojure]
    [teachers-center-be.openapi.openapi :as openapi]
    )
  )

(defn plain
  [text]
  {:status  200
   :headers {"Access-Control-Allow-Origin" "*"}
   :body text})

(defn api-key [request]
  (plain @openapi/open-api-key)
  )

(compojure/defroutes routes
                     (compojure/GET "/key" request (api-key request))

                     )
