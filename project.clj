(defproject shared-routing "0.1.0-SNAPSHOT"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.brunobonacci/mulog "0.9.0"]
                 [mount "0.1.16"]
                 [aleph "0.6.0"]
                 [metosin/reitit "0.5.18"]]
  :main ^:skip-aot shared-routing.core
  :target-path "target/%s"
  :profiles {:dev {:source-paths ["dev"]
                   :resource-paths ["public"]
                   :dependencies [[org.clojure/tools.namespace "1.3.0"]]}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
