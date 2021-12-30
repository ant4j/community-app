const communityRepository = require("../repository/communityRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = event.queryStringParameters;

	let res = {
		statusCode: httpStatus.NOT_FOUND,
		body: "Community not found"
	};

	let comm = await communityRepository.findCommunity(params.commCode, undefined);

	if(comm) {
		res = {
			statusCode: httpStatus.OK,
			body: JSON.stringify(comm)
		};
	}

	return res;
};