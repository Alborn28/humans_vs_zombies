import { io } from "socket.io-client";

const URL = "https://hvz-experis-chatserver.herokuapp.com/";
const socket = io(URL, { autoConnect: false });

export default socket;