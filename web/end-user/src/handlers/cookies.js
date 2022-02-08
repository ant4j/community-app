import Cookies from "js-cookie";
import appconfig from "../appconfig.json";

const cookieName = {
	communityId: "community-id",
	communityCode: "community-code",
	communityName: "community-name",
	username: "username"
}

function setupCookies(data) {
	Cookies.set(cookieName.communityId,
		data.communityId,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(cookieName.communityCode,
		data.communityCode,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(cookieName.communityName,
		data.communityName,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(cookieName.username,
		data.username,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
}

function areCookiesSetup() {
	if (Cookies.get(cookieName.username) !== undefined) {
		return true;
	}
	return false;
}

function getCommunityIdCookie() {
	return Cookies.get(cookieName.communityId);
}

function getCommunityCodeCookie() {
	return Cookies.get(cookieName.communityCode);
}

function getCommunityNameCookie() {
	return Cookies.get(cookieName.communityName);
}

function getUsernameCookie() {
	return Cookies.get(cookieName.username);
}

function removeCookies() {
	Cookies.remove(cookieName.communityId, { secure: true });
	Cookies.remove(cookieName.communityCode, { secure: true });
	Cookies.remove(cookieName.communityName, { secure: true });
	Cookies.remove(cookieName.username, { secure: true });
}

function removeOldCookies() {
	console.log(
		"old invalid cookies removal until all users are up to date"
	);
	if (Cookies.get("signin-comm-id")) {
		Cookies.remove("signin-comm-id");
	}
	if (Cookies.get("signin-comm-name")) {
		Cookies.remove("signin-comm-name");
	}
	if (Cookies.get("signin-username")) {
		Cookies.remove("signin-username");
	}
}

const cookies = {
	setupCookies,
	areCookiesSetup,
	getCommunityIdCookie,
	getCommunityCodeCookie,
	getCommunityNameCookie,
	getUsernameCookie,
	removeCookies,
	removeOldCookies
};

export default cookies;
