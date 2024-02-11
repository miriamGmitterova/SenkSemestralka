

<style>
    .fotky-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 16px;
        margin: 16px;
    }

    .fotka {
        width: 100%;
        height: 100%;
        object-fit: cover;
        cursor: pointer;
        opacity: 0.8;
        transition: 0.4s;
    }
    .fotka:hover{
        box-shadow: #646cff 2px 2px;
        opacity: 1;
    }

    .fullscreen {
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        background-color: rgba(0, 0, 0, 0.8);
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .fullscreen img {
        max-width: 90%;
        max-height: 90%;
        object-fit: contain;
        margin-bottom: 100px;
    }
</style>
<head>
    <link rel="icon" type="image/svg+xml" href="../../assets/beer.png" />
    <title>Blog</title>
</head>
<main style="max-height: calc(100vh - 100px); overflow-y: auto; margin-bottom: 50px;">
<div class="fotky-grid">
    {#each obrazky as fotka}
        <img
                src={fotka}
                alt="Photo"
                class="fotka"
                on:click={() => openFullscreen(fotka)}
        />
    {/each}

    {#if jeFullscreen}
        <div class="fullscreen" on:click={zavriFullscreen}>
            <img src={jeFullscreen} alt="Fullscreen Photo"/>
        </div>
    {/if}

</div>
</main>
<br>

<script>
    let obrazky = [
        '../../../src/assets/slideshow/slideshow1.jpg',
        '../../../src/assets/slideshow/slideshow2.jpg',
        '../../../src/assets/slideshow/slideshow3.jpg',
        '../../../src/assets/slideshow/slideshow5.jpg',
        '../../../src/assets/slideshow/slideshow6.jpg',
        '../../../src/assets/slideshow/slideshow8.jpg',
        '../../../src/assets/slideshow/slideshow11.jpg',
        '../../../src/assets/slideshow/slideshow12.jpg',
        '../../../src/assets/slideshow/slideshow13.jpg',
        '../../../src/assets/slideshow/slideshow14.jpg',
        '../../../src/assets/fotoalbum/f1.jpg',
        '../../../src/assets/fotoalbum/f2.png',
        '../../../src/assets/fotoalbum/f3.jpg',
    ];

    let jeFullscreen = null;

    function openFullscreen(fotka) {
        jeFullscreen = fotka;
    }

    function zavriFullscreen() {
        jeFullscreen = null;
    }
</script>