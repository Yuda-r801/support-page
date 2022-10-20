(ns audyx.support-page.support
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :as a]))

(def app-data [])

;;;;;;;;;;;;;LIVE SUPPORT TEMPLATE;;;;;;;;;;;;;;

(defn live-support-title []
  (dom/div  #js {:className "col-xs-12"}
            (dom/button #js {:type "button" :className "btn btn-primary"} "123")
            (dom/h2 nil "{{'SUPPORT.ABOUT' | translate}}")
            (dom/p #js {:className "available"}))) ;;"{{countryContents.support_hours [userLanguage] || Object.values (countryContents.support_hours) [0]}}"

(defn live-support-phone []
  (dom/div  #js {:className "ad-bookmark phone border"}
            (dom/i #js {:className "fa fa-phone"} "fa fa-phone")
            (dom/a  #js {:className "contact"} "phone")));;  href= "tel:{{countryContents.support_phone.replaceAll(' ', '').replaceAll('-', '')}}" > {{countryContents.support_phone}}       


(defn live-support-email []
  (dom/div  #js {:className "ad-bookmark email border"}
            (dom/i #js {:className "fa fa-envelope-o"} "fa fa-envelope-o")
            (dom/a  #js {:className "contact"} "email")));;   href= "mailto:{{countryContents.support_email}}" > {{countryContents.support_email}}       


(defn live-support-status-server []
  (dom/div  #js {:className "ad-bookmark status-server"}
            (dom/i #js {:className "fa fa-line-chart"} "fa-line")
            (dom/a  #js {:className "contact"} "server")));;   href= "http://status.audyx.com" target= "_blank" > {{'STATUS.PAGE' | translate}}       

(defn live-support []
  (dom/div  #js {:className "ribbon row"}
            (live-support-title)
            (live-support-phone)
            (live-support-email)
            (live-support-status-server)))

;;;;;;;;;;;;;;;;ABOUT COMPONENT;;;;;;;;;;;;;;;;;

;; (def versions ["version2_4" "version2_3" "version2_2" "version2_1" "version1_22" "version2_4"])

(defn about-audyx []
  (dom/div  #js {:className "about-audix"}
            (dom/h2 "{{'SUPPORT.ABOUT_AUDYX_HEADER' | translate}}")
            (dom/h2 "{{'SUPPORT.ABOUT_AUDYX' | translate}}")))

(defn version-buttons [owner]
  (dom/div #js {:className "btn-group audyx-app-version"}
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(om/set-state! owner :version  "version2_4")}
                       (dom/h4 nil "{{ formatDate('2022-3') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.4.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(om/set-state! owner :version  "version2_3")}
                       (dom/h4 nil "{{ formatDate('2021-5') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.3.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(om/set-state! owner :version  "version2_2")}
                       (dom/h4 nil "{{ formatDate('2020-9') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.2.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(om/set-state! owner :version  "version2_1")}
                       (dom/h4 nil "{{ formatDate('2020-6') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.1.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(om/set-state! owner :version  "version1_22")}
                       (dom/h4 nil "{{ formatDate('2020-1') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "1.22.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(om/set-state! owner :version  "version1_21")}
                       (dom/h4 nil "{{ formatDate('2019-4') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "1.21.0.0"))))


(defn animate-switch-container [owner]
  (let [hide #js {:display "none"} color #js {:backgroundColor "green"}]
    (dom/div  #js {:className "animate-switch-container"}
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= (om/get-state owner :version) "version2_4") hide)} "version1")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= (om/get-state owner :version) "version2_3") hide)} "version2")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= (om/get-state owner :version) "version2_2") hide)} "version3")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= (om/get-state owner :version) "version2_1") hide)} "version4")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= (om/get-state owner :version) "version1_22") hide)} "version5")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= (om/get-state owner :version) "version1_21") hide)} "version6"))))

(defn support [data owner]
  (reify
    om/IInitState
    (init-state [_]
      {:version "version2_4"})
    om/IRenderState
    (render-state [_]
      (dom/div #js {:className "support panel"}
               (live-support)
               (about-audyx)
               (version-buttons owner)
               (animate-switch-container owner)))))

(defn create-support-view []
  (om/root support app-data {:target (. js/document (getElementById "support"))}))