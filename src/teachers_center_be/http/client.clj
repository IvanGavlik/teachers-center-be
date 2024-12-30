(ns teachers-center-be.http.client
  (:require [clj-http.client :as http]
            [cheshire.core :as json])
  )

(defn post [url key payload]
  (:body (http/post url
                    {:headers {"Content-Type" "application/json"
                               "Authorization" (str "Bearer " key)}
                     :body (json/generate-string payload)
                     :as :json}))
  )

