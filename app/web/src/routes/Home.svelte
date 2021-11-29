<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push, replace } from "svelte-spa-router";
	import Cookies from "js-cookie";
	import { isSignedIn, removeOldCookies } from "../utils";
	import CollListComp from "../components/CollListComp.svelte";

	let proposal = {};

	let view = false;

	onMount(() => init());

	function init() {
		removeOldCookies();
		if (isSignedIn()) {
			retrieveLastProposal();
		} else {
			replace("/");
		}
	}

	async function retrieveLastProposal() {
		let res = await fetch(
			endpoint.service.getLastProposal +
				"?commId=" +
				Cookies.get("signin-comm-id-133-1")
		);
		let json = await res.json();
		console.log("retrieveLastProposal, json: " + JSON.stringify(json));

		if (json.status.code == "1") {
			proposal = json.data;
		} else {
			proposal = {};
		}
		view = true;
	}

	function exit() {
		let commCode = Cookies.get("signin-comm-code-133-1");
		Cookies.remove("signin-comm-id-133-1");
		Cookies.remove("signin-comm-code-133-1");
		Cookies.remove("signin-comm-name-133-1");
		Cookies.remove("signin-username-133-1");
		push("/signin/" + commCode);
	}
</script>

{#if view}
	<div class="mb-3">
		<h5>
			<i class="bi bi-house" /> Home /
			<span class="fw-bolder">
				{Cookies.get("signin-comm-name-133-1")}
			</span>
		</h5>
	</div>

	<div class="mb-3">
		<div>
			<i class="bi bi-emoji-sunglasses" />
			Benvenuta/o in questa community
		</div>
		<div>
			<i class="bi bi-person-circle" /> Il tuo nome utente è
			<span class="fw-bolder">
				{Cookies.get("signin-username-133-1")}
			</span>
		</div>
	</div>

	<div class="mb-3">
		<div class="card">
			{#if Object.keys(proposal).length}
				<div class="card-header">
					Proposta del momento
					<div class="text-muted text-small">
						Ultimo aggiornamento: 23 Settembre 2021 13:52
					</div>
				</div>
				<div class="card-body">
					<p class="card-title">
						<span class="fw-bolder">{proposal.username}</span> propone:
					</p>
					<p class="card-text text-center fs-5">
						<i class="bi bi-megaphone" />
						&laquo; {#if proposal.collectionType == "0"}
							Cantiamo
						{:else}
							Leggiamo
						{/if}
						<span class="fst-italic">{proposal.contentTitle}</span> !
						&raquo;
					</p>
					<div class="d-grid gap-2">
						<!-- TODO centralizzare il context=0 nel endpoint.json che diventera' config.json -->
						<button
							class="btn btn-primary"
							type="button"
							on:click={() =>
								push(
									"/content/1/" +
										proposal.collId +
										"/" +
										proposal.contId
								)}
							>Partecipa <i
								class="bi bi-emoji-laughing"
							/></button
						>
					</div>
				</div>
			{:else}
				<div class="card-header">
					Proposta del momento
					<div class="text-muted text-small">&nbsp;</div>
				</div>
				<div class="card-body">
					<p class="card-title text-center">
						Nessuna proposta
					</p>
				</div>
			{/if}
		</div>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-primary"
				type="button"
				on:click={() => retrieveLastProposal()}
				id="update-proposal-btn"
				>Recupera proposta più recente <i class="bi bi-download" />
			</button>
		</div>
	</div>

	<hr />

	<div class="mb-3">
		<CollListComp
			route="/collection/"
			commId={Cookies.get("signin-comm-id-133-1")}
		/>
	</div>

	<div class="mb-3">
		<div class="btn-group" role="group">
			<!-- <button type="button" class="btn btn-outline-primary"
				>Cronologia</button
			> -->
			<button
				type="button"
				class="btn btn-outline-primary"
				on:click={() => exit()}>Esci</button
			>
		</div>
	</div>
{/if}

<style>
	#update-proposal-btn {
		margin-left: 20px;
		margin-right: 20px;
	}
</style>
