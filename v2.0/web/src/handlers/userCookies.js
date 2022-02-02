import Cookies from "js-cookie";
import appconfig from "../appconfig.json";

const userCommunityId = "user-community-id",
	userCommunityCode = "user-community-code",
	userCommunityName = "user-community-name",
	userUsername = "user-username";

function setupUserCookies(data) {
	Cookies.set(userCommunityId,
		data.communityId,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(userCommunityCode,
		data.communityCode,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(userCommunityName,
		data.communityName,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
	Cookies.set(userUsername,
		data.username,
		{ secure: true },
		{ expires: appconfig.signinCookiesExpiration }
	);
}

function areUserCookiesSetup() {
	if (Cookies.get(userUsername) !== undefined) {
		return true;
	}
	return false;
}

function getUserCommunityIdCookie() {
	return Cookies.get(userCommunityId);
}

function getUserCommunityCodeCookie() {
	return Cookies.get(userCommunityCode);
}

function getUserCommunityNameCookie() {
	return Cookies.get(userCommunityName);
}

function getUserUsernameCookie() {
	return Cookies.get(userUsername);
}

function removeUserCookies() {
	Cookies.remove(userCommunityId, { secure: true });
	Cookies.remove(userCommunityCode, { secure: true });
	Cookies.remove(userCommunityName, { secure: true });
	Cookies.remove(userUsername, { secure: true });
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

//TODO provare a fare export default userCookies = {

export const userCookies = {
	setupUserCookies,
	areUserCookiesSetup,
	getUserCommunityIdCookie,
	getUserCommunityCodeCookie,
	getUserCommunityNameCookie,
	getUserUsernameCookie,
	removeUserCookies,
	removeOldCookies
};
