<script>
  import { onMount } from "svelte";
  import srvEndpoints from "../service-endpoints.json";
  import { push, pop, replace } from "svelte-spa-router";

  let commtyArr = [];
  let signinData = {};

  onMount(() => retrieveCommunities());

  function retrieveCommunities() {
    fetch(srvEndpoints.getCommunities)
      .then((res) => res.json())
      .then((data) => {
        commtyArr = data;
        signinData.communityId = commtyArr[0].id;
      });
  }

  function signin() {
    fetch(srvEndpoints.signin, {
      method: "post",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(signinData),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log("data: " + JSON.stringify(data));

        if (data.status.code == "1") {
          // TODO settare il cookie con idUtente/nomeUtente e idCommunity/nomeCommunity
          push("/home");
        } else {
          alert("errore");
          console.log("errore");
        }
      });
  }
</script>

<div class="mb-3">
  <h5>Accesso</h5>
</div>

<div class="mb-3">
  <label for="commty-list" class="form-label">Elenco delle Community</label>
  <div class="list-group" aria-describedby="commty-list-help" id="commty-list">
    {#each commtyArr as commtyEl}
      <button
        type="button"
        class="list-group-item list-group-item-action"
        class:active={signinData.communityId === commtyEl.id}
        on:click={() => (signinData.communityId = commtyEl.id)}
        >{commtyEl.name}</button
      >
    {/each}
  </div>
  <div class="form-text" id="commty-list-help">
    Seleziona la Community in cui vuoi entrare.
  </div>
</div>

<div class="mb-3">
  <input
    type="text"
    class="form-control"
    placeholder="Parola d'ordine della Community"
    aria-describedby="watchword-help"
    bind:value={signinData.watchword}
    id="watchword"
  />
  <div class="form-text" id="watchword-help">
    Serve per entrare nella community selezionata.
  </div>
</div>

<hr />

<div class="mb-3">
  <label for="commty-username" class="form-label"
    >La tua Identità nella Community</label
  >
  <input
    type="text"
    class="form-control"
    maxlength="3"
    placeholder="Nome Utente"
    aria-describedby="commty-username-help"
    bind:value={signinData.username}
    id="commty-username"
  />
  <div class="form-text" id="commty-username-help">
    Prime 2 lettere del tuo nome + prima lettera del tuo cognome.
    <br />
    Es.: Mario Rossi = mar.
  </div>
</div>

<div class="mb-3">
  <div class="d-grid gap-2">
    <button
      class="btn btn-primary"
      type="button"
      on:click={() => signin()}
      id="commty-btn-enter">Entra</button
    >
  </div>
</div>
