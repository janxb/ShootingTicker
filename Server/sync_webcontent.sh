#!/bin/bash

while true; do

rsync -a --delete 	target/server-1.0-SNAPSHOT/images 		src/main/webapp
rsync -a --delete 	target/server-1.0-SNAPSHOT/stylesheets	src/main/webapp
rsync -a --delete 	target/server-1.0-SNAPSHOT/scripts 		src/main/webapp
/bin/cp -r 			target/server-1.0-SNAPSHOT/*.jsp		src/main/webapp

sleep 10

done;