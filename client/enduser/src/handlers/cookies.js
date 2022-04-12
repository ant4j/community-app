import Cookies from "js-cookie";
import appconfig from "../appconfig.json";

const cookieName = {
	communityId: "community-id-2",
	communityCode: "community-code-2",
	communityName: "community-name-2",
	username: "username-2"
}

function setupCookies(data) {
	Cookies.set(cookieName.communityId,
		data.communityId,
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(cookieName.communityCode,
		data.communityCode,
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(cookieName.communityName,
		data.communityName,
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(cookieName.username,
		data.username,
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
	Cookies.remove(cookieName.communityId);
	Cookies.remove(cookieName.communityCode);
	Cookies.remove(cookieName.communityName);
	Cookies.remove(cookieName.username);
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
	if (Cookies.get("community-id")) {
		Cookies.remove("community-id");
	}
	if (Cookies.get("community-code")) {
		Cookies.remove("community-code");
	}
	if (Cookies.get("community-name")) {
		Cookies.remove("community-name");
	}
	if (Cookies.get("username")) {
		Cookies.remove("username");
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
