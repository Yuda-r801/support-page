(ns om-tut.support
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

;;;;;;;;;;;;;LIVE SUPPORT TEMPLATE;;;;;;;;;;;;;;

(defn live-support-title []
  (dom/div  #js {:className "col-xs-12"}
            (dom/button #js {:type "button" :className "btn btn-primary"} "123")
            (dom/h2 nil "{{'SUPPORT.ABOUT' | translate}}")
            (dom/p #js {:className "available"}) "{{countryContents.support_hours [userLanguage] || Object.values (countryContents.support_hours) [0]}}"))

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

(def version (atom "version2_4"))

(defn about-audyx []
  (dom/div  #js {:className "about-audix"}
            (dom/h2 "{{'SUPPORT.ABOUT_AUDYX_HEADER' | translate}}")
            (dom/h2 "{{'SUPPORT.ABOUT_AUDYX' | translate}}")))

(defn version-buttons []
  (dom/div #js {:className "btn-group audyx-app-version"}
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(reset! version "version2_4")}
                       (dom/h4 nil "{{ formatDate('2022-3') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.4.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(reset! version "version2_3")}
                       (dom/h4 nil "{{ formatDate('2021-5') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.3.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(reset! version "version2_2")}
                       (dom/h4 nil "{{ formatDate('2020-9') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.2.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(reset! version "version2_1")}
                       (dom/h4 nil "{{ formatDate('2020-6') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "2.1.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(reset! version "version1_22")}
                       (dom/h4 nil "{{ formatDate('2020-1') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "1.22.0.0"))
           (dom/button #js {:type "button" :className "btn btn-default" :onClick #(reset! version "version1_21")}
                       (dom/h4 nil "{{ formatDate('2019-4') }}"
                               (dom/i #js {:className "fa fa-check"}))
                       (dom/h5 nil "1.21.0.0"))))

(defn animate-switch-container []
  (let [hide #js {:display "none"} color #js {:backgroundColor "green"}]
    (dom/div  #js {:className "animate-switch-container"} 
              (dom/div #js {:className "animate-switch" :style 
                            (when-not (= @version "version2_4") hide)} "not ever1")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= @version "version2_3") hide)} "not ever2")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= @version "version2_2") hide)} "not ever3")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= @version "version2_1") hide)} "not ever4")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= @version "version1_22") hide)} "not ever5")
              (dom/div #js {:className "animate-switch" :style
                            (when-not (= @version "version1_21") hide)} "not ever6"))))

(defn support []
  (dom/div nil
;;    #js {:className ribbon row"}
           (live-support)
           (about-audyx)
           (version-buttons)
           (animate-switch-container)))

#_(deftry support-view [_ owner]
  (reify
    om/IRenderState
    (render-state [_ state]
      (let [$translate ((om/get-shared owner :filter) "translate")]
        (dom/div #js {:className (str "row legends-view test-panel-animation display-legends-view" (when display-legends? " enlarged"))}
                 (dom/div #js {:className "display-legends"
                               :onClick #(put! (om/get-state owner :chan-legends-toggle-display) (not display-legends?))} "...")
                 (apply dom/div #js {:className "legends-container"}
                        (concat
                         (mapv (partial build-grouped-legend owner state display-legends?) tests)
                         (if normo (vector (om/build legend-normo-view (assoc normo :collapsed-legends? (not display-legends?)))) [])))
                 (when (not-empty noah-tests)
                   (separator-view $translate))
                 (apply dom/div nil
                        (mapv (partial build-grouped-legend owner state display-legends?) noah-tests)))))))

(defn support1 [data owner]
  (reify
    om/IInitState
    (init-state [_]
      {:version "version1_21"})
    om/IRenderState
    (render-state [this state]
     )))
