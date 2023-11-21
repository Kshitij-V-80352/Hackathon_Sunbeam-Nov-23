package com.sunbeam.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviesDao extends SuperDao {
	private PreparedStatement displayMovies;

	public MoviesDao() {
		super();
		try {
			displayMovies = con.prepareStatement("SELECT * from movies;");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		super.close();
		if (displayMovies != null)
			displayMovies.close();
	}

	public List<Movies> displayAllMovies() {
		List<Movies> list = new ArrayList<Movies>();
		ResultSet rs = null;
		try {
			rs = displayMovies.executeQuery();
			while (rs.next()) {
				int movie_id = rs.getInt("movie_id");
				String title = rs.getString("title");
				java.util.Date release_date = rs.getDate("released");
				Movies movie = new Movies(movie_id, title, release_date);
				list.add(movie);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
}
