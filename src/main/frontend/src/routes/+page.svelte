<script>
    import Navbar from "../components/Navbar.svelte";
    import Footer from "../components/Footer.svelte";
    import Historias from "../components/Historias.svelte";
    import Carrusel from "../components/Carrusel.svelte";
</script>

<svelte:head>
    <title>Refugio La Mascota Feliz</title>
</svelte:head>

<Navbar/> <!-- Llama al componente Navbar que importe desde "../components/Navbar.svelte" -->

<Carrusel/> <!-- Llama al componente Carrusel que importe desde "../components/Carrusel.svelte" -->

<Historias/> <!-- Llama al componente Historias que importe desde "../components/Historias.svelte" -->

<Footer/> <!-- Llama al componente footer que importe desde "../components/Footer.svelte" -->