let logoutButton = document.getElementById('logout');

function logout(event) {
    event.preventDefault();
    fetch('http://localhost:7000/logout', {
        method: 'POST', 
        credentials: 'include',
        headers: {
            'Content-Type': 'application.json'
        } 
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/index.html';
        }
    })
};

logoutButton.addEventListener('click', logout);