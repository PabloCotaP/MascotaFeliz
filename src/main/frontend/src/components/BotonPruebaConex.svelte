<script>
    import axios from 'axios';

    async function pruebaConex() {
        try {
            const response = await axios.get('http://localhost:8081/api/users/test');
            console.log(response.data);
        } catch (error) {
            console.error(error);
        }
    }
</script>

<button on:click={pruebaConex} class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-400">Probar Conexión</button>