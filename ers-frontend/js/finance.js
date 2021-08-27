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

        if (user.userRole.userRoleId != 2) {
            window.location.href = '/unauthorized.html'
        };
        return fetch('http://localhost:7000/reimbursement', {
            'method': 'GET', 
            'credentials': 'include'
        });
    }).then((response) => {
        return response.json()
    }).then((reimbursements) => {
        populateReimbursements(reimbursements)
    })
};


function populateReimbursements(reimbArray) {
    let tbody = document.querySelector('#reimbursements tbody');

    for (const reimbursement of reimbArray) {

        let tr = document.createElement('tr');



        let reimbIdTd = document.createElement('td');
        reimbIdTd.innerHTML = reimbursement.reimbId;

        let reimbFirstNameTd = document.createElement('td');
        reimbFirstNameTd.innerHTML = reimbursement.author.firstName;

        let reimbLastNameTd = document.createElement('td');
        reimbLastNameTd.innerHTML = reimbursement.author.lastName;

        let reimbDescriptionTd = document.createElement('td');
        reimbDescriptionTd.innerHTML = reimbursement.description;

        let reimbAmountTd = document.createElement('td');
        reimbAmountTd.innerHTML = (reimbursement.reimbAmount).toLocaleString('en-US', {
            style: 'currency',
            currency: 'USD',
          });

        let timeSubmittedTd = document.createElement('td');
        timeSubmittedTd.innerHTML = new Date(reimbursement.timeSubmitted).toISOString().slice(0, 10);

        let timeResolvedTd = document.createElement('td');
        timeResolvedTd.innerHTML = reimbursement.timeResolved;

        let reimbStatusTd = document.createElement('td');
        reimbStatusTd.innerHTML = reimbursement.status.status;

        let reimbursementTypeTd = document.createElement('td');
        reimbursementTypeTd.innerHTML = reimbursement.type.type;

        let approveButton = document.createElement("BUTTON");
        approveButton.innerHTML = "Approve";

        let denyButton = document.createElement("BUTTON");
        denyButton.innerHTML = "Deny";


        tr.appendChild(reimbIdTd);
        tr.appendChild(reimbFirstNameTd);
        tr.appendChild(reimbLastNameTd);
        tr.appendChild(reimbDescriptionTd);
        tr.appendChild(reimbAmountTd);
        tr.appendChild(timeSubmittedTd);
        tr.appendChild(timeResolvedTd);
        tr.appendChild(reimbStatusTd);
        tr.appendChild(reimbursementTypeTd);
        tr.appendChild(approveButton);
        tr.appendChild(denyButton);

        approveButton.setAttribute('id', (String(reimbursement.reimbId) + "approve"));
        denyButton.setAttribute('id', (String(reimbursement.reimbId) + "deny"));
        
        
        tbody.appendChild(tr);


    }
}

window.addEventListener('load', onLoad);
