<script>
    import {Carousel} from "flowbite-svelte";

    const images = [
        {alt: "Perros Jugando 1", src: "../../images/perros_carru1.jpg", title: "Perros Jugando 1"},
        {alt: "Perros Jugando 2", src: "../../images/perros_carru2.jpg", title: "Perros Jugando 2"},
        {alt: "Perros Jugando 3", src: "../../images/perros_carru3.jpg", title: "Perros Jugando 3"},
        {alt: "Perros Jugando 4", src: "../../images/perros_carru4.jpg", title: "Perros Jugando 4"},
        {alt: "Perros Jugando 5", src: "../../images/perros_carru5.jpg", title: "Perros Jugando 5"}
    ];
</script>

<section>
    <div class="py-8 max-w-4xl mx-auto">
        <h1 class="text-black text-3xl font-bold pb-8 text-center">Somos una comunidad dedicada a ayudar a los animales a encontrar un lugar feliz</h1>
        <Carousel {images} let:Indicators duration="5000" class="rounded-2xl pb-8 object-fill shadow-lg">
            <Indicators />
        </Carousel>
        <div class="bg-gray-400 rounded-lg mt-8 shadow-xl">
            <h2 class="text-black text-2xl font-semibold pt-10 text-center">Te interesa saber mas?</h2>
            <div class="flex justify-center pt-3 shadow-lg pb-8">
                <a href='./AcercaDe' class="px-6 py-3.5 text-base font-medium text-black bg-emerald-600
                    hover:bg-emerald-800 focus:ring-3 focus:outline-none focus:bg-gray-800 rounded-lg text-center ">
                    Haz click aquí
                </a>
            </div>
        </div>
    </div>
</section>