#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/RestTask-1.0-SNAPSHOT.jar \
    a1@10.0.6.38:/Users/a1/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa a1@10.0.6.38 << EOF

pgrep java | xargs kill -9
nohup java -jar sweater-1.0.SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'