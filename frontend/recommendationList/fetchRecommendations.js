export default function fetchRecommendations() {
    return fetch("http://localhost:8080/api/recommendations")
    .then((response) => response.json());
  }
  
  
  
