<script>
	import { onMount } from "svelte";
	import { replace } from "svelte-spa-router";

	import { userCookies } from "../handlers/userCookies";

	import BackButton from "../components/BackButton.svelte";
	import ContentList from "../components/ContentList.svelte";

	export let params = {};

	let view = {
		display: false,
	};

	onMount(() => init());

	function init() {
		if (userCookies.areUserCookiesSetup()) {
			view.display = true;
		} else {
			replace("/");
		}
	}
</script>

{#if view.display}
<!-- TODO sostituire in tutti i BackButton text-center con text-start -->
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
