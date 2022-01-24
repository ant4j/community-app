import config from "../config.json";

function buildFullUrl(baseUrl, path) {
	return `${baseUrl}${path}`;
}

function buildCommunityGet(communityCode) {
	let fullUrl = buildFullUrl(config.endpoint.cmm_srv.baseUrl,
		config.endpoint.cmm_srv.path.community);
	return `${fullUrl}${communityCode}`;
}

function buildAuthenticationPost() {
	let fullUrl = buildFullUrl(config.endpoint.cmm_srv.baseUrl,
		config.endpoint.cmm_srv.path.authentication);
	return fullUrl;
}

export const endpoint = { buildCommunityGet, buildAuthenticationPost };
