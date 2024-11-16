<script>
    import PetCard from './PetCard.svelte';

    export let pets = [];
</script>

<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
    {#each pets as pet (pet.id)}
        <PetCard {pet} />
    {/each}
</div>