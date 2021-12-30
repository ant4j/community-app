const adminRepository = require("../repository/adminRepository");
const httpStatus = require("http-status");

exports.handler = async (event, context, callback) => {
	const params = JSON.parse(event.body);

	let res = {
		statusCode: httpStatus.UNAUTHORIZED,
		body: "Authentication not authorized"
	};

	let admin = await adminRepository.findAdmin(params.username, params.password);

	if (admin != undefined) {
		res = {
			statusCode: httpStatus.OK,
			body: JSON.stringify({ adminId: admin.id })
		};
	}

	return res;
};