window.onload = function() {
	getAllEmployees();
}

function getAllEmployees() {
	//makes a table that displays users information
	fetch("http://localhost:8082/p1/viewEmployees").then(function(response) {
		let JSONdata = response.json();
		return JSONdata;
	}).then(function(JSONdata) {
		employees = JSONdata;
		console.log(employees);
		// Create a HTML Table element.
		let table = document.createElement("TABLE");
		table.border = "1";

		// Add the header row.
		let row = table.insertRow(-1);
		let th = document.createElement("TH");

		th = row.insertCell(-1);
		th.innerHTML = "First Name";

		th = row.insertCell(-1);
		th.innerHTML = "Last Name";

		th = row.insertCell(-1);
		th.innerHTML = "Email";
			
		th = row.insertCell(-1);
		th.innerHTML = "Department";
		
		th = row.insertCell(-1);
		th.innerHTML = "ReportsTo";

		//add data to the rows
			for (let i = 0; i < JSONdata.length; i++) {
				row = table.insertRow(-1);

				let cell = row.insertCell(-1);
				cell.innerHTML = JSONdata[i].firstName;

				cell = row.insertCell(-1);
				cell.innerHTML = JSONdata[i].lastName;
				
				cell = row.insertCell(-1);
				cell.innerHTML = JSONdata[i].email;

				cell = row.insertCell(-1);
				cell.innerHTML = JSONdata[i].department;
				
				cell = row.insertCell(-1);
				cell.innerHTML = JSONdata[i].reportsTo;
			}

		// replace the empty div with a table
		let newTable = document.getElementById("employeesTable");
		newTable.innerHTML = "";
		newTable.appendChild(table);
	})
}