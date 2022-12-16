(ns shared-routing.views.home
  (:require ; [shared-routing.routes :refer [href]]
            [reitit.frontend.easy :refer [href]]
            [shared-routing.routes.web :as-alias web]))

(defn home
  []
  [:div
   [:h2
    "Home"]
   [:ul
    [:li
     {:key (gensym)}
     [:a
      {:href (href ::web/ping)}
      "Ping"]]
    [:li
     {:key (gensym)}
     [:a
      {:href (href ::web/pong)}
      "Pong"]]]])
