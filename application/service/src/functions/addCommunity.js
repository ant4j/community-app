const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let commKeys = await dbClient.keys("comm:*:*:*:" + params.name);

	if (!commKeys.length) {
		await dbClient.incr("comm_id_seq");

		let commId = await dbClient.get("comm_id_seq");

		let commNameSplit = params.name.split(" ");
		let commCode = "";
		commNameSplit.forEach(val => {
			commCode = commCode + val.toLowerCase().charAt(0);
		});
		commCode = commCode + commId;

		let key = "comm:" + commId + ":" + params.adminId + ":" + commCode + ":" + params.name;
		let value = JSON.stringify({ "id": commId, "code": commCode, "name": params.name });

		await dbClient.set(key, value);

		await dbClient.set("comm_auth:" + commId, params.watchword);

		return {
			statusCode: httpStatus.CREATED,
			body: "Community created"
		};
	} else {
		return {
			statusCode: httpStatus.CONFLICT,
			body: "Community already exist"
		};
	}
};