const collectionRepository = require("../repository/collectionRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);
	
	let res = {
		statusCode: httpStatus.CONFICT,
		body: "Collection already exist"
	};

	let coll = await collectionRepository.findCollection(undefined, params.name);

	if(coll == undefined) {
		let collId = await collectionRepository.detachCollectionId();

		await collectionRepository.createCollection(collId, params.commId, params.name, params.type);

		res = {
			statusCode: httpStatus.CREATED,
			body: "Collection created"
		};
	}
	
	return res;
};
