<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import BackBtn from "../components/BackBtn.svelte";

	export let params = {};

	let textLines = [];

	onMount(() => retrieveContent());

	async function retrieveContent() {
		console.log(
			"retrieveContent, params.collId: " +
				params.collId +
				", params.contId: " +
				params.contId
		);
		let res = await fetch(
			endpoint.service.getContent +
				"?collId=" +
				params.collId +
				"&contId=" +
				params.contId
		);
		let json = await res.json();

		let text = json.text;
		textLines = text.split("\n");
	}
</script>

<div class="mb-3 text-center">
	<BackBtn />
</div>

<div class="mb-3 text-center">
	{#each textLines as textLine}
		<div>{textLine}</div>
	{/each}
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button class="btn btn-primary" type="button"
			><i class="bi bi-megaphone" /> Proponi</button
		>
	</div>
</div>
