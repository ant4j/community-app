const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	let params = JSON.parse(event.body);

	let key = "comm_auth:" + params.commId;

	let existsKey = await dbClient.exists(key);

	if (existsKey == 1) {
		let watchword = await dbClient.get(key);
		if (params.watchword == watchword) {
			await dbClient.incr("user_id_seq");

			let userId = await dbClient.get("user_id_seq");

			let username = "user" + userId;

			return {
				statusCode: httpStatus.OK,
				body: JSON.stringify({ "username": username })
			};
		} else {
			return {
				statusCode: httpStatus.UNAUTHORIZED,
				body: "Authentication not authorized"
			};
		}
	} else {
		return {
			statusCode: httpStatus.UNAUTHORIZED,
			body: "Authentication not authorized"
		};
	}
};