import React from 'react';

export default function RecommendationListItem({ recommendation, onDelete, authenticatedUser, authenticated }) {

	const fetchDeleteRecommendation = async (id) => {
		if (!window.confirm("Are you sure you want to delete this recommendation?")) {
			return null;
		}
		try {
			const response = await fetch(`/recommendations/${id}/delete`, {
				method: 'DELETE',
			});
			if (!response.ok) {
				throw new Error("Something went wrong: " + response.statusText);
			}
			onDelete(id);
		} catch (error) {
			console.error(error);
		}
	}


	return (
		<tr>
			<td>{recommendation.title}</td>
			<td><a href={recommendation.link}>{recommendation.link}</a></td>
			<td>{recommendation.description}</td>
			<td>{recommendation.category?.name}</td>
			<td>{new Date(recommendation.createdOn).toLocaleDateString()}</td>
			{authenticated && authenticatedUser.userId === recommendation.appUser.userId &&
				<td>
					<a className="btn btn-primary" href={`/recommendations/edit/${recommendation.id}`}>
						Edit
					</a>
				</td>
			}
			{authenticated && authenticatedUser.userId === recommendation.appUser.userId &&
				<td>

					<button className="btn btn-danger" onClick={() => fetchDeleteRecommendation(recommendation.id)}>Delete</button>

				</td>
			}
		</tr>
	);
}