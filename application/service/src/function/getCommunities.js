const communityRepository = require("../repository/communityRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	let comms = await communityRepository.findCommunities();

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify(comms)
	};
};