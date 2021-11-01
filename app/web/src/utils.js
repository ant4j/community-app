import Cookies from "js-cookie";

export const Mode = Object.freeze({ USER: 1, ADMIN: 2 });

export function isSignedIn(mode) {
	let isSignedIn = false;
	switch (mode) {
		case Mode.ADMIN:
			if (Cookies.get("admin-id-133-1") !== undefined) {
				isSignedIn = true;
			}
			break;
		case Mode.USER:
		default:
			if (Cookies.get("signin-comm-id-133-1") !== undefined) {
				isSignedIn = true;
			}
			break;
	}
	console.log("Utils.isSignedIn=" + isSignedIn);
	return isSignedIn
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
