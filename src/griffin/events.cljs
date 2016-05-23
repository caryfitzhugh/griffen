(ns griffin.events

)

(def movement-scalar 0.1)
(defn move-center [state dx dy]
  ;; New x
  (-> state
    (update-in [:center :x] + (* movement-scalar dx))
    ;; New y
    (update-in [:center :y] + (* movement-scalar dy))))

(defn mouse-event [s]
  (fn [evt]
    (let [type (.-type evt)
          x (.-pageX evt)
          y (.-pageY evt) ]

      (case type
        "mouseup" (swap! s assoc-in [:events :mouse-event :state] :up)
        "mouseleave" (swap! s assoc-in [:events :mouse-event :state] nil)
        "mousedown" (do
          (swap! s (fn [old]
              (-> old
                (assoc-in [:events :mouse-event :state] :down)
                (assoc-in [:events :mouse-event :start] { :x x :y y})))
          ))
        "mousemove" (do
          ;; If the mouse button is *down* - we are dragging
          (if (= (:state (:mouse-event (:events @s))) :down)
              ;; So find the last start position.
              ;; And get the deltas (x / y)
              ;; Moving the map center, and then re-setting the start x/y
             (let [ start (:start (:mouse-event (:events @s)))
                    dx (- x (:x start))
                    dy (- y (:y start))]
                (swap! s (fn [old]
                  (-> old
                    (assoc-in [:events :mouse-event :start] {:x x :y y})
                    (move-center dx dy)))))))
        )
    true)))

(def scrolling-scalar 0.1)

(defn scrolling [s]
  "Creates a handler for the scrolling, with the given state attached."
  (fn [evt]
    (swap! s update-in [:center :z] + (* (.-deltaY evt) scrolling-scalar))))
