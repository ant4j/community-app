const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	const params = event.queryStringParameters;

	let contKeys = await dbClient.keys("cont:" + params.collId + ":*:*");

	let collKeys = await dbClient.keys("coll:*:" + params.collId + ":*");

	let coll = JSON.parse(await dbClient.get(collKeys[0]));

	let res = { collName: coll.name, data: [] };

	if (contKeys.length) {
		let conts = await dbClient.mget(contKeys);
		res.data = conts.map(JSON.parse);
		return {
			statusCode: httpStatus.OK,
			body: JSON.stringify(res)
		};
	} else {
		return {
			statusCode: httpStatus.NOT_FOUND,
			body: JSON.stringify(res)
		};
	}
};