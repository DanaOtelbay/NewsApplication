# News Application

## Information

#### The application is written in Java.
#### The application contain the following methods:
- [x] GET, POST, PUT, DELETE methods for news sources;
- [x] GET, POST, PUT, DELETE methods for news;
- [x] GET, POST, PUT, DELETE methods for news topics;
- [x] GET method for getting list of all news sources;
- [x] GET method for getting list of all news topics;
- [x] GET method for getting list of all news (with pagination);
- [x] GET method for getting list of news by source id (with pagination);
- [x] GET method for getting list of news by topic id (with pagination);
#### Security with API token
- [x] The application contain a security mechanism via the client token API.
- [x] The user receive a token during authentication.
#### Docker Compose File
- [x] Create docker-compose.yml file that run the application with the database in the Docker container
- [x] Write instructions for creating a Docker image.

---
# Getting Started
##### These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See running for notes on how to run the project on a system.

### Prerequisites
- 1.Clone the project to your local environment:
```aidl
git clone https://github.com/DanaOtelbay/NewsApplication.git
```
- 2.Running
- Update your DD_API_KEY in docker-compose.yml
- Run all containers with docker-compose up
```aidl
docker-compose up
```

---

### GET, POST, PUT, DELETE methods for news sources;
##### GET
![GET](img/getNewsSourceById.png)
##### POST
![POST](img/createnewsSource.png)
##### DELETE
![DELETE](img/deleteNewsSource.png)
##### PUT
![DB](img/updateNewsSorce.png)

---

### GET, POST, PUT, DELETE methods for news;
##### GET
![GET](img/getnewsById.png)
##### POST
![POST](img/CreateNews.png)
##### DELETE
![DELETE](img/Deletenews.png)
##### PUT
![DB](img/updateNews.png)

---

### GET, POST, PUT, DELETE methods for news topics;
##### GET
![GET](img/getNewsSourceById.png)
##### POST
![POST](img/CreateNewsTopics.png)
##### DELETE
![DELETE](img/deleteNewsTopics.png)
##### PUT
![DB](img/UpdatenewsTopics.png)

---

### GET method for getting list of all news sources;
![GET](img/getAllNewsSource.png)

---

### GET method for getting list of all news topics;
![GET](img/getNewsTopic.png)

---

### GET method for getting list of all news (with pagination);
![GET](img/getallNewsWirhPagination.png)

---

### GET method for getting list of news by source id (with pagination);
![GET](img/GetNewsBySourceId.png)

---

### GET method for getting list of news by topic id (with pagination);
![GET](img/getNewsByTopic.png)

---

### Security with API token

#### Register
![REG](img/Register.png)

#### Authentication
![AUTH](img/auth.png)
