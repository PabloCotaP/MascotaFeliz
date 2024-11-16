<script>
    import { Search } from 'flowbite-svelte';

    let searchQuery = '';
</script>

<div class="mb-6">
    <Search
            size="lg"
            placeholder="Buscar mascota..."
            bind:value={searchQuery}
    />
</div>