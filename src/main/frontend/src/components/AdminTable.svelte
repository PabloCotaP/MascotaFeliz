<script>
    import axios from "axios";

    let users1 = $state([]);
    let currentUser1 = $state(null);
    let showUserModal = $state(false);

    $effect(() => {
        loadUsers();
    });

    async function loadUsers() {
        try {
            const response = await axios.get('http://localhost:8081/api/users', {
                withCredentials: true
            });
            users1 = response.data;
        } catch (error) {
            console.error('Error cargando usuarios:', error);
        }
    }

    function openUserModal(user) {
        currentUser1 = { ...user };
        showUserModal = true;
    }

    function closeUserModal() {
        showUserModal = false;
    }
</script>

<div class="overflow-x-auto">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Nombre
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Email
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Teléfono
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Acciones
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        {#each users1 as user}
            {#if user.role === 'ADMIN'}
                <tr>
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm font-medium text-gray-900">{user.nombres} {user.apellidos}</div>
                    </td>
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500">{user.email}</div>
                    </td>
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500">{user.telefono}</div>
                    </td>
                    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                        <button
                                onclick={() => openUserModal(user)}
                                class="text-indigo-600 hover:text-indigo-900 bg-indigo-100 hover:bg-indigo-200 px-3 py-1 rounded-md flex items-center gap-1"
                        >
                            Ver más
                        </button>
                    </td>
                </tr>
            {/if}
        {/each}
        </tbody>
    </table>
</div>

{#if showUserModal}
    <div class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center">
        <div class="bg-white p-5 rounded-lg shadow-xl max-w-3xl w-full">
            <h2 class="text-xl font-bold mb-4">Detalles del Usuario</h2>
            <div class="space-y-4">
                <h3 class="text-lg font-semibold">Información Personal</h3>
                <div class="grid grid-cols-2 gap-4">
                    <div>
                        <p><strong>Nombre:</strong> {currentUser1.nombres} {currentUser1.apellidos}</p>
                    </div>
                    <div>
                        <p><strong>Email:</strong> {currentUser1.email}</p>
                    </div>
                    <div>
                        <p><strong>Teléfono:</strong> {currentUser1.telefono}</p>
                    </div>
                    <div>
                        <p><strong>Rol:</strong> {currentUser1.role}</p>
                    </div>
                </div>
            </div>

            <div class="flex justify-end gap-2 mt-4">
                <button onclick={closeUserModal} class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">Cerrar</button>
            </div>
        </div>
    </div>
{/if}
