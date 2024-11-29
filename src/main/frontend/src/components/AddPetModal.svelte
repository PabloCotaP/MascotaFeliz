<script>
    import axios from "axios";

    let {showAddModal, onPetAdded} = $props();

    const today = new Date().toISOString().split('T')[0];

    let newPet = $state({
        nombre: '',
        edad: '',
        genero: '',
        tamano: '',
        raza: '',
        descripcion: '',
        fechaIngreso: today,
        estadoSalud: '',
        adoptado: false,
        vacunado: false,
        esterilizado: false,
        tipo: ''
    });
    function handleClose() {
        showAddModal = false;
    }

    async function handleSave() {
        try {
            await axios.post('http://localhost:8081/api/pets', newPet, {
                withCredentials: true
            });
            showAddModal = false;
            // Limpiar el formulario después de guardar
            newPet = {
                nombre: '',
                edad: '',
                genero: '',
                tamano: '',
                raza: '',
                descripcion: '',
                fechaIngreso: today,
                estadoSalud: '',
                adoptado: false,
                vacunado: false,
                esterilizado: false,
                tipo: ''
            };
            onPetAdded();
            showAddModal = false;
        } catch (error) {
            console.error('Error guardando la mascota:', error);
        }
    }
</script>

{#if showAddModal}
    <div class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center">
        <div class="bg-white p-5 rounded-lg shadow-xl max-w-2xl w-full">
            <h2 class="text-xl font-bold mb-4">Agregar Nueva Mascota</h2>
            <form class="space-y-4">
                <div class="grid grid-cols-2 gap-4">
                    <div>
                        <label for="nombre" class="block text-sm font-medium text-gray-700">Nombre</label>
                        <input id="nombre" type="text" bind:value={newPet.nombre} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="edad" class="block text-sm font-medium text-gray-700">Edad</label>
                        <input id="edad" type="text" bind:value={newPet.edad} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="genero" class="block text-sm font-medium text-gray-700">Género</label>
                        <select id="genero" bind:value={newPet.genero} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                            <option value="Macho">Macho</option>
                            <option value="Hembra">Hembra</option>
                        </select>
                    </div>
                    <div>
                        <label for="tamano" class="block text-sm font-medium text-gray-700">Tamaño</label>
                        <input id="tamano" type="text" bind:value={newPet.tamano} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="raza" class="block text-sm font-medium text-gray-700">Raza</label>
                        <input id="raza" type="text" bind:value={newPet.raza} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="descripcion" class="block text-sm font-medium text-gray-700">Descripción</label>
                        <textarea id="descripcion" bind:value={newPet.descripcion} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
                    </div>
                    <div>
                        <label for="fechaIngreso" class="block text-sm font-medium text-gray-700">Fecha de Ingreso</label>
                        <input id="fechaIngreso" type="date" bind:value={newPet.fechaIngreso} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="estadoSalud" class="block text-sm font-medium text-gray-700">Estado de Salud</label>
                        <input id="estadoSalud" type="text" bind:value={newPet.estadoSalud} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="vacunado" class="block text-sm font-medium text-gray-700">Vacunado</label>
                        <input id="vacunado" type="checkbox" bind:checked={newPet.vacunado} class="mt-1 block rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="esterilizado" class="block text-sm font-medium text-gray-700">Esterilizado</label>
                        <input id="esterilizado" type="checkbox" bind:checked={newPet.esterilizado} class="mt-1 block rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div>
                        <label for="tipo" class="block text-sm font-medium text-gray-700">Tipo</label>
                        <select id="tipo" bind:value={newPet.tipo} class="mt-1 block w-full rounded px-3 py-2 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
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
                        Guardar
                    </button>
                </div>
            </form>
        </div>
    </div>
{/if}