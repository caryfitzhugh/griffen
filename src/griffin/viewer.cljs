(ns griffin.viewer
  (:require [reagent.core :as r]
            [griffin.dom :as dom])
  )

;; Image Tiling Pyramid Description:  http://northstar-www.dartmouth.edu/doc/idl/html_6.2/Image_Tiling.html

(def initial-state
  "The initial state of the viewer"
  {
    :center {:x 0
             :y 0
             :z 0 ;; Tiling pyramid level
            }
    :tiles {
      :url (fn [x y z] (str "http://placehold.it/300?text=" x "-" y "-" z))
      :levels [0.0]
      :level_0 {
        ;;:microns {:x 1, :y 1} ;; Lvl 0 pixel micron size
        :tiles  {:x 256, :y 256},
        :size   {:x 20000, :y 20000}
        :level_scale 2 ;; How much are the tiles split by?
      }
    }
  })

(defn create-viewer
  "Create the viewer component, and return its data atom / renderer tuple"
  [element opts]
  (let [data (r/atom (merge initial-state opts))]
    (list data (r/render-component [dom/layout data] element))))
