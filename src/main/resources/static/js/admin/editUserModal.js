document.addEventListener('DOMContentLoaded', function() {
    const editButtons = document.querySelectorAll('.edit-user-btn');

    editButtons.forEach(button => {
        button.addEventListener('click', function() {
            const userId = this.dataset.userId;
            const firstName = this.dataset.userFirstname;
            const lastName = this.dataset.userLastname;
            const age = this.dataset.userAge;
            const email = this.dataset.userEmail;

            // Заполняем форму
            document.getElementById('editUserId').value = userId;
            document.getElementById('firstName').value = firstName;
            document.getElementById('lastName').value = lastName;
            document.getElementById('age').value = age;
            document.getElementById('email').value = email;
            document.getElementById('password').value = '';

            // Заполняем отображаемое поле ID
            const displayUserId = document.getElementById('displayUserId');
            if (displayUserId) {
                displayUserId.value = userId;
            }

            // Обновляем action формы
            const form = document.getElementById('editUserForm');
            form.action = `/admin?id=${userId}`;
        });
    });
});

