(ns teachers-center-be.core
  (:require
    [ring.adapter.jetty :as jetty]
    [ring.middleware.params :as params]
    [ring.middleware.multipart-params :as form-params]
    [ring.middleware.json :as json]
    [teachers-center-be.routes :as routes]
    [teachers-center-be.openapi.openapi :as openapi]
    )
  (:gen-class)
  )

;; small example
(comment

  (defn handler [request]
  {:status 200
   :headers {"Content-Type" "test/html"}
   :body "Hello World"
   }
  )

(defn wrap-content-type [handler content-type]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Type"] content-type)
      )
    )
  )

(compojure/defroutes app
                     (compojure/GET "/" request (wrap-content-type handler "text/plain")))


  (defn -main [& args]
    "I don't do a whole lot."
    (jetty/run-jetty app {:port 3000
                          :join? false})
    )
  )

(def app (-> routes/routes
             params/wrap-params
             form-params/wrap-multipart-params
             json/wrap-json-body
             ))

(defn -main [& args]
  "I don't do a whole lot."
  (jetty/run-jetty app {:port 3000
                      :join? false})
  (println "Welcome to teachers center! ")
  (swap! openapi/open-api-key (fn [_] (first args)))
  (println "key " @openapi/open-api-key)
  )
