const collectionRepository = require("../repository/collectionRepository");
const contentRepository = require("../repository/contentRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = event.queryStringParameters;

	let coll = await collectionRepository.findCollection(parseInt(params.collId), undefined);

	let conts = await contentRepository.findContents(parseInt(params.collId));

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify({ collName: coll.name, data: conts })
	};
};