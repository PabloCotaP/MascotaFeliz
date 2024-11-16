<script>
    const testimonios = [
        {quote: "Adoptar fue la mejor decisión", author: "Juan", image: '../../images/cachorro-pastor-aleman.jpg'},
        {quote: "Encontré a mi mejor amigo", author: "Pedro", image: '../../images/cachorro-golden-retriever.jpg'},
        {quote: "Yo y mi perrito somos muy felices", author: "Matilda", image: '../../images/perro-labrador.jpg'}
    ];
</script>

<!-- Historias de Éxito -->
<section class="py-12 px-4">
    <div class="container mx-auto">
        <div class="bg-gray-400 rounded-lg p-8 mb-12 shadow-2xl">
            <h2 class="text-2xl mb-4">Historias de Éxito</h2>
            <p class="mb-8">Lee las experiencias de algunos adoptantes</p>

            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                {#each testimonios as {quote, author, image}}
                    <div class="bg-emerald-600 p-6 rounded-2xl text-center">
                        <img src="{image}" alt="Perro Historia" class="size-64 object-cover mx-auto rounded-full border-4 border-gray-800">
                        <br>
                        <p class="text-white mb-4">"{quote}" - {author}</p>
                    </div>
                {/each}
            </div>
        </div>
    </div>
</section>