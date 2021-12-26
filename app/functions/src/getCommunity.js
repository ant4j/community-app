const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	let commCode = event.queryStringParameters.commCode;

	let commKeys = await dbClient.keys("comm:*:*:" + commCode + ":*");

	if (commKeys.length) {
		let comm = JSON.parse(await dbClient.get(commKeys[0]));
		return {
			statusCode: httpStatus.OK,
			body: JSON.stringify({ data: comm })
		};
	} else {
		return {
			statusCode: httpStatus.NOT_FOUND,
			body: "Community not found"
		};
	}
};