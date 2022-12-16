(ns shared-routing.subs
  (:require [re-frame.core :as rf]
            [shared-routing.routes :as-alias routes]))

(rf/reg-sub
 ::current-route
 (fn [db]
   (::routes/current-route db)))
