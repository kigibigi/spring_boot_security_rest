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

            // Роли не трогаем - они уже загружены с сервера
            // Пользователь сам выберет нужную роль

            // Обновляем action формы
            const form = document.getElementById('editUserForm');
            form.action = `/admin?id=${userId}`;
        });
    });
});

// // Временно добавьте тестовые значения, чтобы проверить, работает ли заполнение
// document.addEventListener('DOMContentLoaded', function() {
//     const editButtons = document.querySelectorAll('.edit-user-btn');
//
//     editButtons.forEach(button => {
//         button.addEventListener('click', function() {
//             // Тестовые данные для проверки
//             document.getElementById('editUserId').value = '123';
//             document.getElementById('firstName').value = 'TEST';
//             document.getElementById('lastName').value = 'TEST';
//             document.getElementById('age').value = '25';
//             document.getElementById('email').value = 'test@test.com';
//
//             // Если поля заполнятся тестовыми данными, значит проблема в data-атрибутах
//         });
//     });
// });