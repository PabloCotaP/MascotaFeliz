<script>
    import { Checkbox, Range } from 'flowbite-svelte';

    let age = 7;
    let filters = {
        types: {
            dogs: true,
            cats: true
        },
        gender: {
            male: true,
            female: true
        },
        size: {
            small: true,
            medium: true,
            large: true
        }
    };
</script>

<div class="w-64 bg-gray-400 rounded-lg p-4  shadow-md">
    <h2 class="text-xl font-semibold mb-4">Filtros</h2>

    <div class="mb-6">
        <h3 class="font-medium mb-2">Tipo de Mascota</h3>
        <div class="space-y-2">
            <Checkbox bind:checked={filters.types.dogs}>Perros</Checkbox>
            <Checkbox bind:checked={filters.types.cats}>Gatos</Checkbox>
        </div>
    </div>

    <div class="mb-6">
        <h3 class="font-medium mb-2">Género</h3>
        <div class="space-y-2">
            <Checkbox bind:checked={filters.gender.male}>Macho</Checkbox>
            <Checkbox bind:checked={filters.gender.female}>Hembra</Checkbox>
        </div>
    </div>

    <div class="mb-6">
        <h3 class="font-medium mb-2">Edad: {age} años</h3>
        <Range bind:value={age} min={0} max={15} step={1} />
    </div>

    <div class="mb-6">
        <h3 class="font-medium mb-2">Tamaño</h3>
        <div class="space-y-2">
            <Checkbox bind:checked={filters.size.small}>Raza Chica</Checkbox>
            <Checkbox bind:checked={filters.size.medium}>Raza Mediana</Checkbox>
            <Checkbox bind:checked={filters.size.large}>Raza Grande</Checkbox>
        </div>
    </div>
</div>