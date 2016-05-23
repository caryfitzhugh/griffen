(ns griffin.core
  (:require [reagent.core :as r]
            [griffin.viewer :as viewer]))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (r/atom {}))

(defn ^:export start
  "Initializes a griffin viewer.  Handed an ID, this will fill the div with the viewer.
   options are a JS object of parameters"
  [element-id js-options]
  (let [el (. js/document (getElementById element-id))
        opts (js->clj js-options :keywordize-keys true)]

    (if el
      (swap! app-state assoc element-id (viewer/create-viewer el opts)
      nil ;; Should we squawk here?
     )
   )))

;; Register for changes
;; Accept updates to start
;; Accept other things... ?
