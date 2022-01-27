<script>
	import { push } from "svelte-spa-router";

	import { userCookies } from "../../handlers/userCookies";

	function exit() {
		let communityCode = userCookies.getUserCommunityCodeCookie();
		userCookies.removeUserCookies();
		push(`/signin/${communityCode}`);
	}
</script>

<div class="mb-3">
	<div class="btn-group" role="group">
		<button
			type="button"
			class="btn btn-outline-primary"
			on:click={() => exit()}>Esci</button
		>
	</div>
</div>
