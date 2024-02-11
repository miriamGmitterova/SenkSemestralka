<script>
    import { onMount } from 'svelte';
    import { writable } from 'svelte/store';
    import { formatUnixTimestamp, formatLocalDateTime } from '../../utilities/DateTimeFormatter.js';
    import {navigate} from "svelte-routing";

    const postData = writable(null);
    export let id;

    onMount(() => {
        console.log('params:', window.location.pathname.replace("/rezervacia/", ""));
        const id = window.location.pathname.replace("/rezervacia/", "");
        console.log('ID:', id);
        if (id) {
            fetchPostData(id);
        }
    });

    const storedData = JSON.parse(localStorage.getItem('aktualnyPouzivatel'));


    async function fetchPostData(id) {
        try {
            const response = await fetch(`http://localhost:8080/rezervacia/` + id);
            if (!response.ok) {
                throw new Error('Chyba');
            }
            const data = await response.json();
            postData.set(data);
            $postData.datum = formatUnixTimestamp($postData.datum );

        } catch (error) {
            console.error('Chyba', error);
        }
    }

    async function handleUprav() {
        try {

            const { id,datum, cas, poznamka, pocetLudi, typAkcie } = $postData;


            const response = await fetch(`http://localhost:8080/rezervacia/` + $postData.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    datum,
                    cas,
                    pocetLudi,
                    typAkcie,
                    poznamka
                })
            });

            if (!response.ok) {
                throw new Error('Chyba');
            }

        } catch (error) {
            console.error('Chyba', error);
        }
    }

    async function handleVymaz() {
        try {

            const { id} = $postData;


            const response = await fetch(`http://localhost:8080/rezervacia/` + $postData.id, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error('Chyba');
            }
            navigate("/rezervacia");

        } catch (error) {
            console.error('Chyba', error);
        }    }
</script>

<main style="height: 100vh; display: flex; flex-direction: column; justify-content: flex-start; align-items: center; margin-top: 20px; font-size: 1.2em;">
    {#if $postData}
        <div class="prispevok-detail">

            <div style="margin-bottom: 10px;"><strong>Id:</strong> {$postData.id}</div>
            <div style="margin-bottom: 10px;"><strong>Vytvorené:</strong> {formatLocalDateTime($postData.vytvorene)}</div>
            <input type="text" bind:value={$postData.cas} placeholder="Datum" style="font-size: 1em; padding: 5px; margin-bottom: 10px;">
            <input type="text" bind:value={$postData.datum} placeholder="Cas" style="font-size: 1em; padding: 5px; margin-bottom: 10px;">
            <input type="text" bind:value={$postData.poznamka} placeholder="poznámka" style="font-size: 1em; padding: 5px; margin-bottom: 10px;">
            <input type="text" bind:value={$postData.vytvoril.userName} placeholder="Autor" disabled style="font-size: 1em; padding: 5px; margin-bottom: 10px;">
            <input type="text" bind:value={$postData.pocetLudi} placeholder="pocetLudi" style="font-size: 1em; padding: 5px; margin-bottom: 10px;">
            <select bind:value={$postData.typAkcie} style="font-size: 1em; padding: 5px; margin-bottom: 10px;">
                <option value="OSLAVA">OSLAVA</option>
                <option value="RANDE">RANDE</option>
                <option value="SVADBA">SVADBA</option>
                <option value="NESPECIFIKOVANE">NEŠPECIFIKOVANÉ</option>
            </select>
        </div>

    {:else}
        <p>Loading post...</p>
    {/if}
    {#if storedData && storedData.rola === 'ADMIN'}

    <div class="tlacidla-kontainer">
        <button on:click={handleUprav} style="font-size: 1.2em; padding: 10px 20px;">Uprav</button>
        <button on:click={handleVymaz} style="font-size: 1.2em; padding: 10px 20px;">Vymaz</button>
    </div>
    {/if}

</main>

<style>
    .prispevok-detail {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 20px;
    }

    .tlacidla-kontainer {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .tlacidla-kontainer button {
        margin-right: 10px;
    }


    .tlacidla-kontainer button {
        background-color: #0074d9;
        color: white;
        border: none;
        cursor: pointer;
    }


    .tlacidla-kontainer button:hover {
        background-color: #0056b3;
    }


    .prispevok-detail strong {
        font-weight: bold;
    }
</style>






