(ns shared-routing.core
  (:require [aleph.http :as http]
            [mount.core :refer [defstate start stop]]
            [shared-routing.router :refer [handler]])
  (:import [java.io
            Closeable])
  (:gen-class))

(defstate http-server
  :start
  (http/start-server
   handler
   {:port 3000
    :compression true})
  :stop
  (.close ^Closeable http-server))

(defn -main
  [& _args]
  (start))
