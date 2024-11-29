<script>
    import { Button } from 'flowbite-svelte';
    export let selectedPet;
    export let onClose = () => {};
</script>

<div class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center">
    <div class="bg-white p-5 rounded-lg shadow-xl max-w-4xl w-full">
        <div class="flex flex-col md:flex-row gap-6">
            <div class="w-full md:w-1/2">
                <img src={selectedPet.image} alt={selectedPet.name} class="w-full h-64 object-cover rounded-lg" />
            </div>
            <div class="w-full md:w-1/2 space-y-4">
                <h3 class="text-2xl font-semibold">{selectedPet.name}</h3>
                <div class="space-y-2">
                    <p class="text-gray-600">Edad: {selectedPet.age} años</p>
                    <p class="text-gray-600">Género: {selectedPet.gender}</p>
                    <p class="text-gray-600">Tamaño: {selectedPet.size}</p>
                    <p class="text-gray-600">Tipo: {selectedPet.type}</p>
                    <p class="text-gray-600">Descripción: {selectedPet.description}</p>
                    <p class="text-gray-600">Fecha de Ingreso: {selectedPet.fechaIngreso}</p>
                    <p class="text-gray-600">Estado de Salud: {selectedPet.estadoSalud}</p>
                    <p class="text-gray-600">Vacunado: {selectedPet.vacunado ? 'Sí' : 'No'}</p>
                    <p class="text-gray-600">Esterilizado: {selectedPet.esterilizado ? 'Sí' : 'No'}</p>
                    <p class="text-gray-600">Adoptado: {selectedPet.adoptado ? 'Sí' : 'No'}</p>
                </div>
                <Button color="red" onclick={onClose} class="w-full">Cerrar</Button>
            </div>
        </div>
    </div>
</div>