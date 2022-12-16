(ns shared-routing.routes
  (:require [shared-routing.routes.api :as api]
            [shared-routing.routes.web :as web]))

(def routes
  [api/routes
   web/routes])

(defprotocol Expand
  (expand [_ _]))

(defprotocol ByName
  (href [_] [_ _] [_ _ _]))
