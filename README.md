ИНСТРУКЦИЯ ПО СБОРКЕ ПРОЕКТА
Склонируйте репозиторий на локальный компьютер. Проект собирается через Gradle, в Build/dependency должны быть указаны зависимости: 
1. implementation group: 'commons-net', name: 'commons-net', version: '3.8.0'
2. testImplementation group: 'org.testng', name: 'testng', version: '7.6.1'
3. testImplementation group: 'org.slf4j', name: 'slf4j-simple', version: '1.7.36'
В test{} должно быть указано useTestNG()

ИНСТРУКЦИЯ ПО РАБОТЕ С ПРИЛОЖЕНИЕМ
1. Соберите проект
2. Добавьте в проект файл формата json. В нем будут локально сохраняться файлы из файла сервера.
2. Запустите Client/Main()
3. Следуйте инструкциям на экране. Все возможные действия будут перечислятся (к примеру, вам предложат на выбор несколько команд: вводите команду именно так, как она указана; все доступные команды указаны на экране
Дополнительно:
1. Для работы с клиентом вы должны знать адрес сервера FTP, учетную запись, у которой есть доступ к нему, содержимое сервера.
2. Клиент обрабатывает один файл формата json.

ИНСТРУКЦИЯ ПО ТЕСТИРОВАНИЮ
1. Отредактируйте класс DP.java: в нем находятся методы, вовзращающие значения для тестирования; укажите актуальные для вас значения (например, корректный IP адрес сервера)
2. Запустите файл testing.xml. Он запускает сразу все тесты.

ОБОСНОВАНИЕ ТЕСТОВ
Внутри заложены следующие Unit-тесты:
1. FTPTest.java - тестирует всю функциональность сервера (соединение, авторизация, скачивание файла, загрузка файла)
2. JsonParserTest.java - тестирует запись данных в файл
3. DP.java - хранит данные для тестирования
Для классов Student, Root нет тестов, так как они только хранят данные из файла. Класс Client не тестируется, так как он использует классы, которые уже были обработаны тестами.
