(ns shared-routing.core
  (:require [reagent.dom]
            [re-frame.core :as rf]
            [shared-routing.events :as events]
            [shared-routing.router :as router]
            [shared-routing.views :as views]))

(defn ^:dev/after-load remount
  []
  (rf/clear-subscription-cache!)
  (let [root-el (js/document.getElementById "app")]
    (reagent.dom/unmount-component-at-node root-el)
    (reagent.dom/render [views/main] root-el)))

(defn ^:export init
  []
  (rf/dispatch-sync [::events/initialize-db])
  (router/start-router)
  (remount))
