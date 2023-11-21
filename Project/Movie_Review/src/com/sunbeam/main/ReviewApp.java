package com.sunbeam.main;

import static com.sunbeam.main.MainMenu.mainMenu;
import static com.sunbeam.main.SignInMenu.signInMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.classes.Movies;
import com.sunbeam.classes.MoviesDao;
import com.sunbeam.classes.Reviews;
import com.sunbeam.classes.ReviewsDao;
import com.sunbeam.classes.Users;
import com.sunbeam.classes.UsersDao;

public class ReviewApp {
	public static void main(String[] args) {
		try (UsersDao uDao = new UsersDao()) {
			try (MoviesDao mDao = new MoviesDao()) {
				try (ReviewsDao rDao = new ReviewsDao()) {
					Scanner sc = new Scanner(System.in);
					Users currentUser = null;
// =========================================================================================================================
					while (true) {
						ESignInMenu choice; // Login Page
						if (currentUser == null) {
							while ((choice = signInMenu(sc)) != null) {
								switch (choice) {
								case EXIT: {
									System.out.println("Bye..");
									System.exit(0);
								}

								case SIGN_UP: {
									System.out.print("First Name : ");
									String firstName = sc.next();
									System.out.print("Last Name : ");
									String lastName = sc.next();
									System.out.print("Email : ");
									String email = sc.next();
									System.out.print("Password : ");
									String password = sc.next();
									System.out.print("Mobile (10 digit) : ");
									String mobile = sc.next();
									System.out.print("BirthDate (DD-MM-YYYY) : ");
									String dateString = sc.next();
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
									java.util.Date birthDate = sdf.parse(dateString);

									Users newUser = new Users(0, firstName, lastName, email, password, mobile,
											birthDate);
									uDao.signUp(newUser);
									break;
								}

								case SIGN_IN: {
									System.out.print("Email : ");
									String email = sc.next();
									System.out.print("Password : ");
									String password = sc.next();

									currentUser = uDao.signIn(email, password); // user fetched from DB if login
																				// successful,
																				// null if unsuccessful
									if (currentUser == null)
										System.out.println("Details incorrect. Please retry.");
									else
										System.out.println("Successful Login");
									break;
								}

								default: {
									System.out.println("Wrong input. Please try again..");
									break;
								}
								}
								if (currentUser != null)
									break;
							}
						}

// ========================================================================================================================		

						EMainMenu choice1; // Home Page
						if (currentUser != null) {
							while ((choice1 = mainMenu(sc)) != null) {
								switch (choice1) {
								case LOG_OUT: {
									currentUser = null;
									System.out.println("Logged out successfully.");
									break;
								}

								case EDIT_PROFILE: {
									System.out.print("First Name : ");
									String firstName = sc.next();
									System.out.print("Last Name : ");
									String lastName = sc.next();
									System.out.print("Mobile (10 digit) : ");
									String mobile = sc.next();
									System.out.print("BirthDate (DD-MM-YYYY) : ");
									String dateString = sc.next();
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
									java.util.Date birthDate = sdf.parse(dateString);
									Users newUser = new Users(currentUser.getUserId(), firstName, lastName, null, null,
											mobile, birthDate);
									uDao.editProfile(newUser);
									break;
								}

								case CHANGE_PASSWORD: {
									System.out.print("Current Password :: ");
									String currPassword = sc.next();
									if (currPassword.equals(currentUser.getPassword())) {
										System.out.print("Enter new Password :: ");
										String newPassword = sc.next();
										int count = uDao.changePassword(newPassword, currentUser.getUserId());
										if (count == 0)
											System.out.println(
													"There was some issue with resetting the password. Please retry..");
										else
											System.out.println("Password changed successfully.");
									} else {
										System.out.println("Wrong password entered.");
									}

									break;
								}

								case WRITE_REVIEW: {
									int index = 0;
									List<Movies> availableMovies = new ArrayList<Movies>();
									availableMovies = mDao.displayAllMovies();
									System.out.println("Available Movies Are:");
									System.out.println("MOVIE_ID\tTITLE\tRELEASE_DATE:");
									for (int i = 0; i < availableMovies.size(); i++) {
										System.out.printf("%s\t%s\t%s\n", availableMovies.get(i).getMovieId(),
												availableMovies.get(i).getTitle(),
												availableMovies.get(i).getReleaseDate());
									}
									boolean flagMovieFound = false;
									System.out.print("ENTER MOVIE ID FROM ABOVE LIST TO WRITE A REVIEW:");
									int movie_id = sc.nextInt();
									for (int i = 0; i < availableMovies.size(); i++) {
										if (availableMovies.get(i).getMovieId() == movie_id) {
											flagMovieFound = true;
											index = i;
										}
									}
									if (flagMovieFound != false) {
										System.out.println("Movie :" + availableMovies.get(index).getTitle());
										sc.nextLine();
										while (true) {
											System.out.println("Enter Your Review:");
											String review = sc.nextLine();

											if (!review.isEmpty() && review.contains(" ")) {
												System.out.print("Enter Rating:");
												int rating = sc.nextInt();
												rDao.createReview(currentUser, availableMovies.get(index), review,
														rating);
												break;
											} else {
												System.out.println("You cannot enter blank/one worded review.");
											}
										}
									} else {
										System.out.println("Unable to find movie with current Movie ID");
									}
									break;
								}

								case EDIT_REVIEW: {
									boolean reviewFound = false;
									List<Reviews> list = new ArrayList<Reviews>();
									list = rDao.displayMyReviews(currentUser.getUserId());
									System.out.println("My Reviews Are:");
									System.out.printf("%s\t\t%s\t\t%s\n", "ReviewID", "Review", "Rating");
									for (int i = 0; i < list.size(); i++) {
										System.out.printf("%s\t\t%s\t\t%s\n", list.get(i).getReviewId(),
												list.get(i).getReview(), list.get(i).getRating());
									}
									System.out.print("\nEnter Review ID to Edit Review:");
									int reviewID = sc.nextInt();
									for (int i = 0; i < list.size(); i++) {
										if (reviewID == list.get(i).getReviewId())
											reviewFound = true;
									}
									if (reviewFound != false) {
										sc.nextLine();
										String review;
										while (true) {
											System.out.println("Enter Your Review:");
											review = sc.nextLine();
											if (review.contains(" ") && !review.isEmpty()) {
												break;
											} else {
												System.out.println("You cannot enter blank/one worded review.");
											}
										}

										System.out.print("Enter Rating:");
										int rating = sc.nextInt();
										if (rating > 10) {
											System.out.println("Rating is More Than 10, Setting Rating as 10");
											rating = 10;
										}
										if (rating < 0) {
											System.out.println("Rating is Less Than 0, Setting Rating as 0");
											rating = 0;
										}
										rDao.editReview(currentUser.getUserId(), review, rating, reviewID);
									} else {
										System.out.println("You have not added any reviews to this movie");
									}
									break;
								}

								case DISPLAY_ALL_MOVIES: {
									List<Movies> movieList = mDao.displayAllMovies();
									movieList.forEach(x -> System.out.println(x));
									break;
								}

								case DISPLAY_ALL_REVIEWS: {
									List<Reviews> reviewList = rDao.displayAllReviews();
									reviewList.forEach(x -> System.out.println(x));
									break;
								}

								case DISPLAY_MY_REVIEWS: {
									List<Reviews> reviewList = rDao.displayMyReviews(currentUser.getUserId());
									if (reviewList != null) {
										reviewList.forEach(x -> System.out.println(x));
									}
									break;
								}

								case DISPLAY_REVIEWS_SHARED_WITH_ME: {
									List<Reviews> reviewList = rDao.displaySharedReview(currentUser.getUserId());
									if (!reviewList.isEmpty()) {
										reviewList.forEach(x -> System.out.println(x));
									} else {
										System.out.println("No reviews shared with you..");
									}
									break;
								}

								case SHARE_REVIEW: {
									boolean userFlag = false;
									boolean reviewFlag = false;
									List<Reviews> list = new ArrayList<Reviews>();
									list = rDao.displayMyReviews(currentUser.getUserId());
									System.out.println("My Reviews Are:");
									System.out.printf("%s\t\t%s\t\t%s\n", "ReviewID", "Review", "Rating");
									for (int i = 0; i < list.size(); i++) {
										System.out.printf("%s\t\t%s\t\t%s\n", list.get(i).getReviewId(),
												list.get(i).getReview(), list.get(i).getRating());
									}
									System.out.print("\nEnter the review ID to be shared ::");
									int review_id = sc.nextInt();
									for (int i = 0; i < list.size(); i++) {
										if (list.get(i).getReviewId() == review_id) {
											reviewFlag = true;
										}
									}
									if (reviewFlag != false) {
										List<Users> userList = uDao.displayUsers();
										userList.forEach(x -> System.out.println(x));
										System.out.print("Enter the user ID to share the review with (end with 0):: ");

										List<Integer> users = new ArrayList<>();

										int user_id = 1;
										while (user_id != 0) {
											user_id = sc.nextInt();
											if (user_id != 0)
												users.add(user_id);
										}

										for (Integer userId : users) {
											if (userId == currentUser.getUserId()) {
												System.out.println("You cannot share review with yourself.");
												break;
											}

											for (int i = 0; i < userList.size(); i++) {
												if (userList.get(i).getUserId() == userId) {
													userFlag = true;
												}
											}

											if (userFlag != false) {
												int count = rDao.shareReview(review_id, userId);
												if (count == 0)
													System.out.println("Review share unsuccessful.");
												else
													System.out.println("Review share successful.");
											} else {
												System.out.println("User ID not found.");
											}
										}
										// int user_id = sc.nextInt();

									} else {
										System.out.println("Review ID not found.");
									}
									break;
								}

								case DELETE_REVIEW: {
									boolean reviewFlag = false;
									int review_id;
									List<Reviews> list = new ArrayList<Reviews>();
									list = rDao.displayMyReviews(currentUser.getUserId());
									System.out.println("My Reviews Are:");
									System.out.printf("%s\t\t%s\t\t%s\n", "ReviewID", "Review", "Rating");
									for (int i = 0; i < list.size(); i++) {
										System.out.printf("%s\t\t%s\t\t%s\n", list.get(i).getReviewId(),
												list.get(i).getReview(), list.get(i).getRating());
									}
									System.out.print("\nEnter the review ID to be deleted ::");
									review_id = sc.nextInt();
									for (int i = 0; i < list.size(); i++) {
										if (list.get(i).getReviewId() == review_id) {
											reviewFlag = true;
										}
									}
									if (reviewFlag == true) {

										int count = rDao.deleteReview(review_id);
										if (count == 0) {
											System.out.println("Delete Operation Unsuccefull");
										} else {
											System.out.println("Delte Operation Succefull");
										}
									} else {
										System.out.println("Entry ID not found.");
									}

									break;
								}

								default:
									break;
								}

								if (currentUser == null)
									break;
							}
						}
					}
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
