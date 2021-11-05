<script>
	import { push } from "svelte-spa-router";
	import BackComp from "../../components/BackComp.svelte";
	import CollListComp from "../../components/CollListComp.svelte";

	export let params = {};
</script>

<div class="mb-3 text-center">
	<BackComp />
</div>

<div class="mb-3">
	<h5>Raccolte in (NOME_COMMUNITY)</h5>
</div>

<div class="mb-3">
	<CollListComp route="/admin-collection/" commId={params.commId} />
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button
			class="btn btn-primary"
			type="button"
			on:click={() => push("/admin-collection-new/" + params.commId)}
			>Crea una Nuova Raccolta <i class="bi bi-folder-plus" /></button
		>
	</div>
</div>
