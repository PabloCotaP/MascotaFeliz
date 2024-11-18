// Crea un estado con la runa $state
const authState = $state({ isAuthenticated: false, role: 'USER' });

export function changeAuth(status, role = 'USER') {
    authState.isAuthenticated = status;
    authState.role = role;
}

export function getAuth() {
    return authState;
}