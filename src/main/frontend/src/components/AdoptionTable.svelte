<script>
    import axios from "axios";

    let adoptions = $state([]);
    let users2 = $state([]);
    let pets3 = $state([]);
    let currentAdoption = $state(null);
    let showEditModal = $state(false);

    $effect(() => {
        loadAdoptions();
        loadUsers();
        loadPets();
    });

    async function loadAdoptions() {
        try {
            const response = await axios.get('http://localhost:8081/api/adoption', {
                withCredentials: true
            });
            adoptions = response.data;
        } catch (error) {
            console.error('Error cargando adopciones:', error);
        }
    }

    async function loadUsers() {
        try {
            const response = await axios.get('http://localhost:8081/api/users', {
                withCredentials: true
            });
            users2 = response.data;
        } catch (error) {
            console.error('Error cargando usuarios:', error);
        }
    }

    async function loadPets() {
        try {
            const response = await axios.get('http://localhost:8081/api/pets', {
                withCredentials: true
            });
            pets3 = response.data;
        } catch (error) {
            console.error('Error cargando mascotas:', error);
        }
    }

    function findUserById(id) {
        const user = users2.find(u => u.id === id);
        return user ? `${user.nombres} ${user.apellidos}` : 'Desconocido';
    }

    function findPetById(id) {
        const pet = pets3.find(p => p.id === id);
        return pet ? pet.nombre : 'Desconocido';
    }

    function openEditModal(adoption) {
        currentAdoption = { ...adoption };
        showEditModal = true;
    }

    async function updateAdoptionStatus(status) {
        try {
            // Actualiza el estado de la adopción
            const updatedAdoption = {...currentAdoption, estado: status};
            await axios.put(
                `http://localhost:8081/api/adoption/${currentAdoption.id}`,
                updatedAdoption,
                { withCredentials: true }
            );

            // Si la adopción es aceptada, actualiza el estado "adoptado" de la mascota
            if (status === 'ACEPTADO') {
                const pet = pets3.find(p => p.id === currentAdoption.petId);
                if (pet && !pet.adoptado) {
                    const petUpdated = {...pet, adoptado: true};
                    await axios.put(
                        `http://localhost:8081/api/pets/${currentAdoption.petId}`,
                        petUpdated,
                        {withCredentials: true}
                    );
                }
            } else if (status === 'RECHAZADO') {
                const pet = pets3.find(p => p.id === currentAdoption.petId);
                if (pet && pet.adoptado) {
                    const petUpdated = {...pet, adoptado: false};
                    await axios.put(
                        `http://localhost:8081/api/pets/${currentAdoption.petId}`,
                        petUpdated,
                        {withCredentials: true}
                    );
                }
            }

            await loadAdoptions();
            showEditModal = false;
        } catch (error) {
            console.error('Error actualizando el estado de la adopción o mascota:', error);
        }
    }
</script>

<div class="overflow-x-auto">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Mascota
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Usuario
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Fecha de Adopción
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Estado
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Motivo
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Acciones
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        {#each adoptions as adoption}
            <tr>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm font-medium text-gray-900">{findPetById(adoption.petId)}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{findUserById(adoption.userId)}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{adoption.fechaAdopcion}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class={`text-sm ${
                        adoption.estado === 'ACEPTADO' ? 'text-green-500' :
                        adoption.estado === 'RECHAZADO' ? 'text-red-500' :
                        'text-gray-500'
                    }`}>{adoption.estado}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{adoption.motivoAdopcion}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                    <button
                            onclick={() => openEditModal(adoption)}
                            class="text-indigo-600 hover:text-indigo-900 bg-indigo-100 hover:bg-indigo-200 px-3 py-1 rounded-md flex items-center gap-1"
                    >
                        Editar
                    </button>
                </td>
            </tr>
        {/each}
        </tbody>
    </table>
</div>

{#if showEditModal}
    <div class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center">
        <div class="bg-white p-5 rounded-lg shadow-xl max-w-3xl w-full">
            <h2 class="text-xl font-bold mb-4">Detalles de la Adopción</h2>
            <div class="space-y-4">
                <h3 class="text-lg font-semibold">Información del Usuario</h3>
                <div class="grid grid-cols-2 gap-4">
                    <div>
                        <p><strong>Nombre:</strong> {findUserById(currentAdoption.userId)}</p>
                    </div>
                    <div>
                        <p><strong>Email:</strong> {users2.find(u => u.id === currentAdoption.userId)?.email}</p>
                    </div>
                    <div>
                        <p><strong>Teléfono:</strong> {users2.find(u => u.id === currentAdoption.userId)?.telefono}</p>
                    </div>
                </div>
                <h3 class="text-lg font-semibold">Información de la Mascota</h3>
                <div class="grid grid-cols-2 gap-4">
                    <div>
                        <p><strong>Nombre:</strong> {findPetById(currentAdoption.petId)}</p>
                    </div>
                    <div>
                        <p><strong>Género:</strong> {pets3.find(p => p.id === currentAdoption.petId)?.genero}</p>
                    </div>
                    <div>
                        <p><strong>Edad:</strong> {pets3.find(p => p.id === currentAdoption.petId)?.edad}</p>
                    </div>
                    <div>
                        <p><strong>Tamaño:</strong> {pets3.find(p => p.id === currentAdoption.petId)?.tamano}</p>
                    </div>
                    <div>
                        <p><strong>Raza:</strong> {pets3.find(p => p.id === currentAdoption.petId)?.raza}</p>
                    </div>
                    <div>
                        <p><strong>Estado de Salud:</strong> {pets3.find(p => p.id === currentAdoption.petId)?.estadoSalud}</p>
                    </div>
                    <div>
                        <p><strong>Esterilizado:</strong>
                            <input type="checkbox" checked={pets3.find(p => p.id === currentAdoption.petId)?.esterilizado} disabled>
                        </p>
                    </div>
                    <div>
                        <p><strong>Vacunado:</strong>
                            <input type="checkbox" checked={pets3.find(p => p.id === currentAdoption.petId)?.vacunado} disabled>
                        </p>
                    </div>
                </div>
            </div>

            <div class="flex justify-end gap-2 mt-4">
                <button onclick={() => updateAdoptionStatus('RECHAZADO')} class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">Rechazar</button>
                <button onclick={() => updateAdoptionStatus('ACEPTADO')} class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">Aceptar</button>
                <button onclick={() => (showEditModal = false)} class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">Cerrar</button>
            </div>
        </div>
    </div>
{/if}
