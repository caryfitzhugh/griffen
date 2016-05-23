(defproject griffin "0.1.0-SNAPSHOT"
  :description "High-Resolution Image Viewer with reactive state API",
  :url "http://"
  :license {:name "Copyright Corista LLC",
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.6.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [org.clojure/core.async "0.2.374"
                  :exclusions [org.clojure/tools.reader]]
                 [reagent "0.6.0-alpha"]]

  :plugins [[lein-figwheel "0.5.3-2"]
            [lein-cljsbuild "1.1.3" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                ;; If no code is to be run, set :figwheel true for continued automagical reloading
                :figwheel true

                :compiler {:main griffin.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/griffin.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}
                }

               ;; This next build is an compressed minified build for
               ;; production / release. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/griffin.min.js"
                           :main griffin.core,
                           :optimizations :advanced
                           :pretty-print false}}]
                }

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             :server-port 7887 ;; default
             ;; :server-ip "127.0.0.1"
             :nrepl-port 7888
             :css-dirs ["resources/public/css"] ;; watch and update CSS
             }


  ;; setting up nREPL for Figwheel and ClojureScript dev
  ;; Please see:
  ;; https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl
)
