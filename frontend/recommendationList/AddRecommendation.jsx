export default function AddRecommendation({ isAuthenticated }) {
	if (isAuthenticated) {
		return (
			<a className="btn btn-primary" href="/recommendations/add">
				Add a Recommendation
			</a>
		);
	} else {
		return (
			<></>
		);
	}
}