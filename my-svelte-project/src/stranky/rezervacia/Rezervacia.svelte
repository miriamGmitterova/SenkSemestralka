<script>
    import { onMount } from 'svelte';
    import { writable } from 'svelte/store';
    import { formatUnixTimestamp } from '../../utilities/DateTimeFormatter.js';
    import {navigate} from "svelte-routing";

    const apiData = writable([]);

    const ulozeneData = JSON.parse(localStorage.getItem('aktualnyPouzivatel'));


    onMount(async () => {
        try {
            const response = await fetch('http://localhost:8080/rezervacia/zoznam');
            if (!response.ok) {
                throw new Error('Chyba v requeste');
            }
            const data = await response.json();
            apiData.set(data);
        } catch (error) {
            console.error('Chyba', error);
        }
    });

    function presmerujVytvorRezervaciu() {
        window.location.href = '/rezervacia/vytvor-rezervaciu';
    }

    function presmerujNaPrispevok(id) {
        navigate(`/rezervacia/${id}`);
    }
</script>

<main class="full-screen" style="max-height: calc(100vh - 150px); overflow-y: auto;">
    <h1>Rezervácie</h1>

    {#if ulozeneData && ulozeneData.rola === 'ADMIN'}
    <button class="tlacidlo" on:click={presmerujVytvorRezervaciu}>Vytvoriť novú rezervaciu</button>
    {:else}
        <h1>Pre vytvorenie rezervácie musíte byť prihlásený!</h1>
    {/if}
    <div class="medzera"></div>

    {#each $apiData as element}
        <div class="prispevky">
            <button class="rezervacia-element" on:click={() => presmerujNaPrispevok(element.id)}>
                <div class="rezervacia-metadata">
                    <p><strong>Čas rezervácie:</strong> {element.cas}</p>
                    <p><strong>Dátum rezervácie:</strong> {formatUnixTimestamp(element.datum)}</p>
                    <p><strong>Autor:</strong> {element.vytvoril.meno}</p>
                    <p><strong>Typ:</strong> {element.typAkcie}</p>
                </div>
                <p><strong>Počet ľudí:</strong> {element.pocetLudi}</p>
                <p><strong>Poznámka:</strong> {element.poznamka}</p>
            </button>
        </div>
    {/each}
</main>

<style>
    .full-screen {
        width: 100%;
        height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        padding: 20px;
    }

    .prispevky {
        margin-bottom: 20px;
    }

    .rezervacia-element {
        width: 100%;
        border: 1px solid #ccc;
        padding: 10px;
        margin-bottom: 10px;
        background-color: #f0f8ff;
    }

    .rezervacia-metadata p {
        margin: 5px 0;
    }

    .tlacidlo {
        background-color: #004080;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-bottom: 10px;
    }

    .tlacidlo:hover {
        background-color: #00254d;
    }

    .medzera {
        height: 20px;
    }
</style>