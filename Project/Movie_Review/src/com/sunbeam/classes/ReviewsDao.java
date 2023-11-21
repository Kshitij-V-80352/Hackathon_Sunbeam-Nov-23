package com.sunbeam.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao extends SuperDao {
	private PreparedStatement createReview;
	private PreparedStatement editReview;
	private PreparedStatement deleteReview;
	private PreparedStatement displayAllReviews;
	private PreparedStatement displayMyReviews;
	private PreparedStatement displaySharedReview;
	private PreparedStatement shareReview;

	public ReviewsDao() {
		super();
		try {
			createReview = con.prepareStatement(
					"INSERT into reviews(movie_id,review,rating,user_id,modified)VALUES(?,?,?,?,CURRENT_TIMESTAMP());");
			editReview = con.prepareStatement(
					"UPDATE reviews set review=?,modified=CURRENT_TIMESTAMP(),rating=? where user_id=? AND review_id = ?;");
			deleteReview = con.prepareStatement("DELETE FROM reviews WHERE review_id = ?");
			displayAllReviews = con.prepareStatement("SELECT * FROM reviews;");
			displayMyReviews = con.prepareStatement("SELECT * FROM reviews WHERE user_id = ?");
			displaySharedReview = con.prepareStatement(
					"SELECT * FROM shares,reviews where shares.review_id = reviews.review_id and shares.user_id = ?");
			shareReview = con.prepareStatement("INSERT INTO shares VALUES(default, ?, ?)");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		super.close();
		if (createReview != null)
			createReview.close();
		if (editReview != null)
			editReview.close();
		if (deleteReview != null)
			deleteReview.close();
		if (displayAllReviews != null)
			displayAllReviews.close();
		if (displayMyReviews != null)
			displayMyReviews.close();
		if (displaySharedReview != null)
			displaySharedReview.close();
		if (shareReview != null)
			shareReview.close();
	}

	public List<Reviews> displayMyReviews(int userId) {
		try {
			displayMyReviews.setInt(1, userId);
			List<Reviews> reviewList = new ArrayList<Reviews>();
			try (ResultSet rs = displayMyReviews.executeQuery()) {
				while (rs.next()) {
					Reviews review = new Reviews();
					review.setUserId(rs.getInt("user_id"));
					review.setReview(rs.getString("review"));
					review.setRating(rs.getInt("rating"));
					review.setMovieId(rs.getInt("movie_id"));
					review.setReviewId(rs.getInt("review_id"));
					review.setModified(rs.getTimestamp("modified"));
					reviewList.add(review);

				}
				return reviewList;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<Reviews> displayAllReviews() {
		List<Reviews> list = new ArrayList<Reviews>();
		ResultSet rs = null;
		try {
			rs = displayAllReviews.executeQuery();
			while (rs.next()) {
				int review_id = rs.getInt("review_id");
				int movie_id = rs.getInt("movie_id");
				String review = rs.getString("review");
				int rating = rs.getInt("rating");
				int user_id = rs.getInt("user_id");
				java.util.Date modified = rs.getTimestamp("modified");
				Reviews all = new Reviews(review_id, movie_id, review, rating, user_id, modified);
				list.add(all);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public int deleteReview(int review_id) {
		try {
			deleteReview.setInt(1, review_id);
			int count = deleteReview.executeUpdate();
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public int createReview(Users u, Movies m, String review, int rating) {
		int count = 0;
		try {
			createReview.setInt(1, m.getMovieId());
			createReview.setString(2, review);
			createReview.setInt(3, rating);
			createReview.setInt(4, u.getUserId());
			count = createReview.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
	}

	public List<Reviews> displaySharedReview(int id) {
		List<Reviews> list = new ArrayList<>();
		try {
			displaySharedReview.setInt(1, id);
			try (ResultSet rs = displaySharedReview.executeQuery()) {
				while (rs.next()) {

					int review_id = rs.getInt("review_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int movie_id = rs.getInt("movie_id");

					java.sql.Timestamp modified = rs.getTimestamp("modified");

					Reviews r = new Reviews(review_id, movie_id, review, rating, id, modified);
					list.add(r);
				}
				return list;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public int shareReview(int review_id, int user_id) {
		try {
			shareReview.setInt(1, review_id);
			shareReview.setInt(2, user_id);

			int count = shareReview.executeUpdate();
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	public int editReview(int user_id, String review, int rating, int review_id) {
		int count = 0;
		try {
			editReview.setInt(2, rating);
			editReview.setString(1, review);
			editReview.setInt(3, user_id);
			editReview.setInt(4, review_id);
			count = editReview.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}
}
