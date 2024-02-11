<script>
    import {onMount} from 'svelte';
    import {writable} from 'svelte/store';

    const apiData = writable([]);

    onMount(async () => {
        try {
            const response = await fetch('http://localhost:8080/cennik/zoznam');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            apiData.set(data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    });
</script>

<main>
    <h1>Cenník</h1>

    {#each $apiData as {id, nazovPolozky, cena, objem, typJedla}}
        <div class="cennik-container">
            <div class="cennik-element">
                <div>
                    <p><strong>Názov:</strong> {nazovPolozky}</p>
                    <p><strong>Cena:</strong> {cena} €</p>
                    <p><strong>Objem:</strong> {objem} L</p>
                    <p><strong>Typ:</strong> {typJedla}</p>
                </div>
            </div>
        </div>
        <div class="medzera"></div>
    {/each}
</main>

<style>
    .cennik-container {
        margin-bottom: 20px;
    }

    .cennik-element {
        width: 50%;
        margin: 0 auto;
        border: 1px solid #ccc;
        padding: 10px;
        margin-bottom: 10px;
        background-color: #f0f8ff;
    }

    .medzera {
        height: 30px;
    }
</style>


