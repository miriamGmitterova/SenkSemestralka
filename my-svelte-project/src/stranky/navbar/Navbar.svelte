<script>
    let linky = [
        {text: 'O nás', url: '/domov'},
        {text: 'Fotogaléria', url: '/foto'},
        {text: 'Blog', url: '/blog'},
        {text: 'Rezervácie', url: '/rezervacia'},
        {text: 'Cenník', url: '/cennik'}
    ];
</script>

<style>
    .navDiv {
        width: 100vw;
        margin: 0;
        padding: 0;
    }

    nav {
        background-color: #333;
        padding: 1rem;
        display: flex;
        justify-content: space-around;
        align-items: center;
        overflow-x: hidden;
        width: 100vw;

    }

    a {
        color: white;
        text-decoration: none;
        padding: 5px;
        opacity: 0.6;
        transition: 0.3s;
    }

    a:hover {
        background-color: #646cff;
        padding: 20px;
        font-size: large;
        text-decoration: white;
        opacity: 1;
    }

    .logo {
        max-height: 32px;
        max-width: 32px;
    }

    @media screen and (max-width: 600px) {
        nav {
            flex-direction: column;
            text-align: center;
        }

        a {
            margin: 0;
        }
    }
</style>
<div class="navDiv">
    <nav>
        {#each linky as {text, url}}
            <a href={url}>{text}</a>
        {/each}
        <div>
            <a href="/"><img class="logo" src="../../../src/assets/user.png" alt="profil"/></a>
        </div>
    </nav>
</div>
