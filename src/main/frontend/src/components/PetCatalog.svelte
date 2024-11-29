<script>
    import axios from 'axios';
    import PetCard from './PetCard.svelte';
    import PetModal from './PetModal.svelte';

    let pets = $state([]);
    let selectedPet = $state(null);
    let showModal = $state(false);

    $effect(async () => {
        await loadPets();
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

    function openModal(pet) {
        selectedPet = pet;
        showModal = true;
    }

    function closeModal() {
        showModal = false;
    }
</script>

<div class="container mx-auto px-4 py-8">
    <div class="flex gap-8">
        <main class="flex-1">
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {#each pets as pet (pet.id)}
                    <PetCard {pet} onclick={() => openModal(pet)} />
                {/each}
            </div>
        </main>
    </div>
</div>

{#if showModal}
    <PetModal {selectedPet} onClose={closeModal} />
{/if}