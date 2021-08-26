let submitButton = document.getElementById('submit');
let amountInput = document.getElementById('amount');
let typeInput = document.getElementById('type');
let descriptionInput = document.getElementById('description');


function submitForm(event) {
    event.preventDefault();

    // keys should match SubmissionDTO variable names. 
    const submissionInputs = {
        'reimbAmount': "" + amountInput.value,
        'type': "" + typeInput.value,
        'reimbDescription': "" + descriptionInput.value
    };

    fetch('http://localhost:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 401) {
            window.location.href = '/index.html'
        } 
        else if (response.status === 200) {
            return response.json();
        }
    }).then((user) => {
        return fetch(`http://localhost:7000/user/${user.userId}/reimbursement`, {
            'credentials': 'include',
            'method': 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(submissionInputs)
        });
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/viewreimbursements.html';
        }
        else {
            alert("Error - Unable to add reimbursement record");
        }
    })
};

submitButton.addEventListener('click', submitForm);


/*
const data = {
    'reimbAmount': amountInput.nodeValue,
    'reimbType': typeInput.nodeValue,
    'reimbDescription': descriptionInput.nodeValue
};

const options = {
    method: 'POST',
    headers: {
        'Context-Type': 'application/json'
    },
    body: JSON.stringify(data)
};

fetch('http://localhost:7000/user/${user.userId}/reimbursement', options);
*/


