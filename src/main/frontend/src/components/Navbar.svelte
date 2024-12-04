<script>
    import {changeAuth, getAuth} from '../authContext.svelte.js';
    import {Button} from "flowbite-svelte";
    import axios from "axios";
    import {goto} from "$app/navigation";

    const { links = [
        {name: 'Inicio', url: './'},
        {name: 'Mascotas', url: './Mascotas'},
        {name: 'Acerca de', url: './AcercaDe'},
    ]} = $props();

    const isAuthenticated = $derived(getAuth().isAuthenticated);
    const userRole = $derived(getAuth().role);

</script>
<!-- Navbar -->
<section>
    <nav class="bg-emerald-600 p-4">
        <div class="container mx-auto flex justify-between items-center">
            <div class="flex items-center gap-2">
                <img src="../../images/logo.svg" alt="Logo" class="size-20 text-gray-800">
                <h1 class="text-black text-4xl font-bold">Refugio La Mascota Feliz</h1>
            </div>
            <div class="flex gap-4">
                {#each links as {name, url}}
                    <a href={url}
                       class="px-6 py-3.5 text-base font-medium text-black bg-emerald-600 hover:bg-emerald-800 focus:ring-3 focus:outline-none focus:bg-emerald-800 rounded-lg text-center">
                        {name}
                    </a>
                {/each}
                {#if !isAuthenticated}
                    <a href="./Login"
                       class="px-6 py-3.5 text-base font-medium text-black bg-emerald-600 hover:bg-emerald-800 focus:ring-3 focus:outline-none focus:bg-emerald-800 rounded-lg text-center">
                        Iniciar Sesión
                    </a>
                {:else if isAuthenticated && userRole === 'USER'}
                    <a href="./Panel"
                       class="px-6 py-3.5 text-base font-medium text-black bg-emerald-600 hover:bg-emerald-800 focus:ring-3 focus:outline-none focus:bg-emerald-800 rounded-lg text-center">
                        Panel de Usuario
                    </a>
                {:else if isAuthenticated && userRole === 'ADMIN'}
                    <a href="./Admin"
                       class="px-6 py-3.5 text-base font-medium text-black bg-emerald-600 hover:bg-emerald-800 focus:ring-3 focus:outline-none focus:bg-emerald-800 rounded-lg text-center">
                        Panel de Administración
                    </a>
                    <a href="./Panel"
                       class="px-6 py-3.5 text-base font-medium text-black bg-emerald-600 hover:bg-emerald-800 focus:ring-3 focus:outline-none focus:bg-emerald-800 rounded-lg text-center">
                        Panel de Usuario
                    </a>
                {/if}
            </div>
        </div>
    </nav>
</section>