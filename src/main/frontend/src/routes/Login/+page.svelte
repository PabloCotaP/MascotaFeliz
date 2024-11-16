<script>
    import Navbar from "../../components/Navbar.svelte";
    import Footer from "../../components/Footer.svelte";
    import Login from "../../components/Login.svelte";
    import BotonPruebaConex from "../../components/BotonPruebaConex.svelte";
</script>

<svelte:head>
    <title>Iniciar Sesión - La Mascota Feliz</title>
</svelte:head>

<!-- Navbar -->
<Navbar/> <!-- Llama al componente navbar que importe desde "../components/Navbar.svelte" -->

<Login/> <!-- Llama al componente login que importe desde "../components/Login.svelte" -->

<Footer/> <!-- Llama al componente footer que importe desde "../components/Footer.svelte" -->