<script>

    let valuesUsersTable = $state({
        loading: false,
        error: null
    });
    let users = [];
</script>

<div class="overflow-x-auto">
    {#if valuesUsersTable.error}
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4">
            {valuesUsersTable.error}
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
                            disabled={valuesUsersTable.loading}
                            class="text-emerald-600 hover:text-emerald-900 mr-4 disabled:opacity-50"
                    >
                        Cambiar Rol
                    </button>
                    <button
                            disabled={valuesUsersTable.loading}
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