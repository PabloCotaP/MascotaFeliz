<script>
import Navbar from "../../components/Navbar.svelte";
import Footer from "../../components/Footer.svelte";
import Donacion from "../../components/Donacion.svelte";
import InfoDona from "../../components/InfoDona.svelte";
</script>

<svelte:head>
    <title>Donaciones - La Mascota Feliz</title>
</svelte:head>

<Navbar/><!-- Llama al componente Navbar que importe desde "../components/Navbar.svelte" -->
<!-- Titulo y descripción de la página -->
<div class="text-center mb-8 p-8">
    <h2 class="text-3xl font-bold text-emerald-800">¡Haz tu donación y cambia vidas!</h2>
    <p class="text-lg text-gray-600">Tu contribución ayuda a brindar bienestar a las mascotas necesitadas.</p>
</div>
    <InfoDona/>
    <div class="flex content-center text-center justify-center space-x-6 pb-8">
        <Donacion title="Donacion Simple" amount={100} buttonText="Donar"/>
        <Donacion title="Personalizado" isCustom="true" buttonText="Donar"/>
        <Donacion title="Mega Donacion" amount={300} buttonText="Donar"/>
    </div>

<Footer/>