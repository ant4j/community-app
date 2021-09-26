<script>
    import { push, pop, replace } from "svelte-spa-router";
</script>

<div class="mb-3 text-center">
    <button type="button" class="btn btn-outline-primary" on:click={() => pop()}
        >indietro</button
    >
</div>

<div class="mb-3">
    <label for="load-photo" class="load-photo-label">Caricamento Foto</label>
    <input class="form-control" type="file" aria-describedby="load-photo-help" id="load-photo" />
    <div class="form-text" id="load-photo-help">
        Scegli una Foto del testo che vuoi caricare.
      </div>
</div>

<div class="mb-3">
    <div class="d-grid gap-2">
        <button class="btn btn-primary" type="button">Carica</button>
    </div>
</div>
