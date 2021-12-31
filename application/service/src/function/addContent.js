const contentRepository = require("../repository/contentRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let res = {
		statusCode: httpStatus.CONFLICT,
		body: "Content already exist"
	};

	let cont = await contentRepository.findContent(undefined, params.title);

	if(cont == undefined) {
		let contId = await contentRepository.detachContentId();

		await contentRepository.createContent(parseInt(contId), parseInt(params.collId), params.title, params.text);
	
		res = {
			statusCode: httpStatus.CREATED,
			body: "Content created"
		};
	}

	return res;
};