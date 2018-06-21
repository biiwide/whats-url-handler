(ns biiwide.whats-url-handler-test
  (:require [clojure.test :refer :all]
            [biiwide.whats-url-handler :refer :all])
  (:import  [java.net URL]))


(register-handler "basic-bytes-test"
                  (basic-readable-stream-handler
                    (fn [^java.net.URL url]
                      (java.io.ByteArrayInputStream.
                        (.getBytes (str url))))))


(deftest test-basic-readable-stream-handler
  (is (= "basic-bytes-test:foo.bar/abc"
         (slurp (URL. "basic-bytes-test:foo.bar/abc")))))

