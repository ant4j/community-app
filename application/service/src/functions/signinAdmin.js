const Redis = require("ioredis"),
	httpStatus = require("http-status");

const dbClient = new Redis(process.env.REDIS_URL);
dbClient.on('error', (err) => console.log('Redis Client ', err));

exports.handler = async (event, context, callback) => {
	let params = JSON.parse(event.body);

	let adminKeys = await dbClient.keys("admin:*:" + params.username);

	if (adminKeys.length > 0) {
		let adminBuffer = await dbClient.get(adminKeys[0]);

		let admin = JSON.parse(adminBuffer);

		if (params.password == admin.password) {
			return {
				statusCode: httpStatus.OK,
				body: JSON.stringify({ adminId: admin.id })
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