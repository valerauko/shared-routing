(ns shared-routing.routes.api)

(def routes
  ["/api/"
   ["ping" ::ping]
   ["pong" ::pong]])
