(ns teachers-center-be.lecture.generate-lecture
  (:require
    [teachers-center-be.openapi.openapi :as openapi]
    )
  )

(def new-line-md "\\")

;; params
;; language: string,
;; language-level: string,
;; topic: string,
;; topic-size: number,
;; topic-questions: number,
;; grammar: string,
;; grammar-examples: number,
;; grammar-exercises: number,
;; homework: boolean,
;; discussion: boolean,
;; dictionary: boolean,
;; common-phrases: boolean

;; for the student
(defn generate-text [language, language-level, topic, topic-size, grammar]
  (let [text-request (str " For my class I need a text on the topic " topic ". Text hast to be in the " language " language on the " language-level ". Generate the text in such a way that the includes grammar topic "  grammar ". Text should be around "  topic-size " words. Test hast to have title. ")]
    (str (openapi/chat-with-open-api-system-teacher text-request) new-line-md)
    )
  )

(defn generate-questions-on-text [language, language-level, text, topic-questions, grammar]
  (println "generate-questions-on-text " text)
  (if (> topic-questions 0)
    (let [questions-request (str " For my class I need " topic-questions " tasks on vocabulary from the text " text " . Each task should have at least 5 subtask. Here are instructions for first 3 tasks (other tasks generate on your own)
      First task should be circled whether the statement is true or false, the statements are based on the text " text "
        After that, select potentially new words for the students and create examples in which you show the students how these words are used in the " language  " at language level " language-level " i.e. based on your explanation, the students should conclude what that word means in native language, without you having said the word in native language. So no translation all hast to be in " language " .

      The 2nd task should include simple word recognition.
      The 3rd task, the students themselves must determine which word corresponds.
      The 4th task should require the students to produce something on text topic.
      Each teak should be more complex than the previous one.
      This are the instructions for the first 4 tasks for the rest of the task be creative keep in mind that each task should be more complex than the previous one. Also all tasks should be suitable for " language-level " .
      All has to be in " language ". " )]
      (str (openapi/chat-with-open-api-system-teacher questions-request) new-line-md)
      )
    (str "")
    )
  )

;; TODO sometimes explanations are gennerated in english change this
(defn generate-grammar [language, language-level, text, grammar, grammar-examples]
  (if (> grammar-examples 0)
    (let [grammar-request-examples (str " Make detail explanation of " grammar " for language " language " for student at level " language-level " . It should be stated when it is used, how it is formed. Be sure to note the exceptions and create examples.
  When explaining use creativity and last modern approach to teaching with keeping in mid that it should be suitable for student at " language-level " level . Here is text " text " use it as inspiration for examples and explanations.
  Create at least " grammar-examples " examples for each important thing lets provided text be inspiration for examples. "
                                        )]
      (str (openapi/chat-with-open-api-system-teacher grammar-request-examples) new-line-md)
      )
    (let [grammar-request (str " Make detail explanation of " grammar " for language " language " for student at level " language-level " . It should be stated when it is used, how it is formed and other important things for student at language " language-level " level . Be sure to note the exceptions and create examples.
  When explaining use creativity and last modern approach to teaching with keeping in mid that it should be suitable for student at " language-level " level . Here is text " text " use it as inspiration for examples and explanations.")]
      (str (openapi/chat-with-open-api-system-teacher grammar-request) new-line-md)
      )))

(defn generate-grammar-exercises [language, language-level, text, grammar, grammar-exercises]
  (if (> grammar-exercises 0)
    (let [grammar-exercises-request (str " Here is text " text ". Here is grammar explanation " grammar ". Create " grammar-exercises " task on grammar topic. In the tasks try to cover important grammar cases and use vocabulary from the text.
    Next task should be more complicated and harder than the previous one but all for " language-level " language level. Use " language "
    Make diverse and creative grammar tasks which are adapted to the student's level")]
      (str (openapi/chat-with-open-api-system-teacher grammar-exercises-request) new-line-md)
      )
    (str "")
    ))

(defn generate-homework [language, language-level, text, grammar, homework]
  (if homework
    (let [homework-request (str " Create homework for student of " language " language at " language-level " level. Focus on topic of " text " also have grammar tasks. Grammar topic is " grammar ".
    Homework tasks should be appropriate for student level " language-level " and written in  " language ".")]
      (str (openapi/chat-with-open-api-system-teacher homework-request) new-line-md)
      )
    (str "")
    )
  )

(defn generate-discussion [language, language-level, topic, text, grammar, discussion]
  (if discussion
    (let [discussion-request (str " I would like to have discussion about this text " text " so create questions, so that we can have conversation in " language " language on " language-level " level
    . When comes to grammar focus on this " grammar
                                  ". Questions for discussion write in " language)]
      (str (openapi/chat-with-open-api-system-teacher discussion-request) new-line-md)
      )
    (str "")
    )
  )

(defn generate-dictionary [language, language-level, text dictionary]
  (if dictionary
    (let [dictionary-request (str " From the text " text " make dictionary of nouns, verbs and other important thinks for student at language " language-level ". Put
    all relevant info for nouns like article, plural for verbs conjugation and so on. Use language " language)]
      (str (openapi/chat-with-open-api-system-teacher dictionary-request) new-line-md)
      )
    (str "")
    )
  )

(defn generate-common-phrases [language, language-level, topic, common-phrases]
  (if common-phrases
    (let [common-phrases-request (str " Generate at least 5 (ideally should be more) common phrases on topic of " topic " on language " language " for student on language level " language-level "- Common phrases has to be written in " language) ]
      (str (openapi/chat-with-open-api-system-teacher common-phrases-request) new-line-md)
      )
    (str "")
    )
  )