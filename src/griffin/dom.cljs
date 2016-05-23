(ns griffin.dom
  (:require [reagent.core :as r]
            [griffin.events :as events]
            )
  )

;; The number of tiles to pre-load around the visible borders of the viewport" 2)
(def tile-padding "The day is young" 2)

;;; Could you just write out all the image tags for a slide?
(defn tile-layer [s zoom]
  (let [tile-func (get-in @s [:tiles :url])
        ]
    [:image {:x (:x (:center @s))
             :y (:y (:center @s))
             :width 1
             :height 1
             "xlinkHref" (tile-func zoom)} (tile-func zoom)]
  ))

(defn image-tiles [s]
  [:div.tile-container
    [:h1 (pr-str (:mouse-event (:events @s)))]
    [:h6 (pr-str (:center @s))]
    [:svg {:width "100%"
           :height "100%"
           :viewBox "0 0 100 100"
           :on-mouse-down (events/mouse-event s)
           :on-mouse-up   (events/mouse-event s)
           :on-mouse-move (events/mouse-event s)
           :on-mouse-leave (events/mouse-event s)
           :on-wheel      (events/scrolling s)
           }
      [tile-layer s (:center @s)]
    ]
  ]
  )

(defn layout  [s]
  [:div.container
    [image-tiles s]
  ])
