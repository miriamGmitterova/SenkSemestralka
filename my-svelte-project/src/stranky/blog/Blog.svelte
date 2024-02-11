<script>
    import { onMount } from 'svelte';
    import { writable } from 'svelte/store';
    import { formatLocalDateTime } from '../../utilities/DateTimeFormatter.js';
    import {navigate} from "svelte-routing";


    const apiData = writable([]);

    const storedData = JSON.parse(localStorage.getItem('aktualnyPouzivatel'));
    console.log(storedData);

    onMount(async () => {
        try {
            const response = await fetch('http://localhost:8080/prispevok/zoznam');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();

            apiData.set(data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    });



    onMount(async () => {
        try {
            const response = await fetch('http://localhost:8080/prispevok/zoznam');
            if (!response.ok) {
                throw new Error('Chyba');
            }
            const data = await response.json();

            apiData.set(data);
        } catch (error) {
            console.error('chyba:', error);
        }
    });

    function redirectToCreatePost() {
        window.location.href = '/blog/vytvor-prispevok';
    }

    function redirectToPost(id) {
        navigate(`/blog/${id}`);
    }
</script>

<main style="max-height: calc(100vh - 100px); overflow-y: auto;">
    <h1>Blog</h1>
    {#if storedData && storedData.rola === 'ADMIN'}
        <button class="vytvorPrispevokButton" on:click={redirectToCreatePost}>Vytvoriť príspevok</button>
    {/if}

    {#each $apiData as { id, nadpis, vytvorene, vytvoril, tag, text, obrazok }, index}
        <div class="prispevky" style="{index === $apiData.length - 1 ? 'margin-bottom: 100px;' : ''}">
            <button class="blog-element" on:click={() => redirectToPost(id)}>
                <h2>{nadpis}</h2>
                <div class="blog-content">
                    <div class="text-container">
                        <div class="blog-metadata">
                            <p><strong>Vytvorené:</strong> {formatLocalDateTime(vytvorene)}</p>
                            <p><strong>Autor:</strong> {vytvoril.meno}</p>
                            <p><strong>Typ:</strong> {tag}</p>
                        </div>
                        <p>{text}</p>
                    </div>
                    {#if obrazok}
                        <img src="{obrazok}" alt="Post Image" class="post-image">
                    {/if}
                </div>
            </button>
        </div>
        <div class="oddelovac"></div>
    {/each}
</main>

<style>

    .prispevky {
        margin-bottom: 20px;
        width: 70%;
        margin-left: auto;
        margin-right: auto;
    }

    .oddelovac {
        height: 20px;
    }

    .vytvorPrispevokButton {
        margin-top: 20px;
        margin-bottom: 40px;
        background-color: #0056b3;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }

    .vytvorPrispevokButton:hover {
        background-color: #004080;
    }

    .blog-element {
        width: 100%;
        border: 1px solid #ccc;
        padding: 10px;
        margin-bottom: 10px;
        background-color: #f0f8ff;
    }

    .blog-metadata {
        display: flex;
        flex-direction: column;
        text-align: left;
        margin-bottom: 10px;
    }
</style>
