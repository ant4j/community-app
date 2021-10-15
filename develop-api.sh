#!/bin/sh

echo "mv ./app/packages/comm-api/*.js ./local/comm-api/"

mv ./app/packages/comm-api/*.js ./local/comm-api/

echo "mv ./app/web/src/endpoint.json ./app/web/src/endpoint.nim.json"

mv ./app/web/src/endpoint.json ./app/web/src/endpoint.nim.json

echo "mv ./app/web/src/endpoint.local.json ./app/web/src/endpoint.json"

mv ./app/web/src/endpoint.local.json ./app/web/src/endpoint.json
