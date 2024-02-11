<script>

    import { createEventDispatcher } from 'svelte';
    import { navigate } from "svelte-routing";
    import aktualnyPouzivatel from "../../utilities/authorization.js";


    let nadpis = '';
    let text = '';
    let vytvoril = '';
    let tag = 'OZNAM';
    let selectedFile = null;
    let obrazok;


    const dispatch = createEventDispatcher();

    const ulozeneData = JSON.parse(localStorage.getItem('aktualnyPouzivatel'));


    function handleSubor(event) {
        selectedFile = event.target.files[0];


        const reader = new FileReader();


        reader.onload = () => {
            obrazok = reader.result;
        };


        reader.readAsDataURL(selectedFile);
    }


    function handleSubmit(event) {
        event.preventDefault();

        const newPost = {
            nadpis,
            text,
            vytvoril,
            tag,
            obrazok
        };

        newPost.vytvoril = ulozeneData.id;


        fetch('http://localhost:8080/prispevok/uloz', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPost)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {

                dispatch('postCreated', data);
            })
            .catch(error => {
                console.error('Error creating post:', error);
            });


        nadpis = '';
        text = '';
        vytvoril = '';
        tag = 'OZNAM';

        navigate('/blog');
    }
</script>

<main class="hlavnyKont">
    <div class="headerKont">
        <h1>Vytvorenie nového príspevku</h1>
    </div>

    <div class="formKont">
        <form on:submit={handleSubmit}>
            <div class="form-group">
                <label class="label-left" for="nadpis">Nadpis:</label>
                <input type="text" id="nadpis" bind:value={nadpis} required>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <div class="half-width">
                        <label class="label-left" for="vytvoril">Vytvoril:</label>
                        <input type="text" id="vytvoril" value={ulozeneData.meno} disabled>
                    </div>
                    <div class="half-width">
                        <label class="label-left" for="tag">Tag:</label>
                        <select id="tag" bind:value={tag}>
                            <option value="OZNAM">OZNAM</option>
                            <option value="BLOG">BLOG</option>
                            <option value="ZMENA">ZMENA</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="label-left" for="text">Text:</label>
                <textarea class="larger-textarea" id="text" bind:value={text} required></textarea>
            </div>


            <input type="file" id="fileUpload" on:change={handleSubor}>


            <div class="tlacidla">
                <button type="submit">Vytvoriť</button>
            </div>
        </form>
    </div>
</main>

<style>


    .label-left {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
        text-align: left;
    }
    .hlavnyKont {
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 100vh;
    }

    .headerKont {
        margin-top: 20px;
    }


    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }
    .tlacidla {
        text-align: right;
    }

    button {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #0056b3;
    }


    input[type="text"],
    textarea,
    select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    .larger-textarea {
        width: 100%;
        height: 200px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }



    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
    }

    .formKont {
        width: 70%;
        padding: 20px;
        background-color: #f0f8ff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
    }
    .input-group {
        display: flex;
    }

    .half-width {
        flex: 1;
        margin-right: 10px;
    }

    .label-left {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
        text-align: left;
    }


    .formKont h1 {
        text-align: center;
    }
    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    input[type="text"],
    textarea,
    select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }


    h1 {
        text-align: center;
        margin-bottom: 20px;
    }
</style>






