<script>
    import axios from 'axios';
    import { getAuth } from '../authContext.svelte.js';
    import UsersTable from "./UsersTable.svelte";
    import StatCard from "./StatCard.svelte";
    import PetsTable from "./PetsTable.svelte";
    import {goto} from "$app/navigation";
    import AdoptionTable from "./AdoptionTable.svelte";
    import AdminTable from "./AdminTable.svelte";
    let valuesAdmin = $state({
        activeTab: 'usuarios',
        stats: {
            totalUsers: 0,
            totalPets: 0,
            totalDonations: 0,
            pendingAdoptions: 0
        }
    });

    // Función para verificar si el usuario es de tipo ADMIN y redirigir si no lo es
    function checkUserRole() {
        const user = getAuth();
        if (!user || user.role !== 'ADMIN') {
            goto('/'); // Redirigir a la página inicial si no es ADMIN
        }
    }

    $effect(() => {
        loadStats();
        checkUserRole();
    });

    async function loadStats() {
        try {
            const response = await axios.get('http://localhost:8081/api/admin/stats', {
                withCredentials: true
            });
            valuesAdmin.stats = response.data;
        } catch (error) {
            console.error('Error cargando estadísticas:', error);
        }
    }

    // Función para actualizar el tab activo
    function setActiveTab(tab) {
        valuesAdmin.activeTab = tab;
    }
</script>

<div class="min-h-screen bg-gray-100">
    <!-- Header del Dashboard -->
    <header class="bg-emerald-600 shadow">
        <div class="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
            <h1 class="text-3xl font-bold tracking-tight text-black">Panel de Administración</h1>
        </div>
    </header>

    <!-- Contenido Principal -->
    <main class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <!-- Tarjetas de Estadísticas -->
        <div class="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-2 mb-6">
            <StatCard title="Total Usuarios" value={valuesAdmin.stats.totalUsers} icon="users" />
            <StatCard title="Total Mascotas" value={valuesAdmin.stats.totalPets} icon="bone" />
        </div>

        <!-- Tabs de Navegación -->
        <div class="bg-white shadow rounded-lg mb-6">
            <nav class="flex space-x-4 p-4">
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${valuesAdmin.activeTab === 'usuarios' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        onclick={() => setActiveTab('usuarios')}
                >
                    Usuarios
                </button>
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${valuesAdmin.activeTab === 'admins' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        onclick={() => setActiveTab('admins')}
                >
                    Empleados
                </button>
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${valuesAdmin.activeTab === 'mascotas' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        onclick={() => setActiveTab('mascotas')}
                >
                    Mascotas
                </button>
                <button
                        class={`px-3 py-2 text-sm font-medium rounded-md ${valuesAdmin.activeTab === 'adopciones' ? 'bg-emerald-600 text-white' : 'text-gray-500 hover:text-gray-700'}`}
                        onclick={() => setActiveTab('adopciones')}
                >
                    Adopciones
                </button>
            </nav>
        </div>

        <!-- Contenido de los Tabs-->
        <div class="bg-white shadow rounded-lg p-6">
            {#if valuesAdmin.activeTab === 'usuarios'}
                <UsersTable/>
            {:else if valuesAdmin.activeTab === 'admins'}
                <AdminTable/>
            {:else if valuesAdmin.activeTab === 'mascotas'}
                <PetsTable />
            {:else if valuesAdmin.activeTab === 'adopciones'}
                <AdoptionTable/>
            {/if}
        </div>
    </main>
</div>