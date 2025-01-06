(ns teachers-center-be.routes
  (:require
    [ring.middleware.params :as params]
    [compojure.core :as compojure]
    [ring.util.response :as response]
    [teachers-center-be.openapi.openapi :as openapi]
    [teachers-center-be.lecture.generate-lecture :as lecture]
    )
  )

(defn plain
  [text]
  {:status  200
   :headers {"Access-Control-Allow-Origin" "https://ivangavlik.github.io"}
   :body text})


(def language "language")
(def language-level "language-level")
(def topic "topic")
(def text "text")
(def topic-size "topic-size")
(def grammar "grammar")
(def topic-questions "topic-questions")

;; TODO no need for params
(defn api-key [request]
  (plain @openapi/open-api-key)
  )


(defn generate-lecture-text [request]
  (let [params (get request :params)
        text (lecture/generate-text (get params language) (get params language-level) (get params topic) (get params topic-size) (get params grammar))
        ]
    (plain text)
    )
  )

(defn generate-lecture-text-questions [request]
  (let [params (get request :params)
        text (get (get request :params) text)
        text-questions (lecture/generate-questions-on-text (get params language) (get params language-level) text (Integer/parseInt (get params topic-questions)) (get params grammar))
        ]
    (plain text-questions)
    )
  )

(defn generate-lecture-grammar-explanation [request]
  (let [params (get request :params)
        text (get (get request :params) text)
        grammar-explanation (lecture/generate-grammar (get params language) (get params language-level) text (get params grammar) (Integer/parseInt (get params "grammar-examples")))
        ]
    (plain grammar-explanation)
    )
  )

(defn generate-lecture-grammar-exercises [request]
  (let [params (get request :params)
        text (get (get request :params) text)
        grammar-exercises (lecture/generate-grammar-exercises (get params language) (get params language-level) text (get params grammar) (Integer/parseInt (get params "grammar-exercises")))
        ]
    (plain grammar-exercises)
    )
  )

(defn generate-lecture-homework [request]
  (let [params (get request :params)
        text (get (get request :params) text)
        homework (lecture/generate-homework (get params language) (get params language-level) text (get params grammar) (boolean (Boolean/valueOf (get params "homework"))))
        ]
    (plain homework)
    )
  )

(defn generate-lecture-discussion [request]
  (let [params (get request :params)
        text (get (get request :params) text)
        discussion (lecture/generate-discussion (get params language) (get params language-level) (get params topic) text (get params grammar) (boolean (Boolean/valueOf (get params "discussion"))))
        ]
    (plain discussion)
    )
  )

(defn generate-lecture-dictionary [request]
  (let [params (get request :params)
        text (get (get request :params) text)
        dictionary (lecture/generate-dictionary (get params language) (get params language-level) text (boolean (Boolean/valueOf (get params "dictionary"))))
        ]
    (plain dictionary)
    )
  )

(defn generate-lecture-phrases [request]
  (let [params (get request :params)
        phrases (lecture/generate-common-phrases (get params language) (get params language-level) (get params topic) (boolean (Boolean/valueOf (get params "common-phrases"))))
        ]
    (plain phrases)
    )
  )

(def options-response
  {:status  204
   :headers {"Access-Control-Allow-Origin" "https://ivangavlik.github.io"
             "Access-Control-Allow-Methods" "OPTIONS, POST"
             "Access-Control-Allow-Headers" "Content-Type"
             "Content-Type" "text/plain"}})

(compojure/defroutes routes
                     (compojure/GET "/key" request (api-key request))
                     (compojure/POST "/generate-lecture/lecture-text" request (generate-lecture-text request))
                     (compojure/OPTIONS "/generate-lecture/lecture-text" _
                       (response/response "")
                       options-response)

                     (compojure/POST "/generate-lecture/text-questions" request (generate-lecture-text-questions request))
                     (compojure/OPTIONS "/generate-lecture/text-questions" _
                       (response/response "")
                       options-response)

                     (compojure/POST "/generate-lecture/grammar-explanation" request (generate-lecture-grammar-explanation request))
                     (compojure/OPTIONS "/generate-lecture/grammar-explanation" _
                       (response/response "")
                       options-response)

                     (compojure/POST "/generate-lecture/grammar-exercises" request (generate-lecture-grammar-exercises request))
                     (compojure/POST "/generate-lecture/homework" request (generate-lecture-homework request))
                     (compojure/POST "/generate-lecture/discussion" request (generate-lecture-discussion request))
                     (compojure/POST "/generate-lecture/dictionary" request (generate-lecture-dictionary request))
                     (compojure/POST "/generate-lecture/phrases" request (generate-lecture-phrases request))

                     )
