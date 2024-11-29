<script>
    import {Button} from "flowbite-svelte";

    export let pet;
    export let onclick = () => {};
</script>

<button type="button" class="bg-gray-400 rounded-lg shadow-md overflow-hidden cursor-pointer w-full" onclick={onclick}>
    <img src={pet.image} alt={pet.name} class="w-full h-48 object-cover" />
    <div class="p-4">
        <h3 class="text-xl font-semibold mb-2">{pet.name}</h3>
        <div class="space-y-1 mb-4">
            <p class="text-gray-600">Edad: {pet.age} años</p>
            <p class="text-gray-600">Género: {pet.gender}</p>
            <p class="text-gray-600">Tamaño: {pet.size}</p>
        </div>
        <Button color="green" class="w-full">Ver más</Button>
    </div>
</button>