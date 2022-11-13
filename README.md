
//************************ Мобильный разработчик ***************************

/*Как создать клон Telegram. Пишем мессенджер для Android на Kotlin. XML */



// Lib ->  Mike Penz, Material Drawer: https://github.com/mikepenz/MaterialDrawer
// Lib -> CircleImageView : https://github.com/hdodenhof/CircleImageView
 
// Урок 1. Подготовка проекта.  В этом уроке подключим все необходимые зависимости нашего проекта. 
// https://www.youtube.com/watch?v=iO8FMBWKO3Y&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=67

// Урок 2. Создаем боковое выдвижное меню. Научимся работать с библиотекой Material Drawer. Все ссылки ниже.
// https://www.youtube.com/watch?v=9iTqSfAV-8c&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=65

// Урок 3. В этом уроке заполним боковое выдвижное меню иконками. Иконки можно скачать ниже по ссылке. 
// https://www.youtube.com/watch?v=diFIhmv-C80&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=64

// Урок 4. Добавляем  фрагменты для окна Чатов и Настроек.
// https://www.youtube.com/watch?v=DZkU4yhBwVI&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=63

// Урок 5. Проведем рефакторинг кода. Вынесем все управления Material Navigation Drawer в отдельный 
//         класс AppDrawer. Создадим общий базовый фрагмент для всех фрагментов приложения, 
//         используем наследование, зададим общее поведение всех фрагментов. 
// https://www.youtube.com/watch?v=YlsovHtpY_c&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=62

// Урок 6. Заполним элементами view фрагмент настроек. Научимся работать с редактором макетов
//         Android Studio и ConstraintLayout.
//https://www.youtube.com/watch?v=B_GznW5F9po&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=63

// Урок 7. Добавим Options menu с иконками, закончим с фрагментом настроек, добавим выпадающее
//         меню с иконками. 
// https://www.youtube.com/watch?v=LjEn7JciNJo&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=61

//Урок 8. Создаем окно регистрации по номеру телефона. Два фрагмента и одно actvity для
//        регистрации и авторизации пользователя. 
// https://www.youtube.com/watch?v=x_kVlYdmk9Q&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=60

//Урок 9. Рефакторинг кода. Научимся улучшать классы из стандартной библиотеки, принимать лямбды
//        и возвращать результат.
// https://www.youtube.com/watch?v=kaXyxHY9pWk&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=58

//Урок 10. Подключение к сервису Firebase. 
// https://www.youtube.com/watch?v=bx5MLV4Wyn8&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=57

//Урок 11. В этом видео мы сделаем возможность создавать и авторизовать нового пользователя
//         по номеру  телефона в Firebase. Научимся работать с PhoneProviderFirebase, отправлять 
//         смс для авторизации аккаунта в Firebase. 
// https://www.youtube.com/watch?v=uhEpXD39oVA&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=56

//Урок 12. Начнем работать с базой данных Firebase. Добавим все необходимые зависимости для работы
//         с Realtime Database. Создадим первые ноды при создании пользователя.
// https://www.youtube.com/watch?v=5Gir79boRbM&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=55

//Урок 13. Добавим поведение выдвижного меню как в оригинальном телеграмме. Блокируем выдвижное 
//         меню, переопределяем поведение гамбургера и кнопки назад.
// https://www.youtube.com/watch?v=AqnVLDTjc94&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=54

//Урок 14. Добавим возможность изменять имя и фамилию пользователя, а так же добавим модель User.
//         Запишем изменённые данные в удаленную базу данных Firebase.
//https://www.youtube.com/watch?v=RiwwfW77TWw&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=53

//Урок 15. Произведем первичную инициализацию модели User. Сделаем при запуске приложения 
//         обновления модели из базы данных Firebase. Заполним значения из базы данных поля
//         для изменения имени и фамилии. 
//https://www.youtube.com/watch?v=IATL2G3FvuA&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=52

//Урок 16. Проинициализируем все поля фрагмента настроек данными из модели User. А так же, я покажу
//         как использовать временные атрибуты в макете, что бы их не было видно в приложении. 
//https://www.youtube.com/watch?v=o6kOEWWF2bk&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=51










