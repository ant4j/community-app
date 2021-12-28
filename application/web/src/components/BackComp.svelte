<script>
	import { push, pop, replace } from "svelte-spa-router";

	export let path = "";
	export let showHomeBtn = false;

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
	{#if showHomeBtn}
		<button
			type="button"
			class="btn btn-link text-decoration-none"
			on:click={() => push("/home")}
			><i class="bi bi-house" /> Home</button
		>
	{/if}
</div>
