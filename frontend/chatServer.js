const express = require('express');
const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http, {
    cors: {
      origin: "http://localhost:8080",
    },
  });


io.on("connection", (socket) =>{
    socket.on("chatMessage", (msg) =>{
        io.emit("chatMessage", msg)
    })
});

http.listen(3000, () => console.log("Listening on port 3000!!!"));
