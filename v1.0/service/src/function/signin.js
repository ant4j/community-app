const communityRepository = require("../repository/communityRepository");
const userRepository = require("../repository/userRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let res = {
		statusCode: httpStatus.UNAUTHORIZED,
		body: "Authentication not authorized"
	};

	let comm_auth = await communityRepository.retrieveAuthentication(parseInt(params.commId));

	if (comm_auth.watchword != undefined && params.watchword == comm_auth.watchword) {
		let userId = await userRepository.detachUserId();
		let username = "user" + userId;
		res = {
			statusCode: httpStatus.OK,
			body: JSON.stringify({ "username": username })
		};
	}

	return res;
};