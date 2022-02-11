<script>
	import { onMount } from "svelte";
	import { push, replace } from "svelte-spa-router";
	import httpStatus from "http-status";
	import moment from "moment";

	import appconfig from "../appconfig.json";
	import cookies from "../handlers/cookies";

	import CollectionList from "../components/CollectionList.svelte";

	let view = {
		display: false,
	};

	let model = {
		proposal: { isPresent: false },
	};

	onMount(() => init());

	function init() {
		if (cookies.areCookiesSetup()) {
			getLastProposal();
		} else {
			replace("/");
		}
	}

	async function getLastProposal() {
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.lastProposal;
		let endpoint = `${baseUrl}${path}/${cookies.getCommunityIdCookie()}`;
		let res = await fetch(endpoint, {
			method: "GET",
		});
		if (res.status == httpStatus.OK) {
			let jsonRes = await res.json();
			model.proposal = jsonRes;
		}
		view.display = true;
	}

	function isLyrics() {
		return model.proposal.collectionType == appconfig.collectionType.lyrics;
	}

	function participate() {
		let context = appconfig.contentContext.participation,
			collectionId = model.proposal.collectionId,
			contentId = model.proposal.contentId;
		push(`/content/${context}/${contentId}`);
	}

	function exit() {
		let communityCode = cookies.getCommunityCodeCookie();
		cookies.removeCookies();
		push(`/signin/${communityCode}`);
	}
</script>

{#if view.display}
	<div class="mb-3">
		<h5>
			<i class="bi bi-house" /> Home &middot;
			<span class="fw-bolder">
				{cookies.getCommunityNameCookie()}
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
				{cookies.getUsernameCookie()}
			</span>
		</div>
	</div>

	<div class="mb-3">
		<div class="card">
			{#if model.proposal.isPresent}
				<div class="card-header">
					Proposta del momento
					<div class="text-muted text-small">
						Ultimo aggiornamento:
						{moment(
							model.proposal.proposedOn,
							"YYYY-MM-DDTHH:mm:ss.SSSZ"
						).format("LLL")}
					</div>
				</div>
				<div class="card-body">
					<p class="card-title">
						<span class="fw-bolder">{model.proposal.username}</span>
						propone:
					</p>
					<p class="card-text text-center fs-5">
						<i class="bi bi-megaphone" />
						&laquo;
						{#if isLyrics()}
							Cantiamo
						{:else}
							Leggiamo
						{/if}
						<span class="fst-italic">
							{model.proposal.contentTitle}
						</span> ! &raquo;
					</p>
					<div class="d-grid gap-2">
						<button
							class="btn btn-primary"
							type="button"
							on:click={() => participate()}
						>
							Partecipa
							<i class="bi bi-emoji-laughing" />
						</button>
					</div>
				</div>
			{:else}
				<div class="card-header">
					Proposta del momento
					<div class="text-muted text-small">&nbsp;</div>
				</div>
				<div class="card-body">
					<p class="card-title text-center">Nessuna proposta</p>
				</div>
			{/if}
		</div>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				id="retrieve-latest-proposal-btn"
				class="btn btn-primary"
				type="button"
				on:click={() => getLastProposal()}
			>
				Recupera proposta più recente <i class="bi bi-download" />
			</button>
		</div>
	</div>

	<hr />

	<div class="mb-3">
		<CollectionList
			route="/collection/"
			communityId={cookies.getCommunityIdCookie()}
		/>
	</div>

	<div class="mb-3">
		<div class="btn-group" role="group">
			<button
				type="button"
				class="btn btn-outline-primary"
				on:click={() => exit()}
			>
				Esci
			</button>
		</div>
	</div>
{/if}

<style>
	#retrieve-latest-proposal-btn {
		margin-left: 20px;
		margin-right: 20px;
	}
</style>
