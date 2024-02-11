<head>
    <link rel="icon" type="image/svg+xml" href="../my-svelte-project/src/assets/beer.png" />
    <title>Domov</title>
</head>
<main>
    <h1>Pohostinstvo u Miry</h1>
    <Galeria/>
</main>

<style>
    .logo {
        height: 6em;
        padding: 1.5em;
        will-change: filter;
        transition: filter 300ms;
        font-size: 30px;
        font-weight: bold;
    }

    .logo:hover {
        filter: drop-shadow(0 0 2em #646cffaa);
    }


    .logo:hover {
        filter: drop-shadow(0 0 2em #ff3e00aa);
    }


</style>
<script>
    import Galeria from "./galeria/Galeria.svelte";

    let apiData = {};

    async function fetchData() {
        try {
            const response = await fetch('http://localhost:8080/pouzivatel/zoznam');
            if (!response.ok) {
                throw new Error('Chyba');
            }
            apiData = await response.json();
        } catch (error) {
            console.error('Chyba', error);
        }
    }


</script>
<main>
    <p class="logo">Naše akcie</p>
</main>


