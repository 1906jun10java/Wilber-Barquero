window.onload = function() {
    welcomeUser();
    employeeInfo();
}

function welcomeUser() {
	fetch("http://localhost:8082/p1/session").then(
			function(resp) {
				let JSONdata = resp.json();
				return JSONdata;
			}).then(
			function(JSONdata) {
				document.getElementById("welcomeEmp").innerText = "Welcome "+ JSONdata.firstName + " " + JSONdata.lastName;
				return JSONdata;
			})
}

function employeeInfo(){
	fetch("http://localhost:8082/p1/session").then(
			function(resp) {
				let JSONdata = resp.json();
				return JSONdata;
			}).then(
			function(JSONdata) {
				document.getElementById("firstname").innerText ="First Name: "+JSONdata.firstName;
				document.getElementById("lastname").innerText = "Last Name: "+JSONdata.lastName;
				document.getElementById("email").innerText = "Email/Username: "+JSONdata.email;
				document.getElementById("department").innerText = "Department: "+JSONdata.department;
				return JSONdata;
			})
			
function managerInfo(){
	fetch("http://localhost:8082/p1/session").then(
			function(resp) {
				let JSONdata = resp.json();
				return JSONdata;
			}).then(
			function(JSONdata) {
				document.getElementById("firstname").innerText ="First Name: "+JSONdata.firstName;
				document.getElementById("lastname").innerText = "Last Name: "+JSONdata.lastName;
				document.getElementById("email").innerText = "Email/Username: "+JSONdata.email;
				document.getElementById("department").innerText = "Department: "+JSONdata.department;
				return JSONdata;
			})
	}
}