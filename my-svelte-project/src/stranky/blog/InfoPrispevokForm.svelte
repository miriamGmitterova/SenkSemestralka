<script>
    import { onMount } from 'svelte';
    import { writable } from 'svelte/store';
    import { formatLocalDateTime } from '../../utilities/DateTimeFormatter.js';
    import {navigate} from "svelte-routing";

    const prispevkyData = writable(null);

    const storedData = JSON.parse(localStorage.getItem('aktualnyPouzivatel'));

    export let id;

    onMount(() => {
        // const urlParams = new URLSearchParams(window.location.search);
        console.log('params:', window.location.pathname.replace("/blog/", ""));
        const id = window.location.pathname.replace("/blog/", "");
        console.log('ID:', id); // Check if ID is retrieved correctly
        if (id) {
            fetchPrispevky(id);
        }
    });

    async function fetchPrispevky(id) {
        try {
            const response = await fetch(`http://localhost:8080/prispevok/` + id);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            prispevkyData.set(data);
        } catch (error) {
            console.error('Error fetching post data:', error);
        }
    }

    async function handleUprav() {
        try {

            const { id,nadpis, text, tag, vytvoril } = $prispevkyData;


            const response = await fetch(`http://localhost:8080/prispevok/` + $prispevkyData.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    nadpis,
                    text,
                    tag
                })
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }


            console.log('Post data updated successfully!');
        } catch (error) {
            console.error('Error updating post data:', error);
        }
    }

    async function handleVymaz() {
        try {

            const { id} = $prispevkyData;


            const response = await fetch(`http://localhost:8080/prispevok/` + $prispevkyData.id, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            navigate("/blog");

            console.log('Post data updated successfully!');
        } catch (error) {
            console.error('Error updating post data:', error);
        }    }
</script>

<main class="full-screen">
    {#if $prispevkyData}
        <div class="prispevokDetail">

            <div><strong>Id:</strong> {$prispevkyData.id}</div>
            <div><strong>Vytvorené:</strong> {formatLocalDateTime($prispevkyData.vytvorene)}</div>
            <div style="margin-bottom: 20px;"></div>
            <div style="margin-bottom: 10px;">
                <input type="text" bind:value={$prispevkyData.nadpis} placeholder="Nadpis" class="input-field">
                <br>
                <input type="text" bind:value={$prispevkyData.text} placeholder="Text" class="input-field input-text">
                <br>
                <input type="text" bind:value={$prispevkyData.vytvoril.meno} placeholder="Autor" disabled class="input-field">
                <br>
                <select bind:value={$prispevkyData.tag} class="input-field">
                    <option value="OZNAM">OZNAM</option>
                    <option value="BLOG">BLOG</option>
                    <option value="ZMENA">ZMENA</option>
                </select>
            </div>
        </div>
        <img src="{$prispevkyData.obrazok}" alt="Post Image" style="max-width: 100%; height: auto;">
        <div style="margin-bottom: 20px;"></div>
    {:else}
        <p>Loading post...</p>
    {/if}

    {#if storedData && storedData.rola === 'ADMIN'}
        <div class="tlacidla">
            <button on:click={handleUprav} class="tlacidlo">Uprav</button>
            <button on:click={handleVymaz} class="tlacidlo">Vymaz</button>
        </div>
    {/if}

</main>

<style>
    .full-screen {
        width: 100%;
        height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
    }

    .prispevokDetail {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin-bottom: 20px;
        margin-top: 50px;
    }

    .input-field {
        width: 100%;
        padding: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-bottom: 10px;
        font-size: 16px;
    }

    .input-text {
        height: 100px;
        width: 1000px;
    }

    .tlacidla {
        display: flex;
        justify-content: center;
    }

    .tlacidlo {
        background-color: #007bff;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin: 0 5px;
    }

    .tlacidlo:hover {
        background-color: #0056b3;
    }
</style>
