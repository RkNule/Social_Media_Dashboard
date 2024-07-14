import React, { useState } from "react";
import axios from "axios";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [user, setUser] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/users/login",
        {
          email,
          password,
        }
      );

      setMessage(response.data.message);
      setUser(response.data.user);
    } catch (error) {
      setMessage("There was an error logging in!");
    }
  };

  return (
    <div className="login">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </label>
        <label>
          Password:
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </label>
        <button type="submit">Login</button>
      </form>
      {message && <p>{message}</p>}
      {user && (
        <div>
          <h3>User Details</h3>
          <p>Email: {user.email}</p>
          <p>Following: {user.following.length}</p>
          <p>Followers: {user.followers.length}</p>
        </div>
      )}
    </div>
  );
}

export default Login;
