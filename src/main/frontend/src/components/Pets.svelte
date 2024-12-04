<script>
    import { onMount } from 'svelte';
    import axios from 'axios';
    import { getAuth } from '../authContext.svelte.js';
    import { Modal, Button } from 'flowbite-svelte';

    let pets2 = $state([]);
    let selectedPet2 = $state(null);
    let isModalOpen2 = $state(false);
    let isAdoptModalOpen2 = $state(false);
    let motivoAdopcion = '';

    const isAuthenticated = $derived(getAuth().isAuthenticated);
    const userId = $derived(getAuth().id);

    onMount(async () => {
        try {
            const response = await axios.get('http://localhost:8081/api/pets');
            pets2 = response.data;
        } catch (error) {
            console.error('Error fetching pets:', error);
        }
    });

    function openPetDetails(pet) {
        selectedPet2 = pet;
        isModalOpen2 = true;
    }

    function openAdoptionModal(pet) {
        if (!isAuthenticated) {
            window.location.href = './Login';
            return;
        }
        selectedPet2 = pet;
        isAdoptModalOpen2 = true;
    }

    async function submitAdoptionRequest() {
        if (!isAuthenticated) return;

        const fechaAdopcion = new Date().toISOString().split('T')[0]; // YYYY-MM-DD

        const adoption = {
            petId: selectedPet2.id,
            userId: userId,
            fechaAdopcion: fechaAdopcion,
            estado: 'PENDIENTE',
            motivoAdopcion: motivoAdopcion
        };

        console.log('Adoption Request Data:', adoption);
        try {
            await axios.post('http://localhost:8081/api/adoption', adoption, { withCredentials: true });
            alert('Solicitud de adopción enviada exitosamente');
            isAdoptModalOpen2 = false;
        } catch (error) {
            console.error('Error submitting adoption request:', error);
            alert('Hubo un error al enviar la solicitud de adopción');
        }
    }
</script>

<div class="container mx-auto p-4">
    <h2 class="text-3xl font-bold mb-6 text-center">Mascotas Disponibles</h2>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {#each pets2 as pet}
            {#if !pet.adoptado}
                <div class="bg-white shadow-lg rounded-lg overflow-hidden">
                    <img
                            src={`../../images/pets/${pet.nombre.toLowerCase()}.jpg`}
                            alt={pet.nombre}
                            class="w-full h-64 object-cover"
                    />
                    <div class="p-4">
                        <h3 class="text-xl font-semibold">{pet.nombre}</h3>
                        <p class="text-gray-600">Edad: {pet.edad}</p>
                        <p class="text-gray-600">Raza: {pet.raza}</p>
                        <p class="text-gray-600">Género: {pet.genero}</p>
                        <div class="flex justify-between mt-4">
                            <Button
                                    on:click={() => openPetDetails(pet)}
                                    class="bg-emerald-600 hover:bg-emerald-800"
                            >
                                Ver más
                            </Button>
                                <Button
                                        on:click={() => openAdoptionModal(pet)}
                                        class="bg-blue-600 hover:bg-blue-800"
                                >
                                    Adoptar
                                </Button>
                        </div>
                    </div>
                </div>
            {/if}
        {/each}
    </div>

    {#if selectedPet2}
        <!-- Modal de cada mascota -->
        <Modal bind:open={isModalOpen2} size="lg">
            <div class="p-4">
                <h2 class="text-2xl font-bold mb-4">{selectedPet2.nombre}</h2>
                <img
                        src={`../../images/pets/${selectedPet2.nombre.toLowerCase()}.jpg`}
                        alt={selectedPet2.nombre}
                        class="w-full h-96 object-cover mb-4"
                />
                <div class="grid grid-cols-2 gap-4">
                    <p><strong>Edad:</strong> {selectedPet2.edad}</p>
                    <p><strong>Raza:</strong> {selectedPet2.raza}</p>
                    <p><strong>Género:</strong> {selectedPet2.genero}</p>
                    <p><strong>Tamaño:</strong> {selectedPet2.tamano}</p>
                    <p><strong>Tipo:</strong> {selectedPet2.tipo}</p>
                    <p><strong>Estado de Salud:</strong> {selectedPet2.estadoSalud}</p>
                    <p><strong>Vacunado:</strong> {selectedPet2.vacunado ? 'Sí' : 'No'}</p>
                    <p><strong>Esterilizado:</strong> {selectedPet2.esterilizado ? 'Sí' : 'No'}</p>
                </div>
                <p class="mt-4"><strong>Descripción:</strong> {selectedPet2.descripcion}</p>

                {#if !selectedPet2.adoptado}
                    <div class="mt-6 flex justify-center">
                        <Button
                                on:click={() => openAdoptionModal(selectedPet2)}
                                class="bg-blue-600 hover:bg-blue-800"
                        >
                            Solicitar Adopción
                        </Button>
                    </div>
                {/if}
            </div>
        </Modal>

        <!-- Modal Adopciones -->
        <Modal bind:open={isAdoptModalOpen2} size="md">
            <div class="p-4">
                <h2 class="text-2xl font-bold mb-4">Solicitud de Adopción: {selectedPet2.nombre}</h2>
                <p class="mb-4">Por favor, indique el motivo de la adopción:</p>
                <textarea bind:value={motivoAdopcion} class="w-full p-2 border rounded"></textarea>
                <p class="mt-4">¿Estás seguro que quieres solicitar la adopción de {selectedPet2.nombre}?</p>
                <div class="flex justify-between mt-4">
                    <Button
                            on:click={submitAdoptionRequest}
                            class="bg-green-600 hover:bg-green-800"
                    >
                        Confirmar Solicitud
                    </Button>
                    <Button
                            on:click={() => isAdoptModalOpen2 = false}
                            class="bg-red-600 hover:bg-red-800"
                    >
                        Cancelar
                    </Button>
                </div>
            </div>
        </Modal>
    {/if}
</div>