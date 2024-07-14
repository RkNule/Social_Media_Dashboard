// src/App.js
import React from "react";
import "./App.css";
import Dashboard from "./Dashboard";
import Login from "./Login";
import Register from "./Register";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Social Media App</h1>
        <Dashboard />
        <Login />
        <Register />
      </header>
    </div>
  );
}

export default App;
