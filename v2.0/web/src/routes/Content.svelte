<script>
	import { onMount } from "svelte";
	import { push, replace } from "svelte-spa-router";
	import httpStatus from "http-status";

	import appconfig from "../appconfig.json";
	import { userCookies } from "../handlers/userCookies";

	import BackButton from "../components/BackButton.svelte";
	import HomeButton from "../components/HomeButton.svelte";

	export let params = {};

	let view = {
		display: false,
	};

	let model = {
		title: "",
		text: "",
	};

	onMount(() => init());

	function init() {
		if (userCookies.areUserCookiesSetup()) {
			getContent();
			view.display = true;
		} else {
			replace("/");
		}
	}

	async function getContent() {
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.contentText;
		let endpoint = `${baseUrl}${path}/${params.contentId}/${params.collectionId}`;
		let res = await fetch(endpoint);
		let json = await res.json();
		//TODO fare controllo se lo status e' OK
		model.title = json.contentTitle;
		model.text = json.text;
	}

	async function proposeContent() {
		let jsonReq = {
			communityId: userCookies.getUserCommunityIdCookie(),
			contentId: params.contentId,
			username: userCookies.getUserUsernameCookie(),
		};
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.proposal;
		let endpoint = `${baseUrl}${path}`;
		let res = await fetch(endpoint, {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(jsonReq),
		});
		if (res.status == httpStatus.CREATED) {
			push("/home");
		}
	}
</script>

{#if view.display}
	{#if params.context == appconfig.contentContext.participation}
		<div class="mb-3 text-start">
			<BackButton />
		</div>
		<div class="mb-3 text-center">
			<div class="card bg-light text-muted participation-msg-card">
				<div class="card-body">
					<img
						src="img/party.png"
						width="35"
						height="35"
						alt="party"
					/>
					Partecipazione in corso
					<img
						src="img/party.png"
						width="35"
						height="35"
						alt="party"
					/>
				</div>
			</div>
		</div>
	{:else}
		<div class="mb-3 text-start">
			<BackButton />
			<HomeButton />
		</div>
	{/if}

	<div class="mb-3">
		<h5 class="fw-bolder">{model.title}</h5>
	</div>

	<div class="mb-3">
		<div class="content-text">
			{model.text}
		</div>
	</div>

	{#if params.context == appconfig.contentContext.participation}
		<div class="mb-3">
			<div class="d-grid gap-2">
				<button
					class="btn btn-primary"
					type="button"
					on:click={() => push("/balloon")}
				>
					Abbiamo finito! <i class="bi bi-alarm" />
				</button>
			</div>
		</div>
	{:else}
		<div class="mb-3">
			<div class="d-grid gap-2">
				<button
					class="btn btn-primary"
					type="button"
					on:click={() => proposeContent()}
				>
					Proponi <i class="bi bi-megaphone" />
				</button>
			</div>
		</div>
	{/if}
{/if}

<style>
	.content-text {
		white-space: pre;
	}
</style>
