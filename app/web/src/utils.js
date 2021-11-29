import Cookies from "js-cookie";

export const MODE = Object.freeze({ USER: 1, ADMIN: 2 });
const IN_HALF_A_DAY_EXP = 0.5;

export function isSignedIn(mode) {
	let isSignedIn = false;
	switch (mode) {
		case MODE.ADMIN:
			if (Cookies.get("admin-id-133-1") !== undefined) {
				isSignedIn = true;
			}
			break;
		case MODE.USER:
		default:
			if (Cookies.get("signin-comm-id-133-1") !== undefined) {
				isSignedIn = true;
			}
			break;
	}
	console.log("Utils.isSignedIn=" + isSignedIn);
	return isSignedIn;
}

export function setupCookies(cookiesData, mode) {
	switch (mode) {
		case MODE.ADMIN:
			Cookies.set("admin-id-133-1", cookiesData.adminId, {
				expires: IN_HALF_A_DAY_EXP,
			});
			Cookies.set("adnim-username-133-1", cookiesData.username, {
				expires: IN_HALF_A_DAY_EXP,
			});
			break;
		case MODE.USER:
		default:
			Cookies.set("signin-comm-id-133-1", cookiesData.commId, {
				expires: IN_HALF_A_DAY_EXP,
			});
			Cookies.set("signin-comm-code-133-1", cookiesData.commCode, {
				expires: IN_HALF_A_DAY_EXP,
			});
			Cookies.set("signin-comm-name-133-1", cookiesData.commName, {
				expires: IN_HALF_A_DAY_EXP,
			});
			Cookies.set("signin-username-133-1", cookiesData.username, {
				expires: IN_HALF_A_DAY_EXP,
			});

			console.log(
				`COOKIES: ${Cookies.get("signin-comm-id-133-1")}, ${Cookies.get(
					"signin-comm-code-133-1"
				)}, ${Cookies.get("signin-comm-name-133-1")}, ${Cookies.get(
					"signin-username-133-1"
				)}`
			);
			break;
	}
}


// deletes old invalid cookies until all users are up to date
export function removeOldCookies() {
	console.log(
		"deletes old invalid cookies until all users are up to date"
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
