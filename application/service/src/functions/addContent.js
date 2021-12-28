const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let contKeys = await dbClient.keys("cont:*:*:" + params.title)

	if (!contKeys.length) {
		await dbClient.incr("cont_id_seq");

		let contId = await dbClient.get("cont_id_seq");

		let key = "cont:" + params.collId + ":" + contId + ":" + params.title;
		let value = JSON.stringify({ "id": contId, "title": params.title });

		await dbClient.set(key, value);

		await dbClient.set("cont_text:" + params.collId + ":" + contId, params.text);

		return {
			statusCode: httpStatus.CREATED,
			body: "Content created"
		};
	} else {
		return {
			statusCode: httpStatus.CONFLICT,
			body: "Content already exist"
		};
	}
};