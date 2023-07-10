document.addEventListener("DOMContentLoaded", () => {
    let userId;

    const userIdInput = document.getElementById("userIdTest");

    userIdInput.addEventListener("input", (event) => {
        userId = event.target.value;
    });

    function fetchUser(id) {
        const url = `/people/${id}`;

        fetch(url)
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Error occurred while fetching user.");
                }
            })
            .then((user) => {
                fillUserInfo(user);
                setupDeleteButton(user.nickname);
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    }

    function fillUserInfo(user) {
        const nameElement = document.querySelector("h1");
        const linksElement = document.querySelector(".links");
        const ageElement = document.querySelector(".age p");
        const locationElement = document.querySelector(".location p");
        const telephoneElement = document.querySelector(".telephone p");
        const emailElement = document.querySelector(".email p");
        const aboutContentElement = document.querySelector(".about p");
        const skillsElement = document.querySelector(".skills");

        nameElement.textContent = `${user.firstName} ${user.lastName}`;

        linksElement.innerHTML = "";

        user.socialMedias.forEach((socialMedia) => {
            const link = document.createElement("a");
            link.href = socialMedia.url;
            link.textContent = socialMedia.name;
            linksElement.appendChild(link);
        });

        ageElement.textContent = user.yearOfBirth;
        locationElement.textContent = user.countryResidence;
        telephoneElement.textContent = user.telephone;
        emailElement.textContent = user.email;
        aboutContentElement.textContent = user.about;

        skillsElement.innerHTML = "";

        user.technologies.forEach((technology) => {
            const skill = document.createElement("p");
            skill.textContent = technology.name;
            skillsElement.appendChild(skill);
        });
    }

    function setupDeleteButton(username) {
        const deleteButton = document.querySelector(".delete-btn");

        deleteButton.addEventListener("click", () => {
            deleteUser(username);
        });
    }

    function deleteUser(username) { // need to declare if we work with id or username
        const url = `/delete/${username}`; // need to declare the endpoint

        fetch(url, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((response) => {
                if (response.ok) {
                    console.log("User deleted successfully.");
                } else {
                    throw new Error("Error occurred while deleting user.");
                }
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    }

    fetchUser(userId);
});