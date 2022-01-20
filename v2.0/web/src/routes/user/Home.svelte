<script>
	import { push } from "svelte-spa-router";

	import { userCookies } from "../../handlers/cookies";

	function exit() {
		let communityCode = userCookies.getUserCookies().userCommunityCode;
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
