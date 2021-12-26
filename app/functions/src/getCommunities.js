const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	let commKeys = await dbClient.keys("comm:*");

	let res = [];

	if (commKeys.length) {
		let comms = await dbClient.mget(commKeys);
		res = comms.map(JSON.parse);
	}

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify(res)
	};
};