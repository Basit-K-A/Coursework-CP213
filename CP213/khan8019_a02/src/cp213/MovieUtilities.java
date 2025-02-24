package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author Basit Khan, 169058019, khan8019@mylaurier.ca
 * @version 2024-09-01 
 */
public class MovieUtilities {

    /**
     * Counts the number of movies in each genre given in Movie.GENRES. An empty
     * movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
     *
     * @param movies List of movies.
     * @return Number of genres across all Movies. One entry for each genre in the
     *         Movie.GENRES array.
     */
    public static int[] genreCounts(final ArrayList<Movie> movies) {
	
	final int[] rArr = {0,0,0,0,0,0,0,0,0,0,0};
 	for(int i = 0; i < movies.size(); i++) {
 	    int index = (movies.get(i)).getGenre();
 	    rArr[index] += 1;
 	}
 
	return rArr;
    }

    /**
     * Creates a Movie object by requesting data from a user. Uses the format:
     *
     * <pre>
    Title:
    Year:
    Director:
    Rating:
    Genres:
    0: science fiction
    1: fantasy
    ...
    10: mystery

    Enter a genre number:
     * </pre>
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return A Movie object.
     */
    public static Movie getMovie(final Scanner keyboard) {

	System.out.println("Title:");
	String title = keyboard.next();
	System.out.println("Year: ");
	int year = keyboard.nextInt();
	System.out.println("Director: ");
	String director = keyboard.next();
	System.out.println("Rating: ");
	double rating = keyboard.nextDouble();
	
	int genre = MovieUtilities.readGenre(keyboard);
	Movie m = new Movie(title,year,director,rating,genre);
	System.out.println(m.genreToName());
	return m; 
    }

    /**
     * Creates a list of Movies whose genre is equal to the genre parameter.
     *
     * @param movies List of movies.
     * @param genre  Genre to compare against.
     * @return List of movies of genre.
     */
    public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

	ArrayList<Movie> GMovies = new ArrayList<Movie>();
	
	for(int i = 0; i < movies.size(); i++) {
	    if(movies.get(i).getGenre() == genre) {
		GMovies.add(movies.get(i));
	    }
	}
	//System.out.println(GMovies);
	return GMovies;
    }

    /**
     * Creates a list of Movies whose ratings are equal to or higher than rating.
     *
     * @param movies List of movies.
     * @param rating Rating to compare against.
     * @return List of movies of rating or higher.
     */
    public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

	ArrayList<Movie> RMovies = new ArrayList<Movie>();
	
	for(int i = 0; i < movies.size(); i++) {
	    if(movies.get(i).getRating() == rating) {
		RMovies.add(movies.get(i));
	    }
	}

	return RMovies;
    }

    /**
     * Creates a list of Movies from a particular year.
     *
     * @param movies List of movies.
     * @param year   Year to compare against.
     * @return List of movies of year.
     */
    public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {
	ArrayList<Movie> YMovies = new ArrayList<Movie>();
	
	for(int i = 0; i < movies.size(); i++) {
	    if(movies.get(i).getYear() == year) {
		YMovies.add(movies.get(i));
	    }
	}

	return YMovies;
    }

    /**
     * Asks a user to select a genre from a list of genres displayed by calling
     * Movie.genresMenu() and returns an integer genre code. The genre must be a
     * valid index to an item in Movie.GENRES.
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return An integer genre code.
     */
    public static int readGenre(final Scanner keyboard) {
	System.out.println("Genres: ");
	System.out.println(Movie.genresMenu());
	System.out.println("Enter a genre number: ");
	int genre = keyboard.nextInt();
	while(genre < 0 || genre > 10) {
	    System.out.println("not a valied genre try again");
	    genre = keyboard.nextInt();
	}
	return genre;
    }

    /**
     * Creates and returns a Movie object from a line of formatted string data.
     *
     * @param line A vertical bar-delimited line of movie data in the format
     *             title|year|director|rating|genre
     * @return The data from line as a Movie object.
     */
    public static Movie readMovie(final String line) {
	//System.out.println(line);
	String[] atts = line.split("\\|");
	//System.out.println(atts);
	String title = atts[0];
	int year = Integer.valueOf(atts[1]);
	String director = atts[2];
	double rating = Double.valueOf(atts[3]);
	int genre = Integer.valueOf(atts[4]);
	
	Movie m = new Movie(title,year,director,rating,genre);
	
	return m;
    }

    /**
     * Reads a list of Movies from a file.
     *
     * @param fileIn A Scanner of a Movie data file in the format
     *               title|year|director|rating|genre
     * @return A list of Movie objects.
     */
    public static ArrayList<Movie> readMovies(final Scanner fileIn) {
	
	String line;
	ArrayList<Movie> YMovies = new ArrayList<Movie>();
	
	while(fileIn.hasNext()) {
        	line = fileIn.nextLine();
        	YMovies.add(readMovie(line));
	}
	return YMovies;
    }

    /**
     * Writes the contents of a list of Movie to a PrintStream.
     *
     * @param movies A list of Movie objects.
     * @param ps     Output PrintStream.
     */
    public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

	for(int i = 0; i < movies.size(); i++) {
	    ps.println(movies.get(i));
	}

	return;
    }

}
