(ns om-tut.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            ;; [bootstrap-cljs :as bs :include-macros true]
            ;; [om-bootstrap.button :as b]
            [om-tut.navbar :refer [navbar-view]]
            [om-tut.support :refer [support]]))
;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world"}))

(om/root
 (fn [data _]
   (reify
     om/IRender
     (render [_]
       (support))))
 app-state
 {:target (. js/document (getElementById "app"))})

#_(defn stripe [text bgc]
    (let [st #js {:backgroundColor bgc}]
      (dom/li #js {:style st} text)))
