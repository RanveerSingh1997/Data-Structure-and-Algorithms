import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * Practice Template: Movie Recommendation System (LLD)
 * Difficulty: Medium | Target Companies: Netflix, Amazon Prime, Spotify, Hulu
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Design a personalized movie recommendation engine. The system maintains user
 * profiles, movies with multi-genre metadata, and user rating histories (1 to 5 stars).
 * It must support pluggable recommendation algorithms (Top-Rated / Popularity,
 * and Genre / Content-Based filtering) via the Strategy Pattern.
 *
 * 📥 REQUIREMENTS:
 * 1. Movie Management: ID, title, release year, genres, average rating, and review count.
 * 2. User Profiles: Record user ratings (1.0 to 5.0) and track watched movies.
 * 3. Pluggable Strategies (Strategy Pattern):
 *    - TopRatedStrategy: Recommends the highest rated movies not yet watched by the user.
 *    - GenreBasedStrategy: Finds genres the user rated >= 4.0 and recommends unwatched movies in those genres.
 * 4. Extensibility: New strategies can be plugged in without changing the core engine.
 *
 * 💡 INTERVIEW HINTS:
 * - Use Java Streams to filter unwatched movies (`!user.hasWatched(m.getMovieId())`).
 * - Apply Strategy pattern for `RecommendationStrategy`.
 */
public class DesginMovieRecomendation {

    // =========================================================================
    // 1. ENUMS & DATA MODELS
    // =========================================================================

    public enum Genre {
        ACTION, ADVENTURE, COMEDY, DRAMA, SCI_FI, THRILLER, ANIMATION, HORROR
    }

    public static class Movie {
        private final String movieId;
        private final String title;
        private final int releaseYear;
        private final Set<Genre> genres;
        private double totalRatingPoints;
        private int ratingCount;

        public Movie(String movieId, String title, int releaseYear, Set<Genre> genres) {
            this.movieId = movieId;
            this.title = title;
            this.releaseYear = releaseYear;
            this.genres = new HashSet<>(genres);
            this.totalRatingPoints = 0;
            this.ratingCount = 0;
        }

        public synchronized void addRating(double rating) {
            // TODO: Update totalRatingPoints and increment ratingCount
        }

        public double getAverageRating() {
            return ratingCount == 0 ? 0.0 : totalRatingPoints / ratingCount;
        }

        public String getMovieId() { return movieId; }
        public String getTitle() { return title; }
        public int getReleaseYear() { return releaseYear; }
        public Set<Genre> getGenres() { return Collections.unmodifiableSet(genres); }
        public int getRatingCount() { return ratingCount; }

        @Override
        public String toString() {
            return String.format("%s (%d) [%.1f★, %d reviews] Genres: %s",
                    title, releaseYear, getAverageRating(), ratingCount, genres);
        }
    }

    public static class User {
        private final String userId;
        private final String name;
        private final Map<String, Double> ratedMovies = new HashMap<>();

        public User(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public void rateMovie(String movieId, double rating) {
            if (rating < 1.0 || rating > 5.0) {
                throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
            }
            ratedMovies.put(movieId, rating);
        }

        public boolean hasWatched(String movieId) {
            return ratedMovies.containsKey(movieId);
        }

        public String getUserId() { return userId; }
        public String getName() { return name; }
        public Map<String, Double> getRatedMovies() { return Collections.unmodifiableMap(ratedMovies); }
    }

    // =========================================================================
    // 2. STRATEGY PATTERN: RECOMMENDATION STRATEGIES
    // =========================================================================

    public interface RecommendationStrategy {
        List<Movie> recommend(User user, Map<String, Movie> catalog, int limit);
    }

    /**
     * Recommends the highest rated unwatched movies with at least 1 rating.
     */
    public static class TopRatedStrategy implements RecommendationStrategy {
        @Override
        public List<Movie> recommend(User user, Map<String, Movie> catalog, int limit) {
            // TODO: Implement TopRatedStrategy:
            // 1. Filter catalog for unwatched movies (!user.hasWatched)
            // 2. Filter for ratingCount > 0
            // 3. Sort by averageRating descending
            // 4. Limit to `limit` items
            return Collections.emptyList();
        }
    }

    /**
     * Recommends unwatched movies that match the user's favorite genres (genres rated >= 4.0).
     */
    public static class GenreBasedStrategy implements RecommendationStrategy {
        @Override
        public List<Movie> recommend(User user, Map<String, Movie> catalog, int limit) {
            // TODO: Implement GenreBasedStrategy:
            // 1. Collect preferred genres from movies user rated >= 4.0
            // 2. Filter catalog for unwatched movies matching at least one preferred genre
            // 3. Sort by genre match count and averageRating descending
            // 4. Limit to `limit` items
            return Collections.emptyList();
        }
    }

    // =========================================================================
    // 3. RECOMMENDATION ENGINE CONTROLLER
    // =========================================================================

    private final Map<String, Movie> catalog = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();
    private RecommendationStrategy strategy = new TopRatedStrategy();

    public void addMovie(Movie movie) { catalog.put(movie.getMovieId(), movie); }
    public void registerUser(User user) { users.put(user.getUserId(), user); }
    public void setStrategy(RecommendationStrategy strategy) { this.strategy = Objects.requireNonNull(strategy); }

    public void rateMovie(String userId, String movieId, double rating) {
        User user = users.get(userId);
        Movie movie = catalog.get(movieId);
        if (user != null && movie != null) {
            user.rateMovie(movieId, rating);
            movie.addRating(rating);
        }
    }

    public List<Movie> getRecommendations(String userId, int limit) {
        User user = users.get(userId);
        if (user == null) return Collections.emptyList();
        return strategy.recommend(user, catalog, limit);
    }

    // =========================================================================
    // 4. VERIFICATION TEST HARNESS (Run to test your code!)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Testing: Movie Recommendation Engine ===");

        DesginMovieRecomendation service = new DesginMovieRecomendation();

        Movie m1 = new Movie("M1", "Inception", 2010, Set.of(Genre.SCI_FI, Genre.ACTION));
        Movie m2 = new Movie("M2", "Interstellar", 2014, Set.of(Genre.SCI_FI, Genre.DRAMA));
        Movie m3 = new Movie("M3", "The Dark Knight", 2008, Set.of(Genre.ACTION, Genre.THRILLER));

        service.addMovie(m1);
        service.addMovie(m2);
        service.addMovie(m3);

        User bob = new User("U1", "Bob");
        User alice = new User("U2", "Alice");
        service.registerUser(bob);
        service.registerUser(alice);

        service.rateMovie("U2", "M1", 5.0);
        service.rateMovie("U2", "M2", 4.8);
        service.rateMovie("U2", "M3", 4.9);
        service.rateMovie("U1", "M1", 4.5); // Bob watched Inception

        // Test 1: Top Rated
        service.setStrategy(new TopRatedStrategy());
        List<Movie> topRated = service.getRecommendations("U1", 2);
        if (!topRated.isEmpty() && !topRated.contains(m1)) {
            System.out.println("  [PASS] Test 1: Top-rated recommendation returned unwatched movies.");
        } else {
            System.out.println("  [TODO] Test 1: TopRatedStrategy not implemented yet.");
        }

        // Test 2: Genre Based
        service.setStrategy(new GenreBasedStrategy());
        List<Movie> genreRecs = service.getRecommendations("U1", 2);
        if (!genreRecs.isEmpty()) {
            System.out.println("  [PASS] Test 2: Genre-based strategy returned recommendations.");
        } else {
            System.out.println("  [TODO] Test 2: GenreBasedStrategy not implemented yet.");
        }
    }
}
