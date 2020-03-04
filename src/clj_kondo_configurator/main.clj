(ns clj-kondo-configurator.main
  (:require [clj-kondo.core :as clj-kondo]
            [clojure.edn :as edn]
            [clojure.pprint :refer [pprint]]
            [clojure.java.io :as io]))

(defn -main [& [flag opts & _args]]
  (when-not (= "--opts" flag)
    (throw (ex-info (str "Unrecognized option: " flag) {:flag flag})))
  (let [opts (edn/read-string opts)]
    (when-let [merge-args (:merge opts)]
      (let [files (map io/file merge-args)
            configs (map slurp files)
            edns (map edn/read-string configs)
            merged-config (apply clj-kondo/merge-configs edns)]
        (pprint merged-config)))))
