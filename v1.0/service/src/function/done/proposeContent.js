const proposalRepository = require("../repository/proposalRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let propId = await proposalRepository.detachProposalId();

	await proposalRepository.createProposal(parseInt(propId), parseInt(params.commId), parseInt(params.collId), parseInt(params.contId), params.username);

	return {
		statusCode: httpStatus.CREATED,
		body: "Proposal created"
	};
};
