// Crea un estado con la runa $state
const authState = $state({ isAuthenticated: false, role: 'USER', id: null });

export function changeAuth(status, role = 'USER', id = null) {
    authState.isAuthenticated = status;
    authState.role = role;
    authState.id = id;
}

export function getAuth() {
    return authState;
}