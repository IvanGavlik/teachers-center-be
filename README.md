# teachers-center-be

A Clojure library designed to ... well, that part is up to you.

## Usage

FIXME

## License

Copyright © 2024 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

## User REPL

Then at the REPL, run the Jetty adapter with your handler.
* (use 'ring.adapter.jetty)
* (use 'teachers-center-be.core)
* (run-jetty handler {:port 3000 :join? false})

## ring and compojure
https://medium.com/helpshift-engineering/understanding-ring-middlewares-and-compojure-routes-1c4e66d63cf

## Use docker 
* start docker demon -> sudo systemctl start docker
* build image run from project root folder (teachers-center-be) -> sudo docker build -t teacers-center-be .
* run image -> sudo docker run -p 3000:3000 -e API_KEY="api-key" teacers-center-be