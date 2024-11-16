<script>
    export let title;
    export let amount;
    export let isCustom = false;

    let customAmount = 100; // Valor inicial en el slider

    function handleDonate() {
        const donationAmount = isCustom ? customAmount : amount;
        console.log(`Procesando donación de $${donationAmount}`);
    }
</script>

<div class="bg-gray-400 rounded-lg shadow-md p-6 transition-transform hover:scale-105">
    <h3 class="text-xl font-semibold text-emerald-800 mb-4">{title}</h3>

    {#if !isCustom}
        <p class="text-3xl font-bold text-emerald-600 mb-4">${amount}</p>
    {:else}
        <div class="mb-4">
            <label for="donation-slider" class="text-lg font-semibold text-emerald-800 mb-2">Elige tu cantidad de donación</label>
            <input
                    type="range"
                    id="donation-slider"
                    min="100"
                    max="2000"
                    step="50"
                    bind:value={customAmount}
                    class="w-full h-2 bg-emerald-200 rounded-full appearance-none cursor-pointer focus:outline-none focus:ring-2 focus:ring-emerald-500"
            />
            <div class="flex justify-between text-sm text-gray-600">
                <span>$100</span>
                <span>${customAmount}</span>
                <span>$2000</span>
            </div>
        </div>
    {/if}

    <button
            on:click={handleDonate}
            class="w-full bg-emerald-600 text-white py-2 px-4 rounded-md hover:bg-emerald-700 transition-colors"
    >
        Donar
    </button>
</div>
