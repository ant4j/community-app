const communityRepository = require("../repository/communityRepository");
const userRepository = require("../repository/userRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let res = {
		statusCode: httpStatus.UNAUTHORIZED,
		body: "Authentication not authorized"
	};

	let watchword = await communityRepository.findAuthentication(params.commId);

	if(watchword != undefined && params.watchword == watchword) {
		let userId = await userRepository.detachUserId();
		let username = "user" + userId;
		res = {
			statusCode: httpStatus.OK,
			body: JSON.stringify({ "username": username })
		};
	}

	return res;
};