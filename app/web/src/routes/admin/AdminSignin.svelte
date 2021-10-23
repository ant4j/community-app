<script>
	import BackBtn from "../../components/BackBtn.svelte";
	import endpoint from "../../endpoint.json";
	import { push, pop, replace } from "svelte-spa-router";
	import Cookies from "js-cookie";

	let credentials = {};

	async function signinAdmin() {
		console.log("signinAdmin, credentials: " + JSON.stringify(credentials));
		let res = await fetch(endpoint.service.signinAdmin, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(credentials),
		});
		let json = await res.json();
		console.log("signinAdmin, json: " + JSON.stringify(json));
		if (json.status.code == "1") {
			const inHalfADay = 0.5;

			Cookies.set("admin-id-133-1", json.adminId, {
				expires: inHalfADay,
			});
			Cookies.set("adnim-username-133-1", credentials.username, {
				expires: inHalfADay,
			});

			push("/admin-dashboard");
		} else {
			alert("errore");
			console.log("errore");
		}
	}
</script>

<div class="mb-3 text-center">
	<BackBtn path="/signin" />
</div>

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
	<div class="form-text" id="admin-username-help">Nome Utente.</div>
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
	<div class="form-text" id="admin-password-help">Password.</div>
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
