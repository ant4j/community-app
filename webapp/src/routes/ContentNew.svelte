<script>
	import endpoint from "../endpoint.json";
	import BackBtn from "../components/BackBtn.svelte";

	let disabled = "disabled";
	let fileToScan;
	let scannedText;
	let contentTitle;

	function onFileSelected(e) {
		fileToScan = e.target.files[0];
		disabled = "";
	}

	async function scanText() {
		let formData = new FormData();
		formData.append("file", fileToScan);
		let res = await fetch(endpoint.external_service.ocr.url, {
			method: "post",
			headers: {
				apikey: endpoint.external_service.ocr.apikey,
			},
			body: formData,
		});
		let json = await res.json();
		scannedText = json.ParsedResults[0].ParsedText;
		contentTitle = scannedText.split("\n")[0];
	}

	function saveToCollection() {
		// TODO
		console.log("che cosa vado a salvare: " + scannedText);
	}
</script>

<div class="mb-3 text-center">
	<BackBtn />
</div>

<div class="mb-3">
	<label for="scan-photo" class="form-label">Acquisizione testo da foto</label
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
			on:click={() => scanText()}>Acquisisci</button
		>
	</div>
</div>

<hr />

<div class="mb-3">
	<input
		type="text"
		class="form-control"
		placeholder="Titolo"
		bind:value={contentTitle}
	/>
</div>

<div class="mb-3">
	<textarea
		class="form-control"
		rows="8"
		aria-describedby="content-text-help"
		placeholder="Testo.."
		bind:value={scannedText}
	/>
	<div class="form-text" id="content-text-help">
		Se hai acquisito da una foto puoi correggere qualcosina, se necessario.
		Oppure puoi anche inserire il titolo e tutto il testo manualmente.
	</div>
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button
			class="btn btn-primary"
			type="button"
			on:click={() => saveToCollection()}>Salva nella raccolta</button
		>
	</div>
</div>
