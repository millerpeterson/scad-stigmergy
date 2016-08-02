(defproject scad-stimergy "0.1.0-SNAPSHOT"
  :description "Creating OpenSCAD models via discrete stigmergy algorithms."
  :url "http://github.com/millerpeterson/scad-stigmergy"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha10"]
                 [org.clojure/math.combinatorics "0.1.3"]]
  :plugins [[lein-marginalia "0.9.0"]]
  :main ^:skip-aot scad-stimergy.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})