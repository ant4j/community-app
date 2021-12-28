const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	await dbClient.incr("prop_id_seq");

	let propId = await dbClient.get("prop_id_seq");

	let key = "prop:" + params.commId + ":" + propId;

	let value = JSON.stringify({ "username": params.username, "coll_id": params.collId, "cont_id": params.contId });

	await dbClient.set(key, value);

	await dbClient.set("last_prop:" + params.commId, propId);

	return {
		statusCode: httpStatus.CREATED,
		body: "Proposal created"
	};
};