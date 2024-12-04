<script>
    const equipo = [
        { name: "Juan Lopez", role: "Fundador", image: '../../images/juanito.jpg' },
        { name: "Guadalupe Ramirez", role: "Entrenadora", image: '../../images/guadalupe.jpg' },
        { name: "Enrique Guerrero", role: "Veterinario", image: '../../images/enrique.jpg' }
    ];
</script>

<div class="bg-gray-400 rounded-lg shadow-lg p-6">
    <h2 class="text-3xl font-bold text-green-700 mb-6 text-center">
        Nuestro Equipo
    </h2>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {#each equipo as { name, role, image }}
            <div class="text-center">
                <img
                        src={image}
                        alt="Miembro del equipo"
                        class="rounded-full mx-auto mb-4 w-32 h-32 object-cover bg-white"
                />
                <h3 class="text-xl font-semibold text-gray-800">{name}</h3>
                <p class="text-gray-600">{role}</p>
            </div>
        {/each}
    </div>
</div>
