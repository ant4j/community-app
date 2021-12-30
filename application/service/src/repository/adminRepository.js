const Redis = require("ioredis");
const client = new Redis(process.env.REDIS_URL);

client.on('error', (err) => console.log('Redis Client ', err));

module.exports.findAdmin = async (username, password) => {
	let pattern = `*"username":"${username}","password":"${password}"*`;
	let res = await client.sscan("admin", 0, "match", pattern);
	if (res[1][0] == undefined) {
		return undefined;
	}
	return JSON.parse(res[1][0]);
};
