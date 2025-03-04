import React, { useEffect, useState } from "react";
import RecommendationListItem from "./RecommendationListItem";
import AddRecommendation from './AddRecommendation';

export default function RecommendaionsList() {
	const [authenticatedUser, setAuthenticatedUser] = useState(null);
	const [authenticated, setAuthenticated] = useState(false);
	const [categories, setCategories] = useState([]);
	const [selectedCategoryId, setSelectedCategoryId] = useState("any");
	const [recommendations, setRecommendations] = useState([]);
	const [searchKeyword, setSearchKeyword] = useState("");
	const [originalRecommendations, setOriginalRecommendations] = useState([]);

	const fetchAuthenticatedUsername = async () => {
		try {
			const response = await fetch('/api/users/current');
			if (!response.ok) {
				if (response.status !== 401)
					throw new Error("Something went wrong: " + response.statusText);
				if (authenticated) {
					setAuthenticatedUser(null);
					setAuthenticated(false);
				}
			}
			response.json()
				.then(data => {
					setAuthenticated(true);
					setAuthenticatedUser(data);
				})
				.catch(err => console.error(err));
		} catch (error) {
			console.error(error);
		}
	}

	const fetchCategories = async () => {
		try {
			const response = await fetch('/api/categories');
			if (!response.ok) {
				throw new Error("Something went wrong: " + response.statusText);
			}
			response.json()
				.then(data => setCategories(data))
				.catch(err => console.error(err))
		} catch (error) {
			console.error(error);
		}
	}

	useEffect(() => {
		fetchCategories();
		fetchAuthenticatedUsername();
	}, []);

	const fetchRecommendationsByCategory = async () => {
		try {
			const response = await fetch(`/api/categories/${selectedCategoryId}/recommendations`);
			if (!response.ok) {
				throw new Error("Something went wrong: " + response.statusText);
			}
			response.json()
				.then(data => {
					setRecommendations(data)
					setOriginalRecommendations(data)
				})
				.catch(err => console.error(err));
		} catch (error) {
			console.error(error);
		}
	}

	const fetchAllRecommendations = async () => {
		try {
			const response = await fetch("/api/recommendations");
			if (!response.ok) {
				throw new Error("Something went wrong: " + response.statusText);
			}
			response.json()
				.then(data => {
					setRecommendations(data)
					setOriginalRecommendations(data)
				})
				.catch(error => console.error("Error fetching trainings:", error));
		} catch (error) {
			console.error("Error fetching trainings:", error);
		}
	}

	useEffect(() => {
		if (selectedCategoryId !== "any") {
			fetchRecommendationsByCategory();
		} else {
			fetchAllRecommendations();
		}
	}, [selectedCategoryId]); // Re-run the effect when selectedCategoryId changes

	const filterRecommendations = () => {
		const keywordLowerCase = searchKeyword.toLowerCase();
		const filtered = originalRecommendations.filter(
			(recommendation) =>
				recommendation.title.toLowerCase().includes(keywordLowerCase) ||
				recommendation.description.toLowerCase().includes(keywordLowerCase)
		);
		setRecommendations(filtered);
	}

	useEffect(() => {
		filterRecommendations();
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
							{authenticated &&
								<th>Edit</th>
							}
							{authenticated &&
								<th>Delete</th>
							}
						</tr>
					</thead>
					<tbody>
						{recommendations.map((recommendation) => (
							<RecommendationListItem
								recommendation={recommendation}
								key={recommendations.id}
								onDelete={(deletedId) => {
									setRecommendations((prevRecommendations) =>
										prevRecommendations.filter((rec) => rec.id !== deletedId));
								}}
								authenticatedUser={authenticatedUser}
								authenticated={authenticated}
							/>
						))}
					</tbody>
				</table>

				{authenticated && <AddRecommendation isAuthenticated={authenticated} />}
			</div>
		</div>
	);
}
