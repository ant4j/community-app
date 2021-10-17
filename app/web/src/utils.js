const Cookies = require("js-cookie");

exports.isSignedIn = () => {
	let signinCommId = Cookies.get("signin-comm-id-133-1");
	console.log("signinCommId=" + signinCommId);
	if (signinCommId !== undefined) {
		console.log("Utils.isSignedIn=true");
		return true;
	}
	console.log("Utils.isSignedIn=false");
	return false
}

// deletes old invalid cookies until all users are up to date
exports.removeOldCookies = () => {
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
