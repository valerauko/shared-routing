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
     ;; i don't like window.history.back kind of back buttons, what if external
     {:href (href ::web/home)}
     "Back to top"]]])
