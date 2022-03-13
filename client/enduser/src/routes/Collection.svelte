<script>
	import { onMount } from "svelte";
	import { replace } from "svelte-spa-router";

	import cookies from "../handlers/cookies";

	import BackButton from "../components/BackButton.svelte";
	import ContentList from "../components/ContentList.svelte";

	export let params = {
		collectionId: 0,
	};

	let view = {
		display: false,
	};

	onMount(() => init());

	function init() {
		if (cookies.areCookiesSetup()) {
			view.display = true;
		} else {
			replace("/");
		}
	}
</script>

{#if view.display}
	<div class="mb-3 text-start">
		<BackButton />
	</div>

	<div class="mb-3">
		<h5>Raccolta</h5>
	</div>

	<div class="mb-3">
		<ContentList collectionId={params.collectionId} />
	</div>
{/if}
