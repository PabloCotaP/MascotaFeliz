<script>
    import Navbar from "../../components/Navbar.svelte";
    import Footer from "../../components/Footer.svelte";
    import Register from "../../components/Register.svelte";
</script>

<svelte:head>
    <title>Registro - La Mascota Feliz</title>
</svelte:head>

<Navbar/> <!-- Llama al componente Navbar que importe desde "../components/Navbar.svelte" -->

<Register/>

<Footer/> <!-- Llama al componente footer que importe desde "../components/Footer.svelte" -->