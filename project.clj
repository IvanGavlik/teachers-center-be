(defproject teachers-center-be "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring "1.13.0"]
                 [compojure "1.7.1"]
                 [clj-http "3.13.0"]
                 [cheshire "5.11.0"] ;; TODO is this last version
                 ]
  :main teachers-center-be.core
  :aot [teachers-center-be.core]
  :repl-options {:init-ns teachers-center-be.core})
