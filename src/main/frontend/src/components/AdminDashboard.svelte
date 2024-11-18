// AdminDashboard.svelte
<script>
    import axios from 'axios';
    import { getAuth } from '../authContext.svelte.js';

    let $state = {
        activeTab: 'usuarios',
        users: [],
        stats: {
            totalUsers: 0,
            totalPets: 0,
            totalDonations: 0,
            pendingAdoptions: 0
        }
    };

    $effect(() => {
        loadStats();
        loadUsers();
    });

    async function loadStats() {
        try {
            const response = await axios.get('http://localhost:8081/api/admin/stats', {
                withCredentials: true
            });
            $state.stats = response.data;
        } catch (error) {
            console.error('Error cargando estadísticas:', error);
        }
    }

    async function loadUsers() {
        try {
            const response = await axios.get('http://localhost:8081/api/admin/users', {
                withCredentials: true
            });
            $state.users = response.data;
        } catch (error) {
            console.error('Error cargando usuarios:', error);
        }
    }

    // Función para actualizar el tab activo
    function setActiveTab(tab) {
        $state.activeTab = tab;
    }
</script>

<div class="min-h-screen bg-gray-100">
    <!-- Header del Dashboard -->
    <header class="bg-emerald-600 shadow">
        <div class="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
            <h1 class="text-3xl font-bold tracking-tight text-white">Panel de Administración</h1>
        </div>
    </header>

    <!-- Contenido Principal -->
    <main class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <!-- Tarjetas de Estadísticas -->
        <div class="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-4 mb-6">
            <StatCard title="Total Usuarios" value={$state.stats.totalUsers} icon="users" />
            <StatCard title="Total Mascotas" value={$state.stats.totalPets} icon="paw" />
            <StatCard title="Total Donaciones" value={$state.stats.totalDonations} icon="dollar" />
            <StatCard title="Adopciones Pendientes" value={$state.stats.pendingAdoptions} icon="clock" />
        </div>

        <!-- Tabs de Navegación -->
        <div class="bg-white shadow rounded-lg mb-6">
            <nav class="flex space-x-4 p-4">
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${$state.activeTab === 'usuarios' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        on:click={() => setActiveTab('usuarios')}
                >
                    Usuarios
                </button>
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${$state.activeTab === 'mascotas' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        on:click={() => setActiveTab('mascotas')}
                >
                    Mascotas
                </button>
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${$state.activeTab === 'donaciones' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        on:click={() => setActiveTab('donaciones')}
                >
                    Donaciones
                </button>
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${$state.activeTab === 'adopciones' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        on:click={() => setActiveTab('adopciones')}
                >
                    Adopciones
                </button>
            </nav>
        </div>

        <!-- Contenido de los Tabs -->
        <div class="bg-white shadow rounded-lg p-6">
            {#if $state.activeTab === 'usuarios'}
                <UsersTable users={$state.users} on:refresh={loadUsers} />
                <!--
            {:else if $state.activeTab === 'mascotas'}
                <PetsTable />
            {:else if $state.activeTab === 'donaciones'}
                <DonationsTable />
            {:else if $state.activeTab === 'adopciones'}
                <AdoptionsTable />
                -->
            {/if}
        </div>
    </main>
</div>

// StatCard.svelte
<script>
    export let title;
    export let value;
    export let icon;
</script>

<div class="bg-white overflow-hidden shadow rounded-lg">
    <div class="p-5">
        <div class="flex items-center">
            <div class="flex-shrink-0">
                {#if icon === 'users'}
                    <svg class="h-6 w-6 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                    </svg>
                {:else if icon === 'paw'}
                    <svg class="h-6 w-6 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5" />
                    </svg>
                {:else if icon === 'dollar'}
                    <svg class="h-6 w-6 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                {:else if icon === 'clock'}
                    <svg class="h-6 w-6 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                {/if}
            </div>
            <div class="ml-5 w-0 flex-1">
                <dl>
                    <dt class="text-sm font-medium text-gray-500 truncate">
                        {title}
                    </dt>
                    <dd class="text-lg font-medium text-gray-900">
                        {value}
                    </dd>
                </dl>
            </div>
        </div>
    </div>
</div>