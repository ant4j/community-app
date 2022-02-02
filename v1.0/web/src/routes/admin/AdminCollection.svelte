<script>
	import BackComp from "../../components/BackComp.svelte";
	import ContListComp from "../../components/ContListComp.svelte";
	import { push } from "svelte-spa-router";

	export let params = {};
</script>

<div class="mb-3 text-start">
	<BackComp />
</div>

<div class="mb-3">
	<h5>Raccolta</h5>
</div>

<div class="mb-3">
	<ContListComp collId={params.collId} />
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button
			class="btn btn-primary"
			type="button"
			on:click={() => push("/admin-content-new/" + params.collId)}
			>Aggiungi un nuovo contenuto <i
				class="bi bi-file-earmark-plus"
			/></button
		>
	</div>
</div>
