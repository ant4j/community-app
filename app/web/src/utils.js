const Cookies = require("js-cookie");

exports.isSignedIn = () => {
	let signinCommId = Cookies.get("signin-comm-id-133-1");
	console.log("signinCommId="+signinCommId);
	if (signinCommId !== undefined) {
		console.log("Utils.isSignedIn="+true);
		return true;
	}
	console.log("Utils.isSignedIn="+false);
	return false
}
