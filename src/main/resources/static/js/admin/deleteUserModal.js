document.addEventListener('DOMContentLoaded', function() {
    const deleteButtons = document.querySelectorAll('.delete-user-btn');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const userId = this.dataset.userId;
            const firstName = this.dataset.userFirstname;
            const lastName = this.dataset.userLastname;
            const age = this.dataset.userAge;
            const email = this.dataset.userEmail;

            // Заполняем поля
            const deleteUserId = document.getElementById('deleteUserId');
            if (deleteUserId) deleteUserId.value = userId;

            const deleteDisplayUserId = document.getElementById('deleteDisplayUserId');
            if (deleteDisplayUserId) deleteDisplayUserId.value = userId;

            const deleteFirstName = document.getElementById('deleteFirstName');
            if (deleteFirstName) deleteFirstName.value = firstName || '';

            const deleteLastName = document.getElementById('deleteLastName');
            if (deleteLastName) deleteLastName.value = lastName || '';

            const deleteAge = document.getElementById('deleteAge');
            if (deleteAge) deleteAge.value = age || '';

            const deleteEmail = document.getElementById('deleteEmail');
            if (deleteEmail) deleteEmail.value = email || '';

            // Обновляем action формы
            const deleteForm = document.getElementById('deleteUserForm');
            if (deleteForm) deleteForm.action = `/admin?id=${userId}`;
        });
    });
});

