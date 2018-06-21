(defproject biiwide/whats-url-handler "0.0.1-SNAPSHOT"

  :description "Clojure library for registering custom URL protocol handlers."

  :url "https://github.com/biiwide/whats-url-handler"

  :license {:name "Eclipse Public License 2.0"
            :url "https://www.eclipse.org/legal/epl-2.0"}

  :dependencies [[org.clojure/clojure "1.8.0"]]

  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  )
