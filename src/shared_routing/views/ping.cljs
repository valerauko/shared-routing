(ns shared-routing.views.ping
  (:require [reitit.frontend.easy :refer [href]]
            [shared-routing.routes.web :as-alias web]))

(defn ping
  []
  [:div
   [:p
    "Hello from ping"]
   [:p
    [:a
     {:href (href ::web/home)}
     "Back to top"]]])
