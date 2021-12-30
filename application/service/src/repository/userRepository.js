const Redis = require("ioredis");
const client = new Redis(process.env.REDIS_URL);

client.on('error', (err) => console.log('Redis Client ', err));

module.exports.detachUserId = async () => {
	return await client.incr("user_id_seq");
};