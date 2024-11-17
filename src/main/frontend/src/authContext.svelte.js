// Crea un estado con la runa $state
const authState = $state({ isAuthenticated: false });

export function changeAuth(status) {
    authState.isAuthenticated = status;
}

export function getAuth() {
    return authState;
}