const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	let collId = event.queryStringParameters.collId;
	let contId = event.queryStringParameters.contId;

	let contText = await dbClient.get("cont_text:" + collId + ":" + contId);

	let contKeys = await dbClient.keys("cont:" + collId + ":" + contId + ":*");

	let cont = JSON.parse(await dbClient.get(contKeys[0]));

	let res = { title: cont.title, text: contText };

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify(res)
	};
};