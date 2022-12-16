(ns shared-routing.router
  (:require [reitit.ring]
            [ring.util.response :refer [resource-response]]
            [shared-routing.routes :as routes :refer [expand routes]]
            [shared-routing.routes.api :as-alias api]
            [shared-routing.routes.web :as-alias web])
  (:import [clojure.lang
            Keyword
            PersistentArrayMap
            PersistentHashMap]))

(defn index-html
  [_]
  (resource-response "index.html"))

(defn route-defs
  [route-name]
  (case route-name
    ::api/ping
    {:get {:handler (constantly {:status 200
                                 :body "ping"})}}

    ::api/pong
    {:get {:handler (constantly {:status 200
                                 :body "pong"})}}

    ;; frontend routes
    {:get {:handler index-html}}))

(extend-protocol routes/Expand
  Keyword
  (expand
   [route-name opts]
   (expand {:name route-name} opts))

  PersistentArrayMap
  (expand
   [{route-name :name :as route} _]
   (conj route (route-defs route-name)))

  PersistentHashMap
  (expand
   [{route-name :name :as route} _]
   (conj route (route-defs route-name))))

(def router
  (reitit.ring/router
   routes
   {:expand expand}))

(def handler
  (reitit.ring/ring-handler
   router
   (reitit.ring/create-default-handler)
   {:inject-router false}))
