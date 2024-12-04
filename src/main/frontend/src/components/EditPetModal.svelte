<script>
    import axios from 'axios';

    let { showEditModal, currentPet, onPetEdited } = $props();

    function handleClose() {
        showEditModal = false;
    }

    async function handleSave() {
        try {
            await axios.put(`http://localhost:8081/api/pets/${currentPet.id}`, currentPet, {
                withCredentials: true
            });
            showEditModal = false;
            // Llamar a la función para actualizar la lista de mascotas
            onPetEdited();
        } catch (error) {
            console.error('Error guardando los cambios de la mascota:', error);
        }
    }
</script>

{#if showEditModal}
    <div class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center">
        <div class="bg-white p-5 rounded-lg shadow-xl max-w-2xl w-full">
            <h2 class="text-xl font-bold mb-4">Editar Mascota</h2>
            <form class="space-y-4">
                <div class="grid grid-cols-2 gap-4">
                    <div>
                        <label for="nombre" class="block text-sm font-medium text-gray-700">Nombre</label>
                        <input
                                id="nombre"
                                type="text"
                                bind:value={currentPet.nombre}
                                class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        >
                    </div>
                    <div>
                        <label for="edad" class="block text-sm font-medium text-gray-700">Edad</label>
                        <input
                                id="edad"
                                type="text"
                                bind:value={currentPet.edad}
                                class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        >
                    </div>
                    <div>
                        <label for="genero" class="block text-sm font-medium text-gray-700">Género</label>
                        <select id="genero" bind:value={currentPet.genero} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                            <option value="Macho">Macho</option>
                            <option value="Hembra">Hembra</option>
                        </select>
                    </div>
                    <div>
                        <label for="tamano" class="block text-sm font-medium text-gray-700">Tamaño</label>
                        <input id="tamano" type="text" bind:value={currentPet.tamano} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="raza" class="block text-sm font-medium text-gray-700">Raza</label>
                        <input id="raza" type="text" bind:value={currentPet.raza} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="descripcion" class="block text-sm font-medium text-gray-700">Descripción</label>
                        <textarea id="descripcion" bind:value={currentPet.descripcion} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
                    </div>
                    <div>
                        <label for="fechaIngreso" class="block text-sm font-medium text-gray-700">Fecha de Ingreso</label>
                        <input id="fechaIngreso" type="date" bind:value={currentPet.fechaIngreso} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="estadoSalud" class="block text-sm font-medium text-gray-700">Estado de Salud</label>
                        <input id="estadoSalud" type="text" bind:value={currentPet.estadoSalud} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="vacunado" class="block text-sm font-medium text-gray-700">Vacunado</label>
                        <input id="vacunado" type="checkbox" bind:checked={currentPet.vacunado} class="mt-1 block rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="esterilizado" class="block text-sm font-medium text-gray-700">Esterilizado</label>
                        <input id="esterilizado" type="checkbox" bind:checked={currentPet.esterilizado} class="mt-1 block rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="adoptado" class="block text-sm font-medium text-gray-700">Adoptado</label>
                        <input id="adoptado" type="checkbox" bind:checked={currentPet.adoptado} class="mt-1 block rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="tipo" class="block text-sm font-medium text-gray-700">Tipo</label>
                        <select id="tipo" bind:value={currentPet.tipo} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                            <option value="Perro">Perro</option>
                            <option value="Gato">Gato</option>
                        </select>
                    </div>
                </div>
                <div class="flex justify-end gap-2 mt-4">
                    <button
                            type="button"
                            onclick={handleClose}
                            class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600"
                    >
                        Cancelar
                    </button>
                    <button
                            type="submit"
                            onclick={handleSave}
                            class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                    >
                        Guardar Cambios
                    </button>
                </div>
            </form>
        </div>
    </div>
{/if}