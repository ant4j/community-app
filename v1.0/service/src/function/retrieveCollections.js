const collectionRepository = require("../repository/collectionRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = event.queryStringParameters;

	let colls = await collectionRepository.retrieveCollections(parseInt(params.commId));

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify(colls)
	};
};