(ns om-tut.navbar (:require [om.core :as om :include-macros true]
                            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defn title [data]
  (dom/h1 nil "logo"))

(defn buttons []
  (dom/div nil
           (dom/button #js {:className "btn btn-default"} "123")
           (dom/button nil "456")
           (dom/hr nil)))

(defn navbar-view [data]
  (dom/div nil
           (dom/div nil (title data))
           (dom/div nil (buttons))))

