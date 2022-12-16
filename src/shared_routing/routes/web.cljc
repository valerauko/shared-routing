(ns shared-routing.routes.web)

(def routes
  ["/"
   ["" ::home]
   ["ping" ::ping]
   ["pong" ::pong]])
