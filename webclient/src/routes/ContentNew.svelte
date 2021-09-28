<script>
    import { push, pop, replace } from "svelte-spa-router";

    let disabled = "disabled";
    let fileToScan;
    let scannedText;

    function onFileSelected(e) {
        fileToScan = e.target.files[0];
        disabled = "";
    }

    function scanText() {
        let formData = new FormData();
        formData.append("file", fileToScan);

        // "apikey": "5a64d478-9c89-43d8-88e3-c65de9999580"
        fetch("https://api.ocr.space/parse/image", {
            method: "post",
            headers: {
                apikey: "helloworld",
            },
            body: formData,
        })
            .then((res) => res.json())
            .then((data) => {
                scannedText = data.ParsedResults[0].ParsedText;
                console.log("text: " + scannedText);
            });
    }

    function saveToCollection() {
        console.log("che cosa vado a salvare: " + scannedText);
    }
</script>

<div class="mb-3 text-center">
    <button type="button" class="btn btn-outline-primary" on:click={() => pop()}
        >indietro</button
    >
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

<div class="mb-3">
    <label
        for="content-text"
        class="form-label"
        aria-describedby="content-text-help">Testo</label
    >
    <textarea class="form-control" rows="10" bind:value={scannedText} id="content-text" />
    <div class="form-text" id="content-text-help">
        Se hai acquisito da una foto puoi correggere qualcosina, se necessario.
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
