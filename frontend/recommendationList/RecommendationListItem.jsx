import React from 'react';

export default function RecommendationListItem({ recommendation, onDelete, authenticatedUser, authenticated }) {

	const handleDelete = () => {
		const confirmDelete = window.confirm("Are you sure you want to delete this recommendation?");

		if (confirmDelete) {
			fetch(`/recommendations/${recommendation.id}/delete`, { method: "POST" })
				.then(() => {
					onDelete(recommendation.id);

				})
				.catch((error) => console.error('Error deleting recommendation:', error));
		}
	};


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

					<button className="btn btn-danger" onClick={handleDelete}>Delete</button>

				</td>
			}
		</tr>
	);
}