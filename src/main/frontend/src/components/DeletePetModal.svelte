<script>
    import axios from 'axios';

    // Definir las propiedades del componente usando $props()
    let { showDeleteModal, currentPet, onPetDeleted } = $props();

    function handleClose() {
        showDeleteModal = false;
    }

    async function handleDelete() {
        try {
            await axios.delete(`http://localhost:8081/api/pets/${currentPet.id}`, {
                withCredentials: true
            });
            showDeleteModal = false;
            // Llamar a la función para actualizar la lista de mascotas
            onPetDeleted();
        } catch (error) {
            console.error('Error eliminando la mascota:', error);
        }
    }
</script>

{#if showDeleteModal}
    <div class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center">
        <div class="bg-white p-5 rounded-lg shadow-xl max-w-md w-full">
            <h2 class="text-xl font-bold mb-4">Confirmar Eliminación</h2>
            <p class="text-gray-600 mb-6">
                ¿Estás seguro de que deseas eliminar a {currentPet?.nombre}? Esta acción no se puede deshacer.
            </p>
            <div class="flex justify-end gap-2">
                <button
                        onclick={handleClose}
                        class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600"
                >
                    Cancelar
                </button>
                <button
                        onclick={handleDelete}
                        class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                >
                    Eliminar
                </button>
            </div>
        </div>
    </div>
{/if}