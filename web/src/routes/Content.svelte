<script>
    import { push, pop, replace } from "svelte-spa-router";
</script>

<div class="mb-3 text-center">
    <button type="button" class="btn btn-outline-primary" on:click={() => pop()}
        >indietro</button
    >
</div>

<div class="mb-3">Titolo</div>

<div class="mb-3">
    Testo<br />
    Testo<br />
    Testo<br />
    Testo<br />
    Testo<br />
    Testo<br />
    Testo<br />
</div>

<div class="mb-3">
    <div class="d-grid gap-2">
        <button class="btn btn-primary" type="button"
            ><i class="bi bi-megaphone" /> Proponi</button
        >
    </div>
</div>
