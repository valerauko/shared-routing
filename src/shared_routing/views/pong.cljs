(ns shared-routing.views.pong
  (:require [reitit.frontend.easy :refer [href]]
            [shared-routing.routes.web :as-alias web]))

(defn pong
  []
  [:div
   [:p
    "Hello from pong"]
   [:p
    [:a
     {:href (href ::web/home)}
     "Back to top"]]])
