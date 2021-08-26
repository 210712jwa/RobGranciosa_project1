let loginButton = document.getElementById('login');
let usernameInput = document.getElementById('username');
let passwordInput = document.getElementById('password');

// When we click on the login button, we need to be able to send some kind of
// post request to out backend 
// We would need to use the Fetch API for this request 
// the 'headers' is where you will put in what key-value pairs that
// indicate what type of metadata to include with your request. 
// if you are sending a JSON request, you need to specify that you are sending JSON. 

function login(event) {
    event.preventDefault();

    const loginInfo = {
        'username': usernameInput.value,
        'password': passwordInput.value
    };

    fetch('http://localhost:7000/login', {
        method: 'POST', 
        credentials: 'include', // specifies that when you receive cookies, 
        // you should include them in future requests
        headers: {
            'Content-Type': 'application/json', // application/json is the 'MIME type'
        }, 
        body: JSON.stringify(loginInfo) //we need to take the loginInfo object and turn it into a string
        // this is required by our backend
    }).then((response) => {
        if (response.status === 200){
            window.location.href = '/viewreimbursements.html'; // redirects to specified page
        }
        else if (response.status === 400) {
            displayInvalidLogin();
        }
    })
};

function displayInvalidLogin() {
    let loginForm = document.getElementById('loginform');

    let p = document.createElement('p');
    p.style.color = 'red';
    p.innerHTML = 'INVALID LOGIN!';

    loginForm.appendChild(p);
};

function checkIfUserCurrentlyLoggedIn(event) {
    fetch('http://localhost:7000/currentuser', {
    'credentials': 'include',
    'method': 'GET'
    }).then((response) =>{
        if (response.status == 200) {
            window.location.href = '/viewreimbursements.html';
        } 
    });
}

loginButton.addEventListener('click', login);
window.addEventListener('load', checkIfUserCurrentlyLoggedIn);

/* let loginButton = document.getElementById('login');
let usernameInput = document.getElementById('username');
let passwordInput = document.getElementById('password');

function login(event) {
    event.preventDefault();

    const loginInfo = {
        'username': usernameInput.nodeValue,
        'password': passwordInput.value
    };

    fetch('http://localhost:7000/login', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies,
        // you should include them in future requests.
        headers: {
            'Content-Type': 'application/json' // application/json is a MIME type
        },
        body: JSON.stringify(loginInfo)
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/viewreimbursements.html';
        } else if (response.status === 400) {
            displayInvalidLogin();
        }
    })
};

function displayInvalidLogin() {
    let loginForm = document.getElementById('loginform');

    let p = document.createElement('p');
    p.style.color = 'red';
    p.innerHTML = 'INVALID LOGIN!';

    loginForm.appendChild(p);
};

function checkIfUserCurrentlyLoggedIn(event) {
    fetch('http://localhost:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/viewreimbursements.html';
        }
    });
}

loginButton.addEventListener('click', login);
window.addEventListener('load', checkIfUserCurrentlyLoggedIn)
*/