<script>
	import { push, pop, replace } from "svelte-spa-router";

	let collArr = [
		{ id: "1", name: "Cantici", type: "c" },
		{ id: "2", name: "Letture", type: "l" },
	];
</script>

<div class="mb-3">
	<div class="card">
		<div class="card-header">
			Proposta del momento
			<div class="text-muted text-small">23 Settembre 2021 13:52</div>
		</div>
		<div class="card-body">
			<p class="card-title">
				<span class="fw-bolder">mar</span> propone:
			</p>
			<p class="card-text text-center fs-5">
				<i class="bi bi-megaphone" />
				<span class="fst-italic">"Cantiamo titolo_canto"</span>
			</p>
			<div class="d-grid gap-2">
				<button
					class="btn btn-primary"
					type="button"
					on:click={() => push("/content")}>Partecipa</button
				>
			</div>
		</div>
	</div>
</div>

<div class="mb-3">
	<label for="coll-list" class="form-label">Elenco Raccolte</label>
	<div class="list-group" aria-describedby="coll-list-help" id="coll-list">
		{#each collArr as collEl}
			<button
				type="button"
				class="list-group-item list-group-item-action"
				on:click={() => push("/collection/" + collEl.id)}
				>{collEl.name}</button
			>
		{/each}
	</div>
	<div class="form-text" id="coll-list-help">Seleziona una Raccolta.</div>
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button class="btn btn-primary" type="button"
			>Crea una Nuova Raccolta</button
		>
	</div>
</div>

<div class="mb-3">
	<div class="btn-group" role="group">
		<button type="button" class="btn btn-outline-primary">Cronologia</button
		>
		<button
			type="button"
			class="btn btn-outline-primary"
			on:click={() => pop()}>Esci</button
		>
	</div>
</div>
