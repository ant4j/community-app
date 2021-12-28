const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);
	
	let collKeys = await dbClient.keys("coll:*:*:" + params.name);
	
	if (!collKeys.length) {
		await dbClient.incr("coll_id_seq");
		
		let collId = await dbClient.get("coll_id_seq");
		
		let key = "coll:" + params.commId + ":" + collId + ":" + params.name;
		let value = JSON.stringify({ "id": collId, "name": params.name, "type": params.type });
		
		await dbClient.set(key, value);
		
		return {
			statusCode: httpStatus.CREATED,
			body: "Collection created"
		};
	} else {
		return {
			statusCode: httpStatus.CONFICT,
			body: "Collection already exist"
		};
	}
};