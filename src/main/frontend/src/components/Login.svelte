<script>
    import axios from "axios";
    import {onMount} from 'svelte';
    import {goto} from "$app/navigation";
    import {changeAuth} from "../authContext.svelte.js";

    axios.defaults.withCredentials = true;

    let csrfToken = '';

    // Esta función obtiene el valor del token CSRF de las cookies
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
        return null;
    }

    // Esta función se ejecuta cuando el componente se monta
    onMount(() => {
        // Obtener el token CSRF desde la cookie
        csrfToken = getCookie('XSRF-TOKEN');
        if (!csrfToken) {
            console.error('No se encontró el token CSRF');
        }
    });

    let email = '';
    let password = '';

    const login = async () => {
        try {
            console.log('Iniciando sesion...');
            console.log('CSRF Token enviado:', csrfToken);
            const response = await axios.post('http://localhost:8081/api/users/login', {
                email,
                password,
            }, {
                headers: {
                    'content-type': 'application/json',
                    'X-XSRF-TOKEN': csrfToken,
                },
            });
            console.log('Login exitoso:', response.data);
            if (response.status === 200) {
                const userRole = response.data.role; // Obtener el rol del usuario de la respuesta
                console.log('Rol del usuario:', userRole);
                changeAuth(true, userRole); // Cambia el estado de autenticación a verdadero
                await goto('/'); // Redirige al usuario a la página principal
            }
            return response.data; // Devuelve datos de la respuesta si es necesario
        } catch (error) {
            console.error('Error en el login:', error.response?.data?.message || error.message);
            throw error; // Propaga el error para manejarlo en la interfaz
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        await login();
    };
</script>

<!-- Log In -->
<div class="bg-gray-50 flex flex-col justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
        <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Iniciar Sesión
        </h2>
        <p class="mt-2 text-center text-sm text-gray-600">
            ¿No tienes una cuenta?
            <a href="../Registro" class="font-medium text-emerald-800 hover:text-emerald-600">
                Regístrate aquí
            </a>
        </p>
    </div>

    <div class="mt-8 sm:mx-auto sm:w-full sm:max-w-md pb-40">
        <div class="bg-gray-400 rounded-lg py-8 px-4 shadow sm:rounded-lg sm:px-10">
            <form class="space-y-6" on:submit={handleSubmit}>
                <!-- Email -->
                <div>
                    <label for="email" class="block text-sm font-medium text-gray-700">
                        Correo electrónico
                    </label>
                    <div class="mt-1">
                        <input
                                id="email"
                                name="email"
                                type="email"
                                bind:value={email}
                                required
                                class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-green-500 focus:border-green-500"
                        />
                    </div>
                </div>

                <!-- Password -->
                <div>
                    <label for="password" class="block text-sm font-medium text-gray-700">
                        Contraseña
                    </label>
                    <div class="mt-1">
                        <input
                                id="password"
                                name="password"
                                type="password"
                                bind:value={password}
                                required
                                class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-green-500 focus:border-green-500"
                        />
                    </div>
                </div>

                <!-- Boton Sesion -->
                <div>
                    <button
                            type="submit"
                            class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-400"
                    >
                        Iniciar Sesión
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>