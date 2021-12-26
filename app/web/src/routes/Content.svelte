<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import BackComp from "../components/BackComp.svelte";
	import Cookies from "js-cookie";
	import httpStatus from "http-status";
	import { push, replace } from "svelte-spa-router";
	import { isSignedIn } from "../utils";

	export let params = {};

	let title = "";
	let text = "";

	let view = false;

	onMount(() => init());

	function init() {
		if (isSignedIn()) {
			retrieveContent();
			view = true;
		} else {
			replace("/");
		}
	}

	async function retrieveContent() {
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
		let res = await fetch(endpoint.service.proposeContent, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: proposalData,
		});
		if (res.status == httpStatus.CREATED) {
			push("/home");
		}
	}
</script>

{#if view}
	{#if params.context == "1"}
		<div class="mb-3 text-center">
			<BackComp />
		</div>
		<div class="mb-3 text-center">
			<div class="card bg-light text-muted participation-msg-card">
				<div class="card-body">
					<img
						src="images/party.png"
						width="35"
						height="35"
						alt="party"
					/>
					Partecipazione in corso
					<img
						src="images/party.png"
						width="35"
						height="35"
						alt="party"
					/>
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
					on:click={() => push("/balloon")}
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
