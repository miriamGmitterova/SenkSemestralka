
<head>
    <link rel="icon" type="image/svg+xml" href="../my-svelte-project/src/assets/beer.png" />
    <title>Login</title>
</head>
<main>
    {#if (!aktualnyPouzivatel.prihlaseny)}
        <h1>Prihlásenie</h1>

        <form on:submit={prihlas}>
            <label for="meno">Prihlasovacie meno:</label>
            <input type="text" id="meno" bind:value={prihlasovacieMeno} required>

            <label for="heslo">Heslo:</label>
            <input type="password" id="heslo" bind:value={heslo} required>

            <button type="submit">Prihlásiť sa</button>
        </form>
    {:else}
        <h1>Vitaj, {aktualnyPouzivatel.meno}!</h1>
        <button on:click={handleLogout} class="logout-button">Odhlásenie</button>
    {/if}
</main>

<style>

    main {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
    }

    label {
        margin-bottom: 8px;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    button[type="submit"] {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button[type="submit"]:hover {
        background-color: #0056b3;
    }

    .logout-button {
        background-color: #007bff;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .logout-button:hover {
        background-color: #0056b3;
    }
</style>
<script>
    import {navigate} from 'svelte-routing';
    import aktualnyPouzivatel from "../utilities/authorization.js";
    import {onMount} from "svelte";


    let prihlasovacieMeno = '';
    let heslo = '';
    let loginData;

    onMount(() => {
        const ulozenyPouzivatel = localStorage.getItem('aktualnyPouzivatel');
        console.log(ulozenyPouzivatel);
        if (ulozenyPouzivatel) {
            let pouzivatel = JSON.parse(ulozenyPouzivatel);
            aktualnyPouzivatel.meno = pouzivatel.meno;
            aktualnyPouzivatel.prihlaseny = pouzivatel.prihlaseny;
            aktualnyPouzivatel.rola = pouzivatel.rola;
            aktualnyPouzivatel.id = pouzivatel.id;
            aktualnyPouzivatel.email = pouzivatel.email;
        }
    });

    async function prihlas() {
        event.preventDefault();

        let apiData = null;

        const prihlasovacieUdaje = {
            username: prihlasovacieMeno,
            password: heslo
        };


        try {
            const response = await fetch('http://localhost:8080/pouzivatel/authorise', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(prihlasovacieUdaje),
            });

            if (!response.ok) {
                throw new Error('Chyba pri requeste');
            }

            apiData = await response;

        } catch (error) {
            console.error('Chyba pri fetchovani', error);
        }

        loginData = await apiData.json();

        aktualnyPouzivatel.meno = loginData.meno;
        aktualnyPouzivatel.rola = loginData.role[0];
        aktualnyPouzivatel.id = loginData.id;
        aktualnyPouzivatel.email = loginData.email;
        aktualnyPouzivatel.prihlaseny = true;

        console.log(loginData);

        localStorage.setItem('aktualnyPouzivatel', JSON.stringify(aktualnyPouzivatel));

        console.log(aktualnyPouzivatel);

        if (aktualnyPouzivatel.meno !== null) {
            navigate('/domov');
            aktualnyPouzivatel.prihlaseny = true;
        }

    }

    function handleLogout() {
        aktualnyPouzivatel.meno = null;
        aktualnyPouzivatel.rola = null;
        aktualnyPouzivatel.id = null;
        aktualnyPouzivatel.email = null;
        aktualnyPouzivatel.prihlaseny = false;
        localStorage.removeItem('aktualnyPouzivatel');
        navigate('/domov');
    }
</script>