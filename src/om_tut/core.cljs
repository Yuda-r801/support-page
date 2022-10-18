(ns om-tut.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om-tut.support :refer [support]]))

(defonce app-state (atom {:text "Hello world"}))

(om/root
 (fn [data owner]
   (reify
     om/IRender
     (render [_]
       (support data owner))))
 app-state
 {:target (. js/document (getElementById "app"))})
