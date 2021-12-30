const contentRepository = require("../repository/contentRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = event.queryStringParameters;
	
	let contText = await contentRepository.findContentText(params.contId, params.collId);
	
	let cont = await contentRepository.findContent(params.contId, undefined);

	return {
		statusCode: httpStatus.OK,
		body: JSON.stringify({ title: cont.title, text: contText })
	};
};