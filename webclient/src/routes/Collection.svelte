<script>
    import { onMount } from "svelte";
    import endpoint from "../endpoint.json";
    import { push, replace } from "svelte-spa-router";
    import BackBtn from "../components/BackBtn.svelte";

    export let params = {};

    let contentArr = [];

    onMount(() => retrieveContents(params.idCollection));

    function retrieveContents(idCollection) {
        fetch(endpoint.service.getContents + "?idCollection=" + idCollection)
            .then((r) => r.json())
            .then((d) => {
                contentArr = d;
            });
    }
</script>

<div class="mb-3 text-center">
    <BackBtn />
</div>

<div class="mb-3">
    <label for="content-list" class="form-label" id="content-list-label" />
    <div class="list-group" id="content-list">
        {#each contentArr as contentEl}
            <button
                type="button"
                class="list-group-item list-group-item-action"
                on:click={() => push("/content")}>{contentEl.name}</button
            >
        {/each}
    </div>
</div>

<div class="mb-3">
    <div class="d-grid gap-2">
        <button
            class="btn btn-primary"
            type="button"
            id="content-btn-create"
            on:click={() => push("/content-new")}>Aggiungi Nuovo</button
        >
    </div>
</div>
