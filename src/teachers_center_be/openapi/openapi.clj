(ns teachers-center-be.openapi.openapi
  (:require
    [teachers-center-be.http.client :as client]
    )
  )

(def url (str "https://api.openai.com/v1/chat/completions"))
(def open-api-key (atom ""))

(def system-role "system") ;; Foreign language teacher
(def system-content  "You are foreign language teacher. You have to prepare materials for you next class.
 You will be provided with points, and your task is to used them to create lecture.") ;;  Lecture is for ' + this.language + ' language ' + this.languageLevel + ' level.

(defn chat-with-open-api-system-teacher [content]
  (let [response (client/post url, @open-api-key,
                              {:model "gpt-4o-mini"
                               :messages
                               [{:role system-role
                                 :content system-content}
                                {:role "user"
                                 :content content}]
                               }
                              )]
    (println "request to open api ")
    (-> response
        :choices
        first
        :message
        :content)
    )
  )