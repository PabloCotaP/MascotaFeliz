<script>
import Navbar from "../../components/Navbar.svelte";
import AdminDashboard from "../../components/AdminDashboard.svelte";
import Footer from "../../components/Footer.svelte";
</script>

<svelte:head>
    <title>Panel de Administrador - La Mascota Feliz</title>
</svelte:head>

<Navbar/> <!-- Llama al componente Navbar que importe desde "../components/Navbar.svelte" -->

<AdminDashboard/> <!-- Llama al componente AdminDashboard que importe desde "../components/AdminDashboard.svelte" -->

<Footer/> <!-- Llama al componente Footer que importe desde "../components/Footer.svelte" -->