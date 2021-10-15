#!/bin/sh

echo "mv ../local/comm-api/*.js ../app/packages/comm-api/"

mv ../local/comm-api/*.js ../app/packages/comm-api/

echo "mv ../app/web/src/endpoint.json ../app/web/src/endpoint.local.json"

mv ../app/web/src/endpoint.json ../app/web/src/endpoint.local.json

echo "mv ../app/web/src/endpoint.nim.json ../app/web/src/endpoint.json"

mv ../app/web/src/endpoint.nim.json ../app/web/src/endpoint.json
