<script>
	import { onMount } from "svelte";
	import { replace } from "svelte-spa-router";
	import BackComp from "../components/BackComp.svelte";
	import ContListComp from "../components/ContListComp.svelte";
	import { isSignedIn } from "../utils";

	export let params = {};

	let view = false;

	onMount(() => init());

	function init() {
		if (isSignedIn()) {
			view = true;
		} else {
			replace("/");
		}
	}
</script>

{#if view}
	<div class="mb-3 text-center">
		<BackComp />
	</div>

	<div class="mb-3">
		<h5>Raccolta</h5>
	</div>

	<div class="mb-3">
		<ContListComp collId={params.collId} />
	</div>
{/if}
