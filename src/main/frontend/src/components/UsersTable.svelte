// UsersTable.svelte
<script>
    import axios from 'axios';
    import StatCard from "./StatCard.svelte";
    import UsersTable from "./UsersTable.svelte";

    export let users = [];

    let $state = {
        loading: false,
        error: null
    };

    async function handleDeleteUser(userId) {
        if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
            $state.loading = true;
            try {
                await axios.delete(`http://localhost:8081/api/admin/users/${userId}`, {
                    withCredentials: true
                });
                dispatch('refresh');
            } catch (error) {
                $state.error = 'Error al eliminar usuario';
                console.error('Error eliminando usuario:', error);
            } finally {
                $state.loading = false;
            }
        }
    }

    async function handleToggleRole(userId, currentRole) {
        $state.loading = true;
        const newRole = currentRole === 'ADMIN' ? 'USER' : 'ADMIN';
        try {
            await axios.patch(`http://localhost:8081/api/admin/users/${userId}/role`, {
                role: newRole
            }, {
                withCredentials: true
            });
            dispatch('refresh');
        } catch (error) {
            $state.error = 'Error al cambiar rol';
            console.error('Error actualizando rol:', error);
        } finally {
            $state.loading = false;
        }
    }
</script>

<div class="overflow-x-auto">
    {#if $state.error}
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4">
            {$state.error}
        </div>
    {/if}

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
                Rol
            </th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Acciones
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        {#each users as user}
            <tr>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm font-medium text-gray-900">
                        {user.nombres} {user.apellidos}
                    </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{user.email}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">{user.telefono}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                        <span class={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full ${
                            user.role === 'ADMIN' ? 'bg-emerald-100 text-emerald-800' : 'bg-gray-100 text-gray-800'
                        }`}>
                            {user.role}
                        </span>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                    <button
                            on:click={() => handleToggleRole(user.id, user.role)}
                            disabled={$state.loading}
                            class="text-emerald-600 hover:text-emerald-900 mr-4 disabled:opacity-50"
                    >
                        Cambiar Rol
                    </button>
                    <button
                            on:click={() => handleDeleteUser(user.id)}
                            disabled={$state.loading}
                            class="text-red-600 hover:text-red-900 disabled:opacity-50"
                    >
                        Eliminar
                    </button>
                </td>
            </tr>
        {/each}
        </tbody>
    </table>
</div>