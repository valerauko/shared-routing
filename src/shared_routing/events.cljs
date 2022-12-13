(ns shared-routing.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-fx
 ::initialize-db
 (fn [_ _]
   {:db {:title "Hello, world!"}}))
