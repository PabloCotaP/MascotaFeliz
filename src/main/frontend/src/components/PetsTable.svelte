<script>
    import AddPetModal from './AddPetModal.svelte';
    import EditPetModal from './EditPetModal.svelte';
    import DeletePetModal from './DeletePetModal.svelte';
    import axios from "axios";

    let pets = $state([]);
    let currentPet = $state(null);
    let showAddModal = $state(false);
    let showEditModal = $state(false);
    let showDeleteModal = $state(false);

    $effect(() => {
        loadPets();
    });

    async function loadPets() {
        try {
            const response = await axios.get('http://localhost:8081/api/pets', {
                withCredentials: true
            });
            pets = response.data;
        } catch (error) {
            console.error('Error cargando mascotas:', error);
        }
    }

    // Función para abrir el modal de agregar
    function openAddModal() {
        showAddModal = true;
    }

    // Función para abrir el modal de editar
    function openEditModal(pet) {
        currentPet = {...pet};
        showEditModal = true;
    }

    // Función para abrir el modal de eliminar
    function openDeleteModal(pet) {
        currentPet = pet;
        showDeleteModal = true;
    }

    // Función para actualizar la lista de mascotas después de agregar una nueva
    function onPetAdded() {
        loadPets();
    }

    // Función para actualizar la lista de mascotas después de editar una mascota
    function onPetEdited() {
        loadPets();
    }

    // Función para actualizar la lista de mascotas después de eliminar una mascota
    function onPetDeleted() {
        loadPets();
    }
</script>

<!-- Botón Agregar Nueva Mascota -->
<div class="my-4">
    <button
            onclick={openAddModal}
            class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded flex items-center gap-2"
    >
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"/>
        </svg>
        Agregar Nueva Mascota
    </button>
</div>

<div class="overflow-x-auto">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Nombre
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Edad
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Género
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Tamaño
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Raza
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Descripción
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Fecha de Ingreso
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Estado de Salud
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Vacunado
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Esterilizado
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Tipo
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Acciones
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        {#each pets as pet}
            <tr>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class={`text-sm font-medium ${pet.adoptado ? 'text-green-900' : 'text-gray-900'}`}>{pet.nombre}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.edad}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.genero}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.tamano}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.raza}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.descripcion}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.fechaIngreso}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.estadoSalud}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.vacunado ? 'Sí' : 'No'}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.esterilizado ? 'Sí' : 'No'}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{pet.tipo}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                    <div class="flex gap-2">
                        <button
                                onclick={() => openEditModal(pet)}
                                class="text-indigo-600 hover:text-indigo-900 bg-indigo-100 hover:bg-indigo-200 px-3 py-1 rounded-md flex items-center gap-1"
                        >
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                      d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"/>
                            </svg>
                            Editar
                        </button>
                        <button
                                onclick={() => openDeleteModal(pet)}
                                class="text-red-600 hover:text-red-900 bg-red-100 hover:bg-red-200 px-3 py-1 rounded-md flex items-center gap-1"
                        >
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                      d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
                            </svg>
                            Eliminar
                        </button>
                    </div>
                </td>
            </tr>
        {/each}
        </tbody>
    </table>
</div>

<AddPetModal {showAddModal}/>
<EditPetModal {showEditModal} {currentPet}/>
<DeletePetModal {showDeleteModal} {currentPet}/>