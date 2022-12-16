(ns shared-routing.views
  (:require [re-frame.core :as rf]
            [shared-routing.subs :as subs]
            [shared-routing.views.home :refer [home]]))

(defn main
  []
  (let [{{:keys [view]} :data :as route}
        @(rf/subscribe [::subs/current-route])]
    [:div
     [:h1
      "Shared routing example"]
     [:p
      (str "Route: " (get-in route [:data :name] "[missing]"))]
     (if (fn? view)
       [view]
       [:p
        "Not found"])]))
