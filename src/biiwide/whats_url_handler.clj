(ns biiwide.whats-url-handler
  (:require [clojure.string :as str])
  (:import  [java.net URL]))


(defonce ^:private HANDLERS (atom {}))


(defn- handler-factory
  []
  (reify java.net.URLStreamHandlerFactory
    (createURLStreamHandler [_ protocol]
      (when protocol
        (get @HANDLERS (str/lower-case protocol))))))


(defonce ^:private registered-factory
  (do (URL/setURLStreamHandlerFactory
        (handler-factory))
      true))


(defn ^java.net.URLConnection basic-readable-connection
  [url open-input-stream]
  (let [input (delay (open-input-stream url))]
    (proxy [java.net.URLConnection] [url]
      (connect [] nil)
      (getInputStream [] @input))))


(defn ^java.net.URLStreamHandler basic-readable-stream-handler
  ""
  [open-input-stream]
  (proxy [java.net.URLStreamHandler] []
    (openConnection [^URL url]
      (basic-readable-connection url open-input-stream))))


(defn register-handler
  [protocol ^java.net.URLStreamHandler url-stream-handler]
  (assert (instance? java.net.URLStreamHandler url-stream-handler)
          "The url-stream-handler must extend java.net.URLStreamHandler.")
  (let [protocol (str/lower-case (name protocol))]
    (swap! HANDLERS assoc (str/lower-case (name protocol))
                          url-stream-handler)
    protocol))

