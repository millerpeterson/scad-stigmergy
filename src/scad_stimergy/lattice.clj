;; A 3D stack of planes with hexagonal tilings.
(ns scad-stimergy.lattice
  (:require [clojure.spec :as s]
            [clojure.math.combinatorics :as combo]))

;; ## Cell Positions
;;
;; Cell and positions are represented as coordinates [x y z d].
;; x, y, and z are cube coordinates for a position in a hexgonally tiled plane,
;; and d is the plane depth. Cube coordinates have the property that x + y + z = 0.
(s/def ::cell-pos (s/and
                    (s/tuple integer? integer? integer? integer?)
                    (fn [[x y z _]] (= (+ x y z) 0))))
(comment
  (s/valid? ::cell-pos [3 0 -3 2])
  (not (s/valid? ::cell-pos [-3 2 5 1])))

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
;; [0, 0, 0, p] where p is the plane height.

(defn plane-positions
  "Valid cell positions within a lattice plane of given radius."
  [radius]
  (let [diameter-range (range (- radius) radius)]
    (filter (fn [[x y z]] (= (+ x y z) 0))
      (combo/cartesian-product diameter-range
                               diameter-range
                               diameter-range))))

(defn lattice-positions
  "Valid cell positions for a lattice with a given radius and height."
  [radius height]
  (map flatten
       (combo/cartesian-product (plane-positions radius) (range height))))

(defn empty-lattice
  "A lattice of a given size with all elements set to unoccupied."
  [radius height]
  (reduce #(assoc %1 %2 0) {} (lattice-positions radius height)))