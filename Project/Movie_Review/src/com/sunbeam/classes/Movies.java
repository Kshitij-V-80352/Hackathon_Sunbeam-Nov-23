package com.sunbeam.classes;

import java.util.Date;

public class Movies {
	int movieId;
	String title;
	java.util.Date releaseDate;

	public Movies(int movieId, String title, Date releaseDate) {
		this.movieId = movieId;
		this.title = title;
		this.releaseDate = releaseDate;
	}

	public Movies() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieId = ").append(movieId).append(", title = ").append(title).append(", releaseDate = ")
				.append(releaseDate);
		return builder.toString();
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public java.util.Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(java.util.Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
