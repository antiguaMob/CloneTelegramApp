
//************************ Мобильный разработчик ***************************

/*Как создать клон Telegram. Пишем мессенджер для Android на Kotlin. XML */



// Lib ->  Mike Penz, Material Drawer: https://github.com/mikepenz/MaterialDrawer
// Lib -> CircleImageView : https://github.com/hdodenhof/CircleImageView
// Lib -> Android-Image-Cropper: https://github.com/CanHub/Android-Image-Cropper
                      Old->      https://github.com/ArthurHub/Android-Image-Cropper
// Lib -> Picasso: https://github.com/square/picasso
// Lib -> FirebaseUI-Android  https://github.com/firebase/FirebaseUI-Android
//**********************************************************************************************
 
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

//Урок 17. Добавим возможность изменять username пользователя, а так же проверять перед изменением 
//         его уникальность в базе данных, а так же удалять старый username из базы данных.
//https://www.youtube.com/watch?v=9el-TPcj1zE&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=50

//Урок 18. Поиск багов, устранение критической ошибки в Debug.
// https://www.youtube.com/watch?v=Db1tGrpVmV0&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=49

//Урок 19. Произведем рефакторинг кода. Добавим возможность изменение информацию о пользователе.
//https://www.youtube.com/watch?v=JHpjv4mvlC8&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=48

//Урок 20. Добавим возможность изменение фото пользователя. Научимся работать с библиотекой
//         Android-Image-Cropper и с Firebase Storage. Произведем отправку выбранной фотографии
//		   удаленное хранилище. Добавим автоматическое скрытие клавиатуры при смене фрагментов. 
//https://www.youtube.com/watch?v=IUuApvej4k0&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=47

//Урок 21. Получим из Firebase Storage адрес нашей картинки (URL) и сохраним его в базу данных
//         Realtime Database.
//https://www.youtube.com/watch?v=ylrdJ49mFwI&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=47

//Урок 22. Научимся загружать и устанавливать фото пользователя. Научимся работать с библиотекой 
//         Picasso. Создадим для нее функцию расширения. Исправим критический баг. 
//https://www.youtube.com/watch?v=5k6xI6ndAi4&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=45

//Урок 23. Произведем рефакторинг кода, создадим функции высшего порядка, научимся использовать
//         живые шаблоны.
//https://www.youtube.com/watch?v=wXyq3yA4Ajs&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=44

//Урок 24. Обновим данные в боковом меню при изменении этих данных  в настройках.
//https://www.youtube.com/watch?v=smUy99sFZLo&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=43

//Урок 25. Научимся устанавливать статус пользователя: "в сети", "был недавно", "печатает". 
//         Научимся работать с ENUM. А так же записывать статус в базу данных Firebase.
//https://www.youtube.com/watch?v=9vXj70MXzB8&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=42

//Урок 26. Работа с GitHub и системой контроля версий. Клонирование проекта, commit, push.
//https://www.youtube.com/watch?v=IoQ0tmjCWjw&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=41

//Урок 27. Обсудим так называемый  Task management, учимся ставить задачи и решать их.
//         Определим дельнейшие задачи которые нам необходимо решить.
//https://www.youtube.com/watch?v=xpt-fVUGlmQ&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=40

//Урок 28. Научимся запрашивать разрешение у пользователя на считывание контактов. 
//https://www.youtube.com/watch?v=sluGqAVkWCc&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=39

//Урок 29. Разберем, что такое КОРУТИНЫ (COROUTINE) в Kotlin и для чего они нужны. 
//         Научимся запускать новые корутины. 
//https://www.youtube.com/watch?v=38UGe9LJgr0&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=38

//Урок 30. Создадим общую модель CommonModel. Научимся считывать телефонную книгу и сохранять 
//         данные в массив. 
//https://www.youtube.com/watch?v=IlyQtIgkLrM&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=37

//Урок 31. Cчитаем номера телефонов, сравним с номерами из телефонной книги и запишим все номера
//         которые совпали в отдельную ноду. 
//https://www.youtube.com/watch?v=INT1O-1l3Sg&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=36

//Урок 32. Cоздадим фрагмент для отображения контактов и отдельный элемент для списка RecycleView. 
//https://www.youtube.com/watch?v=CL49WrJ5OKI&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=36

//Урок 33. Заполним RecyclerView  для отображения контактов. Получим данные из Firebase.
//https://www.youtube.com/watch?v=CE-T1W1OPzY&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=37

//Урок 34. Что такое утечка памяти? Научимся работать с Android Profile, найдем и устраним утечку
//         памяти в нашем приложении. 
//https://tproger.ru/translations/android-memory-leak/
//https://www.youtube.com/watch?v=58HNvOGx16Q&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=34

 //Урок 35. Начнем разрабатывать макет для создания одиночного чата. Создадим специальный тулбар
//          для отображения информации о пользователе. 
//https://www.youtube.com/watch?v=SQrDjdNmSoY&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=32

//Урок 36. Доделаем макет для создания одиночного чата. 
//https://www.youtube.com/watch?v=7StlARxMLGo&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=31

//Урок 37. Обновим тулбар в одиночном чате. Подключим слушателя, который будет слушать изменения
//         в базе данных и обновлять данные тулбаре. 
//https://www.youtube.com/watch?v=5UGzsF8k-SI&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=30

//Урок 38. Устраним баги в приложении которые нашли подписчики данного курса.
//https://www.youtube.com/watch?v=HdLk1EVtnGI&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=29

//Урок 39. Реализуем отправку сообщений в Fireabase.
//https://www.youtube.com/watch?v=ix0JGwEt-3Y&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=28

//Урок 40. Получение сообщений из Firebase и отображение их в RecyclerView одиночного чата.
//https://www.youtube.com/watch?v=Zqv6qEEgGSg&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=27

//Урок 41. Реализуем архитектуру одного активити. Приведем в порядок код.
//https://www.youtube.com/watch?v=DDDqR9a50yA&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=27

//Урок 42. Рассмотрим очень важную тему - Оптимизация работы RecyclerView. Научимся использовать 
//         утилиту DiffUtil.
//https://www.youtube.com/watch?v=2Zb0Hgo8WgY&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=25

//Урок 43. Организуем плавную дозагрузку данных из Firebase. 
//https://www.youtube.com/watch?v=oF3TrSeGdSU&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=24

//Урок 44. Упростим код и  исправим баг, при котором нельзя было подгрузить данные если 
//         отсутствовала прокрутка списка c помощью SwipeRefreshLayout.
//https://www.youtube.com/watch?v=ccewOjtMX04&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=22

//Урок 45. Продолжим заниматься оптимизацией RecyclerView. Исправим ошибку дублирования сообщений.
//         Сделаем небольшой рефакторинг. Разберём как работает RecyclerView, создадим LayoutManger. 
//         Сократим количество запросов в базу данных.
//https://www.youtube.com/watch?v=t2AVOmARpS4&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=21

//Урок 46. Реализуем отправку картинок в облачное хранилище и в базу данных.
//https://www.youtube.com/watch?v=YVtf-RnOVkU&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=20

//Урок 47. Реализуем отображение картинок в чате.
//https://www.youtube.com/watch?v=35qeRid90Jk&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=19

//Урок 47. Начнем реализовывать голосовые сообщения. 
//https://www.youtube.com/watch?v=Q_xOgenE4-M&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=18

//Урок 48. Начнем реализовывать голосовые сообщения.
//https://www.youtube.com/watch?v=Q_xOgenE4-M&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=19

//Урок 49.  Продолжаем работать над  голосовыми сообщениями.
//https://www.youtube.com/watch?v=vctiXmIAUIU&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=18


//Урок 50.  Продолжаем работать над  голосовыми сообщениями.
//https://www.youtube.com/watch?v=KAMhwNhdyLk&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=16

//Урок 51. Разделим наше общий кастомный макет на отдельныe макеты для каждого типа сообщения.
//         Так же создадим для каждого типа сообщения отдельный Holder.
//https://www.youtube.com/watch?v=l5p3qzVCgnM&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=15

//Урок 52.  Реализуем отображение голосовых сообщений.
//https://www.youtube.com/watch?v=htkrU1WcNMU&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=15

//Урок 53. Полностью реализуем прослушивание голосовых сообщений. 
//https://www.youtube.com/watch?v=vMxKde2Adwo&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=13

//Урок 54. Реализуем отправку голосовых сообщений в чат. Сохранение файла в хранилище и 
//         сообщения в базе данных.
//https://www.youtube.com/watch?v=dV7fAl2aH4E&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=12

//Урок 55. Реализуем отображение файловых сообщений в чате. Научимся получать имя файла по URI.
// https://www.youtube.com/watch?v=YocJo-cqFoY&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=11

//Урок 56. начнем реализовывать главный экран приложения. 
//https://www.youtube.com/watch?v=nV4Yt1iNSMw&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=10

//Урок 57. Реализация главного экрана приложения. Часть 2
//https://www.youtube.com/watch?v=Qe0eraR_zTM&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=9

//Урок 58. Обработка кликов по элементам главного листа. Переход в одиночный чат.
//https://www.youtube.com/watch?v=-oh_0RlWWfc&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=8

//Урок 59. Очистка и удаление чата.
//https://www.youtube.com/watch?v=vwyyu1tbRZI&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=7

//Урок 60. Реализация групп. Часть 1
//https://www.youtube.com/watch?v=PCzxapn1GYE&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=6

//Урок 61. Реализация групп. Часть 2
//https://www.youtube.com/watch?v=9NZVUBNGZg0&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=5

//Урок 62. Учимся работать с plurals и quantity.
//https://www.youtube.com/watch?v=nstNmY0Zjy8&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=4

//Урок 63. Реализация групп.
//https://www.youtube.com/watch?v=w6bORaTUco4&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=3

//Урок 64. Добавляем группу в главный лист при создании
//https://www.youtube.com/watch?v=KoJlC3G_RcU&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=2

//Урок 65. Реализация групповых чатов.
//https://www.youtube.com/watch?v=-cVXVX4HMWM&list=PLY8G5DMG6TiOBq7OWFPWF2Um3FRB5s2ke&index=1




