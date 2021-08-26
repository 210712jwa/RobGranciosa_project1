/*
If user is not logged in, they will be redirected to the /index.html page. 
*/
function onLoad(event) {
    fetch('http://localhost:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 401) {
            window.location.href = '/index.html'
        } else if (response.status === 200) {
            return response.json();
        }
    }).then((user) => {
        return fetch(`http://localhost:7000/user/${user.userId}/reimbursement`, {
            'method': 'GET', 
            'credentials': 'include'
        });
    }).then((response) => {
        return response.json()
    }).then((reimbursements) => {
        populateReimbursements(reimbursements);

    })
}

// remember that 'reimbursements' is an array. 

function populateReimbursements(reimbArray) {
    let tbody = document.querySelector('#reimbursements tbody');

    for (const reimbursement of reimbArray) {

        let tr = document.createElement('tr');

        let reimbIdTd = document.createElement('td');
        reimbIdTd.innerHTML = reimbursement.reimbId;

        let reimbDescriptionTd = document.createElement('td');
        reimbDescriptionTd.innerHTML = reimbursement.description;

        let reimbAmountTd = document.createElement('td');
        reimbAmountTd.innerHTML = reimbursement.reimbAmount;

        let timeSubmittedTd = document.createElement('td');
        timeSubmittedTd.innerHTML = reimbursement.timeSubmitted;

        let timeResolvedTd = document.createElement('td');
        timeResolvedTd.innerHTML = reimbursement.timeResolved;

        let reimbStatusTd = document.createElement('td');
        reimbStatusTd.innerHTML = reimbursement.status.status;

        let reimbursementTypeTd = document.createElement('td');
        reimbursementTypeTd.innerHTML = reimbursement.type.type;

        tr.appendChild(reimbIdTd);
        tr.appendChild(reimbDescriptionTd);
        tr.appendChild(reimbAmountTd);
        tr.appendChild(timeSubmittedTd);
        tr.appendChild(timeResolvedTd);
        tr.appendChild(reimbStatusTd);
        tr.appendChild(reimbursementTypeTd);

        tbody.appendChild(tr);
    }
}

window.addEventListener('load', onLoad);
