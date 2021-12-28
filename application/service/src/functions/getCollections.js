const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	const params = event.queryStringParameters;

	let collKeys = await dbClient.keys("coll:" + params.commId + ":*");

	let res = [];

	if (collKeys.length) {
		let colls = await dbClient.mget(collKeys);
		res = colls.map(JSON.parse);
	}

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify(res)
	};
};