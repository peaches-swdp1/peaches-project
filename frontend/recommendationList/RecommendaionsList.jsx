import React, { useEffect, useState } from "react";
import fetchRecommendations from "./fetchRecommendations";
import RecommendationListItem from "./RecommendationListItem";

export default function RecommendaionsList() {
  const [readingRecommendaions, setReadingRecommendaions] = useState([]);

 useEffect(() => {
      fetchRecommendations()
      .then(data => setReadingRecommendaions(data))
      .catch(error => console.error("Error fetching trainings:", error))
    }, [])

  return (
    <div>
     <h1>Reading Recommendations</h1>

      <table className="table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Link</th>
            <th>Description</th>
            <th>Category</th>
            <th>Added on</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {readingRecommendaions.map((readingRecommendaion) => (
            <RecommendationListItem
              recommendation={readingRecommendaion}
              key={readingRecommendaion.id}
            />
          ))}
        </tbody>
      </table>

      <a className="btn btn-primary" href="/recommendations/add">
        Add a Recommendation
      </a>
    </div>
  );
}
