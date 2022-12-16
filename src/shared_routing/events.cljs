(ns shared-routing.events
  (:require [re-frame.core :as rf]
            [reitit.frontend.controllers :as router]
            [shared-routing.routes :as-alias routes]))

(rf/reg-event-fx
 ::initialize-db
 (fn [_ _]
   {:db {:title "Hello, world!"}}))

(rf/reg-event-db
 ::navigated
 (fn [db [_ new-match]]
   (let [old-match (::routes/current-route db)]
     (let [controllers (router/apply-controllers
                        (:controllers old-match)
                        new-match)]
       (assoc db ::routes/current-route
              (assoc new-match :controllers controllers))))))
