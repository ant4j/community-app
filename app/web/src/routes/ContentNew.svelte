<script>
	import endpoint from "../endpoint.json";
	import { pop, replace } from "svelte-spa-router";
	import BackComp from "../components/BackComp.svelte";
	import { onMount } from "svelte";
	import { isSignedIn } from "../utils";

	export let params = {};

	let disabled = "disabled";
	let fileToScan;

	let contentToCreate = { collId: params.collId };

	let isSignedInCheck = false;

	onMount(() => init());

	function init() {
		isSignedInCheck = isSignedIn();
		if (!isSignedInCheck) {
			replace("/signin");
		}
	}

	function onFileSelected(e) {
		fileToScan = e.target.files[0];
		disabled = "";
	}

	async function scanText() {
		let formData = new FormData();
		formData.append("file", fileToScan);
		formData.append("language", "ita");
		formData.append("OCREngine", 2);
		let res = await fetch(endpoint.external_service.ocr.url, {
			method: "post",
			headers: {
				apikey: endpoint.external_service.ocr.apikey,
			},
			body: formData,
		});
		let json = await res.json();
		contentToCreate.text = json.ParsedResults[0].ParsedText;
		contentToCreate.title = contentToCreate.text.split("\n")[0];
	}

	async function createContent() {
		console.log(
			"createContent, contentToCreate: " + JSON.stringify(contentToCreate)
		);

		let res = await fetch(endpoint.service.addContent, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(contentToCreate),
		});
		let json = await res.json();
		console.log("createContent, json: " + JSON.stringify(json));
		if (json.status.code == "1") {
			pop();
		} else if (json.status.code == "0") {
			alert("Il content esiste gia'");
		}
	}
</script>

{#if isSignedInCheck}
	<div class="mb-3 text-center">
		<BackComp showHomeBtn="true" />
	</div>

	<div class="mb-3">
		<h5>Aggiungi un Nuovo Contenuto</h5>
	</div>

	<div class="mb-3">
		<label for="scan-photo" class="form-label"
			>Acquisizione testo da foto</label
		>
		<input
			class="form-control"
			type="file"
			aria-describedby="scan-photo-help"
			on:change={(e) => onFileSelected(e)}
			id="scan-photo"
		/>
		<div class="form-text" id="scan-photo-help">
			Scegli una Foto del testo che vuoi acquisire.
		</div>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-primary {disabled}"
				type="button"
				on:click={() => scanText()}
				>Acquisisci <i class="bi bi-lightning" /></button
			>
		</div>
	</div>

	<hr />

	<div class="mb-3">
		<input
			type="text"
			class="form-control"
			placeholder="Titolo"
			bind:value={contentToCreate.title}
		/>
	</div>

	<div class="mb-3">
		<textarea
			class="form-control"
			rows="8"
			aria-describedby="content-text-help"
			placeholder="Testo.."
			bind:value={contentToCreate.text}
		/>
		<div class="form-text" id="content-text-help">
			Se hai acquisito da una foto puoi correggere qualcosina, se
			necessario. Oppure puoi anche inserire il titolo e tutto il testo
			manualmente.
		</div>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-primary"
				type="button"
				on:click={() => createContent()}
				>Aggiungi alla raccolta <i class="bi bi-stars" /></button
			>
		</div>
	</div>
{/if}
