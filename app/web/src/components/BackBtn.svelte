<script>
	import { pop, replace } from "svelte-spa-router";

	export let path = "";

	function goBack() {
		if (path != "") {
			replace(path);
		} else {
			pop();
		}
	}
</script>

<div class="text-start">
	<button
		type="button"
		class="btn btn-link text-decoration-none"
		on:click={() => goBack()}
		><i class="bi bi-arrow-left-short" /> indietro</button
	>
</div>
