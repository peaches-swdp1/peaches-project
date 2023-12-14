import React, { useEffect, useState } from "react";
import fetchRecommendations from "./fetchRecommendations";
import RecommendationListItem from "./RecommendationListItem";

export default function RecommendaionsList() {
  const [categories, setCategories] = useState([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState("any");
  const [recommendations, setRecommendations] = useState([]);
  const [searchKeyword, setSearchKeyword] = useState("");
  const [originalRecommendations, setOriginalRecommendations] = useState([]);

  useEffect(() => {
    fetch('/api/categories')
        .then(response => {
            if (!response.ok)
                throw new Error("Something went wrong: " + response.statusText);

            return response.json();
        })
        .then(data => setCategories(data))
        .catch(err => console.error(err))
  }, []);

  useEffect(() => {
	  console.log("Selected Category ID:", selectedCategoryId);
    if (selectedCategoryId !== "any") {
       fetch(`/api/categories/${selectedCategoryId}/recommendations`)
    	.then((response) => response.json())
        .then(data => {
			console.log(data)
			setRecommendations(data)
			setOriginalRecommendations(data)})
        .catch(err => console.error(err)) 
    } else {
      fetchRecommendations()
      .then(data => {
		  setRecommendations(data)
		  setOriginalRecommendations(data)})
      .catch(error => console.error("Error fetching trainings:", error))
    }
  }, [selectedCategoryId]); // Re-run the effect when selectedCategoryId changes

  useEffect(() => {
    const keywordLowerCase = searchKeyword.toLowerCase();
    const filtered = originalRecommendations.filter(
      (recommendation) =>
        recommendation.title.toLowerCase().includes(keywordLowerCase) ||
        recommendation.description.toLowerCase().includes(keywordLowerCase)
    );
    setRecommendations(filtered);
  }, [originalRecommendations, searchKeyword]);

  function handleCategoryFilterChange(event) {
    setSelectedCategoryId(event.target.value);
  }
  
  
  function handleSearchInputChange(event) {
    setSearchKeyword(event.target.value);
  }

  return (
    <div>
      <div className="mb-3">
        <label className="form-label">Filter by a category</label>
        <select
          className="form-select"
          onChange={handleCategoryFilterChange}
          value={selectedCategoryId}
        >
          <option value="any">Any category</option>
          {categories.map((category) => (
            <option value={category.categoryId} key={category.categoryId}>
              {category.name}
            </option>
          ))}
        </select>
      </div>
      <div class="mb-3">
        <label class="form-label">Search by Title or Description</label>
        <input
          type="text"
          class="form-control"
          placeholder="Enter keyword"
          value={searchKeyword}
          onChange={handleSearchInputChange}
        />
      </div>
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
          {recommendations.map((recommendations) => (
            <RecommendationListItem
              recommendation={recommendations}
              key={recommendations.id}
              onDelete={(deletedId) => {
                setRecommendations((prevRecommendations) => 
                  prevRecommendations.filter((rec) => rec.id!== deletedId));
              }
              }
            />
          ))}
        </tbody>
      </table>

      <a className="btn btn-primary" href="/recommendations/add">
        Add a Recommendation
      </a>
    </div>
    </div>
  );
}