../develop-api.sh
sudo systemctl start docker
docker kill redis
docker run --name redis --rm -p 6379:6379 -d redis --requirepass password
export __NIM_REDIS_IP=127.0.0.1
export __NIM_REDIS_PASSWORD=password
node --experimental-repl-await
let rd = require("@nimbella/sdk").redis()
rd.ready