# Humans VS Zombies 
Human vs Zombies is not a video game, it's a tag game that takes place out in the real world and simulates the exponential spread of a fictional zombie infection through a population. When the game starts, one of the participants is randomly selected to start as a zombie. The zombie can then “infect” other participants by tagging them. The goal of the zombies is to infect all humans, and the goal of the humans is to survive until the time runs out.

Our application provides the necessary features and tools that are needed to play the game. It is possible to create a game where you choose the location and when the game takes place. Other players can then join the game. When the game starts, one player is randomly selected as “Patient Zero”, the initial zombie. The other players receive a unique bite code. When a zombie “infects/tags” a human, the zombie enters the human’s bite code into the app, turning the human into a zombie who can infect other humans. Humans can create and join squads with other humans, to coordinate and help each other. There is a large map showing the game area, where players can see where other humans have died as well as the locations of their fellow squad members. There are also 3 different chat windows: Global chat, where all players can chat, faction chat, where humans and zombies can chat separately with their own faction, and squad chat, where squad members can chat with each other. 

## Technologies
Backend was built with Spring Boot and Hibernate with PostgreSQL.
For the front end we used Vue.js and Vuex as we needed state management for our application. For the map we used Leaflet and for automated update we used Socket.IO. 
For the styling of the Application we opted for writing our own CSS.
For authentication we decided to use a stand-alone access management system, instead of implementing our own to save time. We chose Keycloak for authentication.
We used Heroku to deploy our application


## Documentation
API documentation - [Swagger](https://hvz-experis-api.herokuapp.com/swagger-ui.html)
\
User manual: "link"



## Members
Katalina Palma - [@katalinapalma](https://github.com/katalinapalma)
\
Albin Ljungdahl - [@Alborn28](https://github.com/Alborn28)
\
Marcus Thornemo Larsson - [@mumaxs](https://github.com/mumaxs)
\
Aljosa Vuckovic - [@NedsBastard](https://github.com/NedsBastard)
