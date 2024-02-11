<script>
    import {onMount} from 'svelte';

    let aktualnaSnimka = 0;
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
    ];

    onMount(() => {
        const interval = setInterval(() => {
            dalsiSnimok();
        }, 5000);

        return () => clearInterval(interval);
    });

    function dalsiSnimok() {
        if (aktualnaSnimka === obrazky.length - 1) {
            aktualnaSnimka = 0;
        } else {
            aktualnaSnimka++;
        }
    }

    function predchadzajuciSnimok() {
        if (aktualnaSnimka === 0) {
            aktualnaSnimka = obrazky.length - 1;
        } else {
            aktualnaSnimka -= 1;
        }
    }
</script>

<style>
    .slideshow-container {
        position: relative;
        max-width: 600px;
        display: flex;
        align-items: center;
        justify-content: center;

        margin: 1rem auto auto;
    }

    .slide {
        display: none;
        width: auto;
        height: 400px;
    }

    .active {
        display: block;
    }

    .prev, .next {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        font-size: 1.5rem;
        cursor: pointer;
        color: white;
        background-color: rgba(0, 0, 0, 0.5);
        padding: 0.5rem;
        opacity: 0.6;
        transition: 0.3s;
    }

    .prev:hover, .next:hover {
        background-color: #646cff;
        opacity: 1;
    }

    .prev {
        left: 0;
    }

    .next {
        right: 0;
    }
</style>

<div class="slideshow-container">
    <div class="prev" on:click={predchadzajuciSnimok}>&#10094;
    </div>
    {#each obrazky as obrazok, i}
        <img class="slide {i === aktualnaSnimka ? 'active' : ''}" src={obrazok} alt={`Slide ${i + 1}`}/>
    {/each}
    <div class="next" on:click={dalsiSnimok}>&#10095;</div>
</div>
