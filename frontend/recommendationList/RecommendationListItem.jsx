import React from 'react';
export default function RecommendationListItem({recommendation}) {
  return (
	   <tr>
      <td>{recommendation.title}</td>
      <td><a href={recommendation.link}>{recommendation.link}</a></td>
      <td>{recommendation.description}</td>
      <td>{recommendation.category?.name}</td>
      <td>{new Date(recommendation.createdOn).toLocaleDateString()}</td>
      <td>
        <a className="btn btn-primary" href={`/recommendations/edit/${recommendation.id}`}>
          Edit
        </a>
      </td>
      <td>
        <form method="POST" action={`/recommendations/${recommendation.id}/delete`}>
		  <button class="btn btn-danger">Delete</button>
		</form>
      </td>
    </tr>
  ); 
}
