import appconfig from "../appconfig.json";

function buildFullUrl(baseUrl, path) {
	return `${baseUrl}${path}`;
}

function buildCommunityGet(communityCode) {
	let fullUrl = buildFullUrl(appconfig.endpoint.cmm_srv.baseUrl,
		appconfig.endpoint.cmm_srv.path.community);
	return `${fullUrl}${communityCode}`;
}

function buildAuthenticationPost() {
	let fullUrl = buildFullUrl(appconfig.endpoint.cmm_srv.baseUrl,
		appconfig.endpoint.cmm_srv.path.authentication);
	return fullUrl;
}

export const endpoint = {
	buildCommunityGet,
	buildAuthenticationPost
};
