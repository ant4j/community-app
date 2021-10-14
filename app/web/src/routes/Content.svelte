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
		let proposalData = JSON.stringify({
			username: Cookies.get("signin-username"),
			commId: Cookies.get("signin-comm-id"),
			collId: params.collId,
			contId: params.contId,
		});
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

{#if params.context == "1"}
	<div class="mb-3 text-center">
		<p class="take-part-msg">
			<i class="bi bi-hourglass-split" />
			Partecipazione in corso
			<i class="bi bi-hourglass-split" />
		</p>
	</div>
	<hr />
{:else}
	<div class="mb-3 text-center">
		<BackBtn />
	</div>
{/if}

<div class="mb-3 text-center">
	{#each textLines as textLine}
		<div>{textLine}</div>
	{/each}
</div>

{#if params.context == "1"}
	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-danger"
				type="button"
				on:click={() => push("/home")}
				>Abbiamo finito! <i class="bi bi-alarm" /></button
			>
		</div>
	</div>
{:else}
	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-primary"
				type="button"
				on:click={() => proposeContent()}
				>Proponi <i class="bi bi-megaphone" /></button
			>
		</div>
	</div>
{/if}

<style>
	.take-part-msg {
		border-radius: 15px;
		color: #595959;
		background: #e6e600;
	}
</style>
