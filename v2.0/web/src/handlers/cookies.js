import Cookies from "js-cookie";

const halfDayExpiration = 0.5;

const userCommunityId = "user-community-id",
	userCommunityCode = "user-community-code",
	userCommunityName = "user-community-name",
	userUsername = "user-username";

const adminAdminId = "admin-admin-id",
	adminUsername = "admin-username";

function setupUserCookies(data) {
	Cookies.set(userCommunityId,
		data.communityId,
		{ secure: true },
		{ expires: halfDayExpiration }
	);
	Cookies.set(userCommunityCode,
		data.communityCode,
		{ secure: true },
		{ expires: halfDayExpiration }
	);
	Cookies.set(userCommunityName,
		data.communityName,
		{ secure: true },
		{ expires: halfDayExpiration }
	);
	Cookies.set(userUsername,
		data.username,
		{ secure: true },
		{ expires: halfDayExpiration }
	);
}

function isUserCookiesSetup() {
	if (Cookies.get(userUsername) !== undefined) {
		return true;
	}
	return false;
}

function getUserCookies() {
	return {
		userCommunityId: Cookies.get(userCommunityId),
		userCommunityCode: Cookies.get(userCommunityCode),
		userCommunityName: Cookies.get(userCommunityName),
		userUsername: Cookies.get(userUsername)
	};
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

function setupAdminCookies(data) {
	Cookies.set(adminAdminId,
		data.adminId,
		{ secure: true },
		{ expires: halfDayExpiration }
	);
	Cookies.set(adminUsername,
		data.username,
		{ secure: true },
		{ expires: halfDayExpiration }
	);
}

function isAdminCookiesSetup() {
	if (Cookies.get(adminUsername) !== undefined) {
		return true;
	}
	return false;
}

function getAdminCookies() {
	return {
		adminAdminId: Cookies.get(adminAdminId),
		adminUsername: Cookies.get(adminUsername),
	};
}
function removeAdminCookies() {
	Cookies.remove(adminAdminId, { secure: true });
	Cookies.remove(adminUsername, { secure: true });
}

export const userCookies = {
	setupUserCookies,
	isUserCookiesSetup,
	getUserCookies,
	removeUserCookies,
	removeOldCookies
};

export const adminCookies = {
	setupAdminCookies,
	isAdminCookiesSetup,
	getAdminCookies,
	removeAdminCookies
};
