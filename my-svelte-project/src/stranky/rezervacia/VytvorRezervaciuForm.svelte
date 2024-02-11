<script>

    import {createEventDispatcher} from 'svelte';
    import {navigate} from "svelte-routing";


    let vytvoril = '';
    let poznamka = '';
    let datum = '';
    let cas = '';
    let pocetLudi = '';
    let typAkcie = 'OSLAVA';


    const dispatch = createEventDispatcher();

    const storedData = JSON.parse(localStorage.getItem('aktualnyPouzivatel'));


    function handleSubmit(event) {
        event.preventDefault();

        const parsedPocetLudi = parseInt(pocetLudi);
        if (isNaN(parsedPocetLudi)) {
            alert('Počet ľudí musí byť číslo.');
            return;
        }

        const newPost = {
            vytvoril,
            poznamka,
            datum,
            cas,
            pocetLudi,
            typAkcie
        };

        newPost.vytvoril = storedData.id;


        fetch('http://localhost:8080/rezervacia/uloz', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPost)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Chyba');
                }
                return response.json();
            })
            .then(data => {

                dispatch('postCreated', data);

            })
            .catch(error => {
                console.error('Chyba', error);
            });


        vytvoril = '';
        datum = '';
        poznamka = '';
        cas = '';
        pocetLudi = '';
        typAkcie = 'OSLAVA';

        navigate('/rezervacia');


    }
</script>

<main class="main">
    <h1 class="heading">Vytvorte rezerváciu</h1>

    <form on:submit={handleSubmit}>
        <label for="vytvoril">Vytvoril:</label>
        <input type="text" id="vytvoril" value={storedData.meno} disabled>

        <label for="datum">Dátum rezervacie:</label>
        <input type="text" id="datum" bind:value={datum} required>

        <label for="cas">Čas rezervacie:</label>
        <input type="text" id="cas" bind:value={cas} required>

        <label for="pocetLudi">Počet ludi:</label>
        <input type="text" id="pocetLudi" bind:value={pocetLudi} required>

        <label for="poznamka">Poznámka k rezervacii:</label>
        <textarea id="text" bind:value={poznamka}></textarea>

        <label for="tag">Tag:</label>
        <select id="tag" bind:value={typAkcie}>
            <option value="OSLAVA">OSLAVA</option>
            <option value="RANDE">RANDE</option>
            <option value="SVADBA">SVADBA</option>
            <option value="NESPECIFIKOVANE">NEŠPECIFIKOVANÉ</option>
        </select>

        <button type="submit">Vytvoriť</button>
    </form>
</main>

<style>

    .main {
        height: calc(100vh - 200px);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }


    form {
        max-width: 400px;
        width: 100%;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        background-color: #f0f8ff;
    }

    .heading {
        margin-bottom: 30px;
        text-align: center;
    }

    label {
        margin-bottom: 8px;
    }

    input,
    textarea,
    select {
        width: 100%;
        margin-bottom: 16px;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    button {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #0056b3;
    }
</style>

