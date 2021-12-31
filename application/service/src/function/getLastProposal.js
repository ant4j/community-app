const proposalRepository = require("../repository/proposalRepository");
const contentRepository = require("../repository/contentRepository");
const collectionRepository = require("../repository/collectionRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = event.queryStringParameters;

	let data = {};

	let lastPropId = await proposalRepository.findLastProposalId(parseInt(params.commId));

	let lastProposal = await proposalRepository.findProposal(parseInt(lastPropId), parseInt(params.commId));

	if (lastProposal != undefined) {

		let content = await contentRepository.findContent(parseInt(lastProposal.cont_id), undefined);

		let collection = await collectionRepository.findCollection(parseInt(lastProposal.coll_id), undefined);

		data = {
			"username": lastProposal.username,
			"contentTitle": content.title,
			"collectionType": collection.type,
			"collId": lastProposal.coll_id,
			"contId": lastProposal.cont_id
		};
	}

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify({ data: data })
	};
};