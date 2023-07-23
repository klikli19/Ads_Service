![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Git](https://img.shields.io/badge/git%20-%23F05033.svg?&style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
<h1 align="center">Бэкенд-часть сайта объявлений.</h1>

<h3>Функционал для пользователя:</h3>
<ul>
  
  **Анонимный пользователь** может:
    <li>Просматривать список всех объявлений.</li>
    <li>Зарегистрироваться на сайте.</li>

  **Авторизованный пользователь** может:
    <li>Просматривать список всех объявлений.</li>
    <li>Просматривать каждое объявление.</li>
    <li>Создавать объявление.</li>
    <li>Редактировать и удалять свое объявление.</li>
    <li>Просматривать все комментарии к объявлениям.</li> 
    <li>Создавать комментарии к любым объявлениям.</li>
    <li>Редактировать/удалять свои комментарии.</li>
  
  **Администратор** может:
    <li>Дополнительно к правам пользователя редактировать или удалять объявления и комментарии любых других пользователей.</li>

</ul>


https://github.com/klikli19/GraduationProject/assets/108924340/1f50cf2d-401b-4d0b-a157-8b065e0932b4


**Для запуска нужно:**
1. Клонировать проект в среду разработки</li>
2. Настроить БД и прописать значения в файле **[application.properties](src/main/resources/application.properties)** 
3. Скачать **[Docker](https://www.docker.com)** и запустить его.
4. Скачать Docker образ с помощью команды ```docker pull ghcr.io/bizinmitya/front-react-avito:latest``` 
5. Запустить Docker образ с помощью команды ```docker run -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:latest``` 
6. Запустить метод **main** в файле **[HomeworkApplication.java](src/main/java/ru/skypro/homework/HomeworkApplication.java)** 

После этого вам будет доступен сайт  http://localhost:3000 и Swagger   http://localhost:8080/swagger-ui/index.html#.

<h3>Участники проекта:</h3>
<ul>
<li><a href="https://github.com/MarinaGubina">Губина Марина</a></li>
<li><a href="https://github.com/klikli19">Киликова Анна</a></li>
<li><a href="https://github.com/IlyaB98">Богомолов Илья</a></li>
<li><a href="https://github.com/alexsa-8">Рогозин Александр</a></li>
</ul>
