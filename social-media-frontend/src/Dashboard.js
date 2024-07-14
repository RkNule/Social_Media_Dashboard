import React, { useEffect, useState } from "react";

function Dashboard() {
  const [posts, setPosts] = useState([]); // State for storing posts
  const [error, setError] = useState(null); // State for handling errors
  const [newPostContent, setNewPostContent] = useState(""); // State for the new post content
  const [userId] = useState(1); // Hardcoded user ID, adjust as needed

  // Function to fetch posts
  const fetchPosts = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/posts/user/${userId}`
      );
      if (!response.ok) {
        throw new Error("Network response was not ok.");
      }
      const data = await response.json();

      // Log the data to debug
      console.log("Fetched posts:", data);

      // Ensure data is an array
      if (Array.isArray(data)) {
        setPosts(data);
      } else {
        console.error("Expected an array, but got:", data);
        setError("Unexpected data format");
      }
    } catch (error) {
      console.error("Error fetching posts:", error);
      setError("Error fetching posts");
    }
  };

  // Function to create a new post
  const createPost = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/posts/create/${userId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ content: newPostContent }), // Body should be an object with a content property
        }
      );
      if (!response.ok) {
        throw new Error("Network response was not ok.");
      }
      const post = await response.json();
      console.log("Post created successfully:", post);
      setNewPostContent(""); // Clear the input field
      fetchPosts(); // Refresh the list of posts
    } catch (error) {
      console.error("Error creating post:", error);
      setError("Error creating post");
    }
  };

  // Fetch posts on component mount
  useEffect(() => {
    fetchPosts();
  }, []); // Dependency array is empty to fetch posts only once on mount

  return (
    <div className="dashboard">
      <h2>Dashboard</h2>
      {error && <p>{error}</p>}{" "}
      {/* Display error message if there is an error */}
      <textarea
        value={newPostContent}
        onChange={(e) => setNewPostContent(e.target.value)}
        placeholder="What's on your mind?"
      />
      <button onClick={createPost}>Create Post</button>
      {posts.length === 0 ? (
        <p>No posts available.</p>
      ) : (
        <ul>
          {posts.map((post, index) => (
            <li key={index}>
              {post.content ? post.content : "No content available"}{" "}
              {/* Safeguard against undefined content */}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Dashboard;
