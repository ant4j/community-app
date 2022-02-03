<script>
	import { onMount } from "svelte";
	import { push, replace } from "svelte-spa-router";
	import httpStatus from "http-status";

	import appconfig from "../appconfig.json";
	import cookies from "../handlers/cookies";
	import { t } from "svelte-i18n";

	import PasswordField from "../components/PasswordField.svelte";
	import ErrorModal from "../components/ErrorModal.svelte";
	// import LocaleSwitch from "../components/LocaleSwitch.svelte";

	export let params = {
		communityCode: "",
	};

	let view = {
		display: false,
		errorModal: undefined,
	};

	let model = {
		communityId: 0,
		communityCode: params.communityCode,
		communityName: "",
		watchword: "",
	};

	onMount(() => init());

	function init() {
		if (!cookies.areCookiesSetup()) {
			getCommunity();
		} else {
			replace("/home");
		}
	}

	async function getCommunity() {
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.community;
		let endpoint = `${baseUrl}${path}/${model.communityCode}`;
		let res = await fetch(endpoint);
		if (res.status == httpStatus.OK) {
			let jsonRes = await res.json();
			model.communityId = jsonRes.id;
			model.communityName = jsonRes.name;
			view.display = true;
		} else if (res.status == httpStatus.NOT_FOUND) {
			replace("/not-found");
		} else {
			replace("/not-found");
		}
	}

	async function signin() {
		if (model.watchword == "") {
			view.errorModal.show($t("access-failed"), $t("watchword-forgot"));
			return;
		}
		let jsonBody = {
			communityId: model.communityId,
			watchword: model.watchword,
		};
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.authentication;
		let endpoint = `${baseUrl}${path}`;
		let res = await fetch(endpoint, {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(jsonBody),
		});
		if (res.status == httpStatus.OK) {
			let jsonRes = await res.json();
			cookies.removeOldCookies();
			cookies.setupCookies({
				communityId: model.communityId,
				communityCode: model.communityCode,
				communityName: model.communityName,
				username: jsonRes.username,
			});
			push("/home");
		} else if (res.status == httpStatus.UNAUTHORIZED) {
			view.errorModal.show($t("access-failed"), $t("watchword-wrong"));
		} else {
			view.errorModal.show(
				$t("internal-error"),
				$t("internal-error-msg")
			);
		}
	}
</script>

{#if view.display}
	<div class="mb-3">
		<h5>{$t("access")}</h5>
	</div>

	<div class="mb-3">
		<div class="card text-white bg-primary mb-3">
			<div class="card-body">
				<p class="card-text">
					<i class="bi bi-people-fill" />
					{model.communityName}
				</p>
			</div>
		</div>
	</div>

	<div class="mb-3">
		<PasswordField
			id="watchword"
			bind:value={model.watchword}
			label={$t("watchword-label")}
			placeholder={$t("watchword")}
			help={$t("watchword-help-msg")}
		/>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				type="button"
				class="btn btn-primary"
				on:click={() => signin()}
			>
				{$t("enter")} <i class="bi bi-door-open" />
			</button>
		</div>
	</div>

	<!-- <div class="mb-3 text-center text-small">
		<LocaleSwitch />
	</div> -->

	<div class="mb-3">
		<ErrorModal bind:this={view.errorModal} />
	</div>
{/if}
