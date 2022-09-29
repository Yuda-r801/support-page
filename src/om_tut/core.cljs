(ns om-tut.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            ;; [bootstrap-cljs :as bs :include-macros true]
            ;; [om-bootstrap.button :as b]
            [om-tut.navbar :refer [navbar-view]]
            [om-tut.support :refer [live-support]]
            ))
;; define your app data so that it doesn't get over-written on reload


(defonce app-state (atom {:list ["Lion" "Zebra" "Buffalo" "Antelope"] 
                          :contacts
                          [{:first "Ben" :last "Bitdiddle" :email "benb@mit.edu"}
                           {:first "Alyssa" :middle-initial "P" :last "Hacker" :email "aphacker@mit.edu"}
                           {:first "Eva" :middle "Lu" :last "Ator" :email "eval@mit.edu"}
                           {:first "Louis" :last "Reasoner" :email "prolog@mit.edu"}
                           {:first "Cy" :middle-initial "D" :last "Effect" :email "bugs@mit.edu"}
                           {:first "Lem" :middle-initial "E" :last "Tweakit" :email "morebugs@mit.edu"}]}))
   

(defn middle-name [{:keys [middle middle-initial]}]
  (cond
    middle (str " " middle)
    middle-initial (str " " middle-initial ".")))

(defn display-name [{:keys [first last] :as contact}]
  (str last ", " first (middle-name contact)))

(defn contact-view [contact owner]
  (reify
    om/IRender
    (render [this]
      (dom/li nil (display-name contact)))))


(defn contacts-view [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil
               (dom/h2 nil "Contact list")
               (apply dom/ul nil
                      (om/build-all contact-view (:contacts data)))))))

(defn stripe [text bgc]
  (let [st #js {:backgroundColor bgc}]
    (dom/li #js {:style st} text)))

(om/root
 (fn [data owner]
   (om/component
    (apply dom/ul #js {:className "animals"}
           (map stripe (:list data) (cycle ["#ff0" "#fff"]))))) 
 app-state
 {:target (. js/document (getElementById "app1"))})

(om/root contacts-view app-state
         {:target (. js/document (getElementById "contacts"))})

(om/root
 (fn [data _]
   (reify
     om/IRender
     (render [_]
       (live-support)
       (navbar-view data))))
 app-state
 {:target (. js/document (getElementById "app"))})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
;;(swap! app-state assoc :text "Multiple roots!")
  )