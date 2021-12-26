const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	let commId = event.queryStringParameters.commId;

	let lastPropId = await dbClient.get("last_prop:" + commId);

	let key = "prop:" + commId + ":" + lastPropId;

	let lastProposal = JSON.parse(await dbClient.get(key));

	if (lastProposal) {

		let contKeys = await dbClient.keys("cont:" + lastProposal.coll_id + ":" + lastProposal.cont_id + ":*");

		let content = JSON.parse(await dbClient.get(contKeys[0]));

		let collKeys = await dbClient.keys("coll:" + commId + ":" + lastProposal.coll_id + ":*");

		let collection = JSON.parse(await dbClient.get(collKeys[0]));

		let data = { "username": lastProposal.username, "contentTitle": content.title, "collectionType": collection.type, "collId": lastProposal.coll_id, "contId": lastProposal.cont_id };

		return {
			statusCode: httpStatus.OK,
			body: JSON.stringify({ data: data })
		};

	} else {
		return {
			statusCode: httpStatus.OK,
			body: JSON.stringify({ data: {} })
		};
	}
};