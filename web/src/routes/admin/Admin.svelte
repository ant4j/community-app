<script>
	import endpoint from "../../endpoint.json";
	import httpStatus from "http-status";
	import { push } from "svelte-spa-router";
	import { isSignedIn, setupCookies, MODE } from "../../utils";

	let credentials = {};

	async function signinAdmin() {
		let res = await fetch(endpoint.service.signinAdmin, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(credentials),
		});
		if (res.status == httpStatus.OK) {
			let json = await res.json();
			setupCookies(
				{ adminId: json.adminId, username: credentials.username },
				MODE.ADMIN
			);

			push("/admin-dashboard");
		} else if (res.status == httpStatus.UNAUTHORIZED) {
			alert("errore");
		}
	}
</script>

<svelte:head>
	<title>CommunityApp Admin</title>
</svelte:head>

<div class="mb-3">
	<h5>Accesso Area Amministratore</h5>
</div>

<div class="mb-3">
	<label for="admin-username" class="form-label">Nome Utente</label>
	<input
		type="text"
		class="form-control"
		placeholder="Nome Utente"
		aria-describedby="admin-username-help"
		bind:value={credentials.username}
		id="admin-username"
	/>
	<div class="form-text" id="admin-username-help" />
</div>

<div class="mb-3">
	<label for="admin-password" class="form-label">Password</label>
	<input
		type="password"
		class="form-control"
		placeholder="Password"
		aria-describedby="admin-password-help"
		bind:value={credentials.password}
		id="admin-password"
	/>
	<div class="form-text" id="admin-password-help" />
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button
			type="button"
			class="btn btn-primary"
			on:click={() => signinAdmin()}>Accedi</button
		>
	</div>
</div>
