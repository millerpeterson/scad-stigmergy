;; A 3D stack of planes with hexagonal tilings.
(ns scad-stimergy.lattice
  (require '[clojure.spec :as s]))

;; ## Cell Positions
;;
;; Cell and positions are represented as coordinates `[x y z d]`.
;; `x`, `y`, and `z` are cube coordinates for a position in a hexgonally tiled plane,
;; and `d` is the plane depth.
(s/def ::cell-pos (s/tuple integer? integer? integer? integer?))
(comment
  (s/valid? ::cell-pos [2 3 5 2])
  (s/valid? ::cell-pos [-3 2 5 1]))

;; ## Cell Values
;;
;; Unoccupied cells have value 0, occupied cells have positive value.
(s/def ::cell-val (s/and integer? #(or (pos? %) (= 0 %))))
(comment
  (s/valid? ::cell-val 9)
  (s/valid? ::cell-val 0)
  (not (s/valid? ::cell-val -9)))

;; ## Lattices
;;
;; A lattice is a map of cell positions to cell values.
(s/def ::lattice (s/map-of ::cell-pos ::cell-val))

;; Lattices are defined by their number of planes and their radius.
;; Planes are numbered from 0 upwards.
;; Each plane is a hexagonal grid of cells radiating from the center position
;; `[0, 0, 0, p]` where `p` is the plane height. 0 is the lowermost plane height, and
;; higher planes have positive indexes.

(defn lattice-positions
  "Valid positions for a lattice with a given radius and height."
  [radius height]
  [])

(defn empty-lattice
  "Returns an lattice of a given plane size."
  [plane-size height]
  [])


