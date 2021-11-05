#!/bin/bash
# check-config-server-started.sh

apt-get update -y

apt-get -y install curl

curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health)

echo "result status code:" $curlResult

while [[ ! $curlResult == "200" ]]; do
    >&2 echo "Config server is not up yet!"
    sleep 2
    curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health)
done

./cnb/process/web