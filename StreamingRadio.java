/**
 * This abstract data type represents the backend for a streaming radio service.
 * It stores the songs, stations, and users in the system, as well as the
 * ratings that users assign to songs.
 */
public interface StreamingRadio {

	/*
	 * The abstract methods below are declared as void methods with no parameters.
	 * You need to expand each declaration to specify a return type and parameters,
	 * as necessary.
	 *
	 * You also need to include a detailed comment for each abstract method
	 * describing its effect, its return value, any corner cases that the client may
	 * need to consider, any exceptions the method may throw (including a
	 * description of the circumstances under which this will happen), and so on.
	 *
	 * You should include enough details that a client could use this data structure
	 * without ever being surprised or not knowing what will happen, even though
	 * they haven't read the implementation.
	 */

	/**
	 * Adds a new song to the system.
	 * 
	 * If the system has not reached its maximum number of songs, then this method
	 * will add a new song to the system. If the system has already reached its
	 * maximum number of songs, then this method will double the capacity of the
	 * system and then add the new song. If the new song to be added already exists
	 * in the system, then the method will throw an IllegalArgumentException.
	 * 
	 * @param newSong song being added to the system
	 * @throws IllegalArgumentException if the new song already exists in the system
	 * @throws NullPointerException     if the new song being added is null
	 */
	public void addSong(Song newSong) throws IllegalArgumentException, NullPointerException;

	/**
	 * Removes an existing song from the system.
	 * 
	 * If a specified song currently exists in the system, then this method removes
	 * the specified song from the system. If the specified song does not currently
	 * exist in the system, then this method will throw an IllegalArgumentException.
	 * 
	 * @param theSong song to remove from the system
	 * @throws IllegalArgumentException if the song to remove does not exist in the
	 *                                  system
	 * @throws NullPointerException     if the song to remove is null
	 */
	public void removeSong(Song theSong) throws IllegalArgumentException, NullPointerException;

	/**
	 * Adds an existing song to the playlist for an existing radio station.
	 * 
	 * This method will add a song to a station's playlist and return true if the
	 * station allowed it. If the station did not allow the song to be added (most
	 * likely if there is a capacity limit), then the song is not added to the
	 * station's playlist and this method returns false. If the song or the station
	 * does not exist in the system, then this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param theStation station containing the playlist to add to
	 * @param theSong    song to be added
	 * @return true if the song was added to the station's playlist; false if the
	 *         song was not added to the station's playlist
	 * @throws IllegalArgumentException if the song being added does not exist in
	 *                                  the system; if the station does not exist in
	 *                                  the system
	 * @throws NullPointerException     if the station or song is null
	 */
	public boolean addToStation(Station theStation, Song theSong) throws IllegalArgumentException, NullPointerException;

	/**
	 * Removes a song from the playlist for a radio station.
	 * 
	 * This method will remove a song from a station's playlist and return true if
	 * the station allowed it. If the station did not allow the song to be removed
	 * (most likely because the playlist does not contain the song), then a song is
	 * not removed from the station's playlist and this method will return false. If
	 * the station does not exist in the system, then this method will throw an
	 * IllegalArguementException.
	 * 
	 * @param theStation station containing the playlist to be removed from
	 * @param theSong    song to be removed
	 * @return true if the song was removed from the station's playlist; false if
	 *         the song was not removed from the station's playlist
	 * @throws IllegalArgumentException if the station does not exist in the system
	 * @throws NullPointerException     if the station or song is null
	 */
	public boolean removeFromStation(Station theStation, Song theSong)
			throws IllegalArgumentException, NullPointerException;

	/**
	 * Sets a user's rating for a song, as a number of stars from 1 to 5.
	 * 
	 * If the specified user did not rate the song yet, then this method will set
	 * the user's rating, as a number of stars, to the specified rating from 1 to 5.
	 * If the specified user already rated the song, then this method will change
	 * the rating to the new specified rating, as a number of stars, from 1 to 5. If
	 * the song or user does not exist in the system or the specified rating is less
	 * than 1 or greater than 5, then this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param theUser   user rating the song
	 * @param theSong   song being rated
	 * @param theRating rating, as stars, from 1 to 5
	 * @throws IllegalArgumentException if the song does not exist in the system; if
	 *                                  the user does not exist in the system; if
	 *                                  the rating is less than 1 or greater than 5
	 * @throws NullPointerException     if either the user or the song is null
	 */
	public void rateSong(User theUser, Song theSong, int theRating) throws IllegalArgumentException, NullPointerException;

	/**
	 * Clears a user's rating on a song. If this user has rated this song and the
	 * rating has not already been cleared, then the rating is cleared and the state
	 * will appear as if the rating was never made. If there is no such rating on
	 * record (either because this user has not rated this song, or because the
	 * rating has already been cleared), then this method will throw an
	 * IllegalArgumentException.
	 *
	 * @param theUser user whose rating should be cleared
	 * @param theSong song from which the user's rating should be cleared
	 * @throws IllegalArgumentException if the user does not currently have a rating
	 *                                  on record for the song
	 * @throws NullPointerException     if either the user or the song is null
	 */
	public void clearRating(User theUser, Song theSong) throws IllegalArgumentException, NullPointerException;

	/**
	 * Predicts the rating a user will assign to a song that they have not yet
	 * rated, as a number of stars from 1 to 5.
	 * 
	 * This method looks at the ratings a user has given to previous songs that are
	 * similar to the unrated song, then predicts and returns a rating, as a number
	 * of stars, for the unrated song based on those ratings. If the user has
	 * already rated the song, this method will return the user's current rating. If
	 * the user has not rated any songs yet, then this method will return -1. If the
	 * user or the song does not exist in the system, then this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param theUser user to predict a rating for
	 * @param theSong song to predict rating for
	 * @return predicted rating if the user has not rated the song; current rating
	 *         is the user has already rated the song; -1 if the user has not
	 *         previously rated any songs
	 * @throws IllegalArgumentException if the user does not exist in the system; if
	 *                                  the song does not exist in the system
	 * @throws NullPointerException     if the user or the song is null
	 */
	public int predictRating(User theUser, Song theSong) throws IllegalArgumentException, NullPointerException;

	/**
	 * Suggests a song for a user that they are predicted to like.
	 * 
	 * This method looks at all of the previous songs a user has rated and compares
	 * the highest rated songs to all of the songs in the system that the user has
	 * not rated, this method then returns the first unrated song in the system that
	 * is similar to the user's highest rated songs. If there are no unrated songs
	 * or there are no songs that can be suggested, then this method returns null.
	 * If the user does not exist in the system, then this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param theUser user to suggest a song for
	 * @return a song the user is predicted to like; null if there are no unrated
	 *         songs; null if no song can be suggested
	 * @throws IllegalArgumentException if the user does not exist in the system
	 * @throws NullPointerException     if the user is null
	 */
	public Song suggestSong(User theUser) throws IllegalArgumentException, NullPointerException;
}