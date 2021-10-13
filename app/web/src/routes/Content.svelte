<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import BackBtn from "../components/BackBtn.svelte";
	import Cookies from "js-cookie";
	import { push, pop, replace } from "svelte-spa-router";

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

	async function proposeContent() {
		let proposalData = JSON.stringify({ username: Cookies.get("signin-username"), commId: Cookies.get("signin-comm-id"), collId: params.collId, contId: params.contId });
		console.log("proposeContent, proposalData: " + proposalData);
		let res = await fetch(endpoint.service.proposeContent, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: proposalData,
		});
		let json = await res.json();
		console.log("proposeContent, json: " + JSON.stringify(json));
		push("/home");
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
		<button
			class="btn btn-primary"
			type="button"
			on:click={() => proposeContent()}
			><i class="bi bi-megaphone" /> Proponi</button
		>
	</div>
</div>
