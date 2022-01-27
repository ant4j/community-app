import Cookies from "js-cookie";
import config from "../config.json";

const adminAdminId = "admin-admin-id",
	adminUsername = "admin-username";

function setupAdminCookies(data) {
	Cookies.set(adminAdminId,
		data.adminId,
		{ secure: true },
		{ expires: config.signin_cookies_expiration }
	);
	Cookies.set(adminUsername,
		data.username,
		{ secure: true },
		{ expires: config.signin_cookies_expiration }
	);
}

function areAdminCookiesSetup() {
	if (Cookies.get(adminUsername) !== undefined) {
		return true;
	}
	return false;
}

function getAdminAdminIdCookie() {
	return Cookies.get(adminAdminId);
}

function getAdminUsernameCookie() {
	return Cookies.get(adminUsername);
}

function removeAdminCookies() {
	Cookies.remove(adminAdminId, { secure: true });
	Cookies.remove(adminUsername, { secure: true });
}

export const adminCookies = {
	setupAdminCookies,
	areAdminCookiesSetup,
	getAdminAdminIdCookie,
	getAdminUsernameCookie,
	removeAdminCookies
};
