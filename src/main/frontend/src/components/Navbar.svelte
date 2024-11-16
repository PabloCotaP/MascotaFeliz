<script>
    export let links =[
        { name: 'Inicio', url: './' },
        { name: 'Mascotas', url: './Mascotas' },
        { name: 'Donaciones', url: './Donaciones' },
        { name: 'Acerca de', url: './AcercaDe' },
        { name: 'Iniciar Sesión', url: './Login' },
    ];
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
                    <a href={url} class="px-6 py-3.5 text-base font-medium text-black bg-emerald-600 hover:bg-emerald-800 focus:ring-3 focus:outline-none focus:bg-emerald-800 rounded-lg text-center">
                        {name}
                    </a>
                {/each}
            </div>
        </div>
    </nav>
</section>