import config from "../config.json";

function buildFullUrl(baseUrl, path) {
	return `${baseUrl}${path}`;
}

function buildCommunityGet(communityCode) {
	let fullUrl = buildFullUrl(config.endpoint.cmm_srv.baseUrl,
		config.endpoint.cmm_srv.path.community);
	return `${fullUrl}${communityCode}`;
}

function buildAuthPost() {
	let fullUrl = buildFullUrl(config.endpoint.cmm_srv.baseUrl,
		config.endpoint.cmm_srv.path.auth);
	return fullUrl;
}

export const endpoint = { buildCommunityGet, buildAuthPost };
