const collectionRepository = require("../repository/collectionRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);
	
	let res = {
		statusCode: httpStatus.CONFLICT,
		body: "Collection already exist"
	};

	let coll = await collectionRepository.retrieveCollection(undefined, params.name);

	if(coll == undefined) {
		let collId = await collectionRepository.detachCollectionId();

		await collectionRepository.createCollection(parseInt(collId), parseInt(params.commId), params.name, parseInt(params.type));

		res = {
			statusCode: httpStatus.CREATED,
			body: "Collection created"
		};
	}
	
	return res;
};
