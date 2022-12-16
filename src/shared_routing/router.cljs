(ns shared-routing.router
  (:require [re-frame.core :as rf]
            [reitit.core]
            [reitit.frontend.easy :as easy]
            [shared-routing.events :as events]
            [shared-routing.routes :as routes :refer [expand href routes]]
            [shared-routing.routes.api :as-alias api]
            [shared-routing.routes.web :as-alias web]
            [shared-routing.views.home :refer [home]]
            [shared-routing.views.ping :refer [ping]]
            [shared-routing.views.pong :refer [pong]]))

(extend-protocol routes/ByName
  Keyword
  (href
   ([route]
    ;; this gets called with the correct keyword value
    (href route nil nil))
   ([route params]
    ;; but this never gets called
    (href route params nil))
   ([route params query]
    ;; nor this
    (easy/href route params query))))

(defn route-defs
  [route-name]
  (case route-name
    ::web/home
    {:view #'home}

    ::web/ping
    {:view #'ping}

    ::web/pong
    {:view #'pong}

    ;; else
    nil))

(extend-protocol routes/Expand
  Keyword
  (expand
   [route-name opts]
   ; this never gets called for some reason
   (expand {:name route-name} opts))

  PersistentArrayMap
  (expand
   [{route-name :name :as route} opts]
   ; (js/console.log "Route:" (clj->js route))
   ; (js/console.log "Opts:" (clj->js opts))
   (conj route (route-defs route-name)))

  PersistentHashMap
  (expand
   [{route-name :name :as route} _]
   (conj route (route-defs route-name))))

(defn build-router
  []
  (reitit.frontend/router
   routes
   ; if i use a local anon function i can confirm it's passed
   ; the correct arguments
   ; {:expand (fn [data opts]
   ;            ;; here data is still a keyword
   ;            (js/console.log "Raw data" (clj->js data))
   ;            (js/console.log "Raw opts" (clj->js opts))
   ;            ;; yet here when i pass it to the protocol it's somehow "lost"
   ;            (expand data opts))}
   {:expand expand}))

(defn on-navigate
  [new-match]
  (when new-match
    (rf/dispatch [::events/navigated new-match])))

(defn start-router
  []
  (easy/start!
   (build-router)
   on-navigate
   {:use-fragment false}))
