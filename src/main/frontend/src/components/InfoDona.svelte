<script>
    export let benefits = [
        {
            title: "Alimento",
            description: "Tu donación ayuda a alimentar a nuestros amigos peludos",
            amount: "5 mascotas / semana"
        },
        {
            title: "Atención Médica",
            description: "Contribuyes a tratamientos veterinarios esenciales",
            amount: "3 mascotas / mes"
        },
        {
            title: "Refugio",
            description: "Ayudas a mantener un espacio seguro y limpio",
            amount: "10 mascotas"
        }
    ];
</script>

<section class="py-8 px-4">
    <div class="max-w-6xl mx-auto">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
            {#each benefits as benefit}
                <div class="bg-gray-400 rounded-lg p-6 shadow-md">
                    <h3 class="text-lg font-semibold text-emerald-800 mb-2">
                        {benefit.title}
                    </h3>
                    <p class="text-gray-600 mb-2">{benefit.description}</p>
                    <p class="text-emerald-600 font-semibold">{benefit.amount}</p>
                </div>
            {/each}
        </div>
    </div>
</section>