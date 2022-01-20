const communityRepository = require("../repository/communityRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let res = {
		statusCode: httpStatus.CONFLICT,
		body: "Community already exist"
	};

	let comm = await communityRepository.retrieveCommunity(undefined, params.name);

	if (comm == undefined) {
		let commId = await communityRepository.detachCommunityId();

		let commCode = "";
		params.name.split(" ").forEach(val => {
			commCode = commCode + val.toLowerCase().charAt(0);
		});
		commCode = commCode + commId;

		await communityRepository.createCommunity(parseInt(commId), parseInt(params.adminId), commCode, params.name, params.watchword);

		res = {
			statusCode: httpStatus.CREATED,
			body: "Community created"
		};
	}

	return res;
};