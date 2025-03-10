document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("contact-form").addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent default form submission

        let form = this;
        let formData = new FormData(form);
        let loadingDiv = document.querySelector(".loading");
        let errorDiv = document.querySelector(".error-message");
        let successDiv = document.querySelector(".sent-message");

        // Show loading text
        loadingDiv.style.display = "block";
        errorDiv.style.display = "none";
        successDiv.style.display = "none";

        fetch(form.action, {
            method: "POST",
            body: formData
        })
        .then(response => response.json()) // Expecting JSON from contact.php
        .then(data => {
            loadingDiv.style.display = "none"; // Hide loading

            if (data.status === "success") {
                successDiv.style.display = "block"; // Show success message
                form.reset(); // Clear form fields
            } else {
                errorDiv.style.display = "block"; // Show error message
                errorDiv.innerHTML = data.message;
            }
        })
        .catch(error => {
            loadingDiv.style.display = "none"; // Hide loading
            errorDiv.style.display = "block"; // Show error
            errorDiv.innerHTML = "Network error: " + error;
        });
    });
});