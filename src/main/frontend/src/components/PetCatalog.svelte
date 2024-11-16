<script>
    import FilterSidebar from './FilterSidebar.svelte';
    import SearchBar from './SearchBar.svelte';
    import PetGrid from './PetGrid.svelte';

    let pets = [
        {
            id: '1',
            name: 'Max',
            age: 2,
            type: 'perro',
            gender: 'Macho',
            size: 'Mediano',
            image: '../../images/pets/max.jpg'
        },
        {
            id: '2',
            name: 'Lucy',
            age: 2,
            type: 'gato',
            gender: 'Hembra',
            size: 'Chico',
            image: '../../images/pets/lucy.jpg'
        },
        {
            id: '3',
            name: 'Waffle',
            age: 4,
            type: 'perro',
            gender: 'Macho',
            size: 'Grande',
            image: '../../images/pets/waffle.jpg'
        },
        {
            id: '4',
            name: 'Araña',
            age: 3,
            type: 'gato',
            gender: 'Macho',
            size: 'Mediano',
            image: '../../images/pets/arana.jpg'
        },
        {
            id: '5',
            name: 'Gundam',
            age: 1,
            type: 'perro',
            gender: 'Macho',
            size: 'Grande',
            image: '../../images/pets/gundam.jpg'
        },
        {
            id: '6',
            name: 'Tesoro',
            age: 6,
            type: 'perro',
            gender: 'Hembra',
            size: 'Grande',
            image: '../../images/pets/tesoro.jpg'
        },
    ];
</script>

<div class="container mx-auto px-4 py-8">
    <div class="flex gap-8">

        <main class="flex-1">
            <SearchBar />
            <PetGrid {pets} />
        </main>
    </div>
</div>