(ns om-tut.support
    (:require [om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]))

;;;;;;;;;;;;;LIVE SUPPORT TEMPLATE;;;;;;;;;;;;;;

(defn live-support-title []
  (dom/div  #js {:className "col-xs-12"}
            (dom/h2 nil "{{'SUPPORT.ABOUT' | translate}}")
            (dom/p #js {:className "available"} "{{countryContents.support_hours [userLanguage] || Object.values (countryContents.support_hours) [0]}}")))

(defn live-support-phone []
  (dom/div  #js {:className "ad-bookmark phone border"}
            (dom/i #js {:className "fa fa-phone"})
            (dom/a  #js {:className "contact"});;  href= "tel:{{countryContents.support_phone.replaceAll(' ', '').replaceAll('-', '')}}" > {{countryContents.support_phone}}       
            ))

(defn live-support-email []
  (dom/div  #js {:className "ad-bookmark email border"}
            (dom/i #js {:className "fa fa-envelope-o"})
            (dom/a  #js {:className "contact"});;   href= "mailto:{{countryContents.support_email}}" > {{countryContents.support_email}}       
            ))

(defn live-support-server-uptime []
  (dom/div  #js {:className "ad-bookmark status-server"}
            (dom/i #js {:className "fa fa-line-chart"})
            (dom/a  #js {:className "contact"});;   href= "http://status.audyx.com" target= "_blank" > {{'STATUS.PAGE' | translate}}       
            ))

(defn live-support []
  (dom/div  #js {:className "ribbon row"}
            (live-support-title)
            (live-support-phone)
            (live-support-email)
            (live-support-server-uptime)))


;; <div class= "ribbon row" >
;; <div class= "col-xs-12" >
;; <h2> {{'SUPPORT.ABOUT' | translate}} </h2>
;; <p class= "available" > {{countryContents.support_hours [userLanguage] || Object.values (countryContents.support_hours) [0]}} </p>
;; </div>    kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
;; <div class= "ad-bookmark phone border" >
;; <i class= "fa fa-phone" ></i>
;; <a class= "contact" href= "tel:{{countryContents.support_phone.replaceAll(' ', '').replaceAll('-', '')}}" > {{countryContents.support_phone}} </a>
;; </div>     kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
;; <div class= "ad-bookmark email border" >
;; <i class= "fa fa-envelope-o" ></i>
;; <a class= "contact" href= "mailto:{{countryContents.support_email}}" > {{countryContents.support_email}} </a>
;; </div>       kkkkkkkkkkkkkkkkkkkkkkk
;; <div class= "ad-bookmark status-server" >
;; <i class= "fa fa-line-chart" ></i>
;; <a class= "contact" href= "http://status.audyx.com" target= "_blank" > {{'STATUS.PAGE' | translate}} </a>
;; </div>
;; </div>
