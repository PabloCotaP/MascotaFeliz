<script>
    import axios from "axios";

    let firstName = '';
    let lastName = '';
    let email = '';
    let password = '';
    let confirmPassword = '';
    let phone = '';

    // Función para formatear el teléfono
    function formatPhoneNumber(value) {
        if (!value) return value;

        // Eliminar todo lo que no sea número
        const phoneNumber = value.replace(/\D/g, '');

        // Limitar a 10 dígitos
        const phoneNumberLength = phoneNumber.length;
        if (phoneNumberLength < 4) return phoneNumber;
        if (phoneNumberLength < 7) {
            return `(${phoneNumber.slice(0, 3)})-${phoneNumber.slice(3)}`;
        }
        return `(${phoneNumber.slice(0, 3)})-${phoneNumber.slice(3, 6)}-${phoneNumber.slice(6, 10)}`;
    }

    // Manejador del evento input para el teléfono
    function handlePhoneInput(event) {
        // Obtener solo los números del valor actual
        const inputValue = event.target.value.replace(/\D/g, '');

        // Limitar a 10 dígitos
        if (inputValue.length <= 10) {
            phone = formatPhoneNumber(inputValue);
        }
    }

    // Validar que sea un número de teléfono válido
    function isValidPhone(phoneNumber) {
        const cleaned = phoneNumber.replace(/\D/g, '');
        return cleaned.length === 10;
    }

    const register = async () => {
        try {
            const response = await axios.post('http://localhost:8081/api/users/register', {
                firstName,
                lastName,
                email,
                password,
                phone,
            });
            console.log('Registro exitoso:', response.data);
            return response.data; // Devuelve datos de la respuesta si es necesario
        } catch (error) {
            console.error('Error en el registro:', error.response?.data?.message || error.message);
            throw error; // Propaga el error para manejarlo en la interfaz
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        // Validar que las contraseñas coincidan
        if (password !== confirmPassword) {
            alert('Las contraseñas no coinciden');
            return;
        }
        // Validar el teléfono
        if (!isValidPhone(phone)) {
            alert('Por favor, ingrese un número de teléfono válido de 10 dígitos');
            return;
        }
        // Llama a la función de registro
        await register();
    };
</script>

<!-- Register -->
<div class="bg-gray-50 flex flex-col justify-center pb-12 pt-6 px-4 sm:px-6 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
        <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Crear una cuenta
        </h2>
        <p class="mt-2 text-center text-sm text-gray-600">
            ¿Ya tienes una cuenta?
            <a href="../Login" class="font-medium text-emerald-800 hover:text-emerald-600">
                Inicia sesión aquí
            </a>
        </p>
    </div>

    <div class="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div class="bg-gray-400 rounded-lg py-8 px-4 shadow sm:rounded-lg sm:px-10">
            <form class="space-y-6" on:submit={handleSubmit}>
                <!-- Nombre(s) -->
                <div>
                    <label for="firstName" class="block text-sm font-medium text-gray-700">
                        Nombre(s)
                    </label>
                    <div class="mt-1">
                        <input
                                id="firstName"
                                name="firstName"
                                type="text"
                                bind:value={firstName}
                                required
                                class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-green-500 focus:border-green-500"
                        />
                    </div>
                </div>

                <!-- Apellidos -->
                <div>
                    <label for="lastName" class="block text-sm font-medium text-gray-700">
                        Apellidos
                    </label>
                    <div class="mt-1">
                        <input
                                id="lastName"
                                name="lastName"
                                type="text"
                                bind:value={lastName}
                                required
                                class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-green-500 focus:border-green-500"
                        />
                    </div>
                </div>

                <!-- Teléfono -->
                <div>
                    <label for="phone" class="block text-sm font-medium text-gray-700">
                        Teléfono
                    </label>
                    <div class="mt-1">
                        <input
                                id="phone"
                                name="phone"
                                type="tel"
                                bind:value={phone}
                                on:input={handlePhoneInput}
                                required
                                maxlength="14"
                                class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-green-500 focus:border-green-500"
                        />
                    </div>
                </div>

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

                <!-- Contraseña -->
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
                                minlength="8"
                                class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-green-500 focus:border-green-500"
                        />
                    </div>
                </div>

                <!-- Confirmar Contraseña -->
                <div>
                    <label for="confirmPassword" class="block text-sm font-medium text-gray-700">
                        Confirmar Contraseña
                    </label>
                    <div class="mt-1">
                        <input
                                id="confirmPassword"
                                name="confirmPassword"
                                type="password"
                                bind:value={confirmPassword}
                                required
                                minlength="8"
                                class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-green-500 focus:border-green-500"
                        />
                    </div>
                </div>

                <!-- Botón Registro -->
                <div>
                    <button
                            type="submit"
                            class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-400"
                    >
                        Registrar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>