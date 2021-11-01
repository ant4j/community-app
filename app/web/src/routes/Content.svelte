<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import BackComp from "../components/BackComp.svelte";
	import Cookies from "js-cookie";
	import { push, pop, replace } from "svelte-spa-router";
	import Utils from "../utils";

	export let params = {};

	let title = "";
	let text = "";

	onMount(() => init());

	function init() {
		if (Utils.isSignedIn()) {
			retrieveContent();
		} else {
			replace("/signin");
		}
	}

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

		title = json.title;
		text = json.text;
	}

	async function proposeContent() {
		let proposalData = JSON.stringify({
			username: Cookies.get("signin-username-133-1"),
			commId: Cookies.get("signin-comm-id-133-1"),
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
		push("/");
	}
</script>

{#if Utils.isSignedIn()}
	{#if params.context == "1"}
		<div class="mb-3 text-center">
			<BackComp />
		</div>
		<div class="mb-3 text-center">
			<div class="card bg-light text-muted participation-msg-card">
				<div class="card-body">
					Partecipazione in corso
					<i class="bi bi-hourglass-split" />
				</div>
			</div>
		</div>
	{:else}
		<div class="mb-3 text-center">
			<BackComp showHomeBtn="true" />
		</div>
	{/if}

	<div class="mb-3">
		<h5 class="fw-bolder">{title}</h5>
	</div>

	<div class="mb-3">
		<div class="content-text">
			{text}
		</div>
	</div>

	{#if params.context == "1"}
		<div class="mb-3">
			<div class="d-grid gap-2">
				<button
					class="btn btn-primary"
					type="button"
					on:click={() => push("/")}
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
{/if}

<style>
	.content-text {
		white-space: pre;
	}
</style>
