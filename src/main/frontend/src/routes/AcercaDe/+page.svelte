<script>
    import Navbar from "../../components/Navbar.svelte";
    import Mapa from "../../components/Mapa.svelte";
    import Footer from "../../components/Footer.svelte";
    import Preguntas from "../../components/Preguntas.svelte";
    import Equipo from "../../components/Equipo.svelte";
    import Estadisticas from "../../components/Estadisticas.svelte";
</script>

<svelte:head>
    <title>Acerca De - La Mascota Feliz</title>
</svelte:head>

<Navbar/> <!-- Llama al componente Navbar que importe desde "../components/Navbar.svelte" -->

<div class="container mx-auto px-4 py-8">
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <div class="order-2 lg:order-1">
            <Preguntas/>
        </div>
        <div class="order-1 lg:order-2">
            <Mapa/>
        </div>
    </div>
    <div class="mt-8">
        <Equipo/>
    </div>
    <div class="mt-8">
        <Estadisticas/>
    </div>
</div>

<Footer/> <!-- Llama al componente Footer que importe desde "../components/Footer.svelte" -->