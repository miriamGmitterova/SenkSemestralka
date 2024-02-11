
<head>
    <link rel="icon" type="image/svg+xml" href="../my-svelte-project/src/assets/beer.png" />
</head>
<main >
    <Navbar/>

    <Router>
        <Route path="/" component = { LoginPage } ></Route>
        <Route path="/domov" component = { Home }></Route>
        <Route path="/blog" component = { Blog }></Route>
        <Route path="/blog/vytvor-prispevok" component = { VytvorPrispevokForm }></Route>
        <Route path="/blog/:id" component = { InfoPrispevokForm }></Route>
        <Route path="/rezervacia" component = { Rezervacia }></Route>
        <Route path="/rezervacia/vytvor-rezervaciu" component = { VytvorRezervaciuForm }></Route>
        <Route path="/rezervacia/:id" component = { InfoRezervaciaForm }></Route>
        <Route path="/cennik" component = { Cennik  }></Route>
        <Route path="/foto" component = {Fotostena}></Route>
    </Router>

    <Footer/>
</main>

<script>

    import Navbar from './stranky/navbar/Navbar.svelte';
    import { Router, Route } from 'svelte-routing';
    import Blog from './stranky/blog/Blog.svelte'
    import Home from './stranky/Home.svelte'
    import VytvorPrispevokForm from './stranky/blog/VytvorPrispevokForm.svelte'
    import InfoPrispevokForm from './stranky/blog/InfoPrispevokForm.svelte'
    import Rezervacia from "./stranky/rezervacia/Rezervacia.svelte";
    import VytvorRezervaciuForm from "./stranky/rezervacia/VytvorRezervaciuForm.svelte";
    import InfoRezervaciaForm from "./stranky/rezervacia/InfoRezervaciaForm.svelte";
    import Footer from "./stranky/footer/Footer.svelte";
    import Galeria from "./stranky/galeria/Galeria.svelte";
    import Cennik from "./stranky/cennik/Cennik.svelte";
    import LoginPage from "./stranky/LoginPage.svelte";
    import Fotostena from "./stranky/fotostena/Fotostena.svelte";


</script>