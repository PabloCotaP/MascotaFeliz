<script>
    import axios from "axios";
    import {changeAuth, getAuth} from "../authContext.svelte.js";
    import {goto} from "$app/navigation";

    let user = $state({});
    let adoptions2 = $state([]);
    let pets4 = $state([]);
    const auth = getAuth();

    $effect(() => {
        loadPets();
        loadUserData();
        loadUserAdoptions();
    });

    function findPetById(id) {
        const pet = pets4.find(p => p.id === id);
        return pet ? pet.nombre : 'Desconocido';
    }

    async function loadPets() {
        try {
            const response = await axios.get('http://localhost:8081/api/pets', {
                withCredentials: true
            });
            pets4 = response.data;
        } catch (error) {
            console.error('Error cargando mascotas:', error);
        }
    }

    async function loadUserData() {
        const response = await axios.get("http://localhost:8081/api/users", { withCredentials: true });
        user = response.data.find(u => u.id === auth.id);
    }

    async function loadUserAdoptions() {
        const response = await axios.get('http://localhost:8081/api/adoption', { withCredentials: true });
        adoptions2 = response.data.filter(adoption => adoption.userId === auth.id);
        console.log(adoptions2);
    }

    async function logout() {
        try {
            await axios.get('http://localhost:8081/api/users/logout', {}, { withCredentials: true });
            changeAuth(false, 'USER', null);
            await goto('/');
        } catch (error) {
            console.error('Error al cerrar sesión', error);
        }
    }

    const handleLogout = async () => {
        await logout();
    };
</script>

<div class="max-w-6xl mx-auto p-6 space-y-6 bg-white rounded-lg shadow-lg">
    <!-- Información del Usuario -->
    {#if user}
        <div>
            <h2 class="text-2xl font-bold text-gray-700">Información del Usuario</h2>
            <div class="mt-4 grid grid-cols-2 gap-4">
                <div>
                    <p><strong>Nombre:</strong> {user.nombres} {user.apellidos}</p>
                </div>
                <div>
                    <p><strong>Email:</strong> {user.email}</p>
                </div>
                <div>
                    <p><strong>Teléfono:</strong> {user.telefono}</p>
                </div>
            </div>
        </div>
    {/if}

    <!-- Lista de Adopciones -->
    <div>
        <h2 class="text-2xl font-bold text-gray-700">Mis Adopciones</h2>
        <div class="mt-4 space-y-4">
            {#each ['ACEPTADO', 'PENDIENTE', 'RECHAZADO'] as estado}
                <div>
                    <h3 class="text-lg font-semibold text-gray-600">{estado}</h3>
                    {#if adoptions2.filter(adoption => adoption.estado === estado).length === 0}
                        <p class="text-sm text-gray-500">No hay adopciones {estado.toLowerCase()}.</p>
                    {:else}
                        <ul class="divide-y divide-gray-200">
                            {#each adoptions2.filter(adoption => adoption.estado === estado) as adoption}
                                <li class="py-2">
                                    <div class="flex justify-between items-center">
                                        <div>
                                            <p><strong>Mascota:</strong> {findPetById(adoption.petId)}</p>
                                            <p><strong>Fecha:</strong> {adoption.fechaAdopcion}</p>
                                            <p><strong>Motivo:</strong> {adoption.motivoAdopcion}</p>
                                        </div>
                                        <div class={`text-sm font-medium ${
                                        estado === 'ACEPTADO' ? 'text-green-500' :
                                        estado === 'RECHAZADO' ? 'text-red-500' : 'text-gray-500'
                                    }`}>
                                            {estado}
                                        </div>
                                    </div>
                                </li>
                            {/each}
                        </ul>
                    {/if}
                </div>
            {/each}
        </div>
    </div>

    <!-- Botón de Cerrar Sesión -->
    <div class="flex justify-end">
        <button
                class="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded"
                onclick={handleLogout}
        >
            Cerrar Sesión
        </button>
    </div>
</div>
