import java.util.*;

public class UsernameChecker {
    // Maps username -> userId (simulating registered users)
    private Map<String, Integer> userDatabase;

    // Tracks frequency of attempted usernames
    private Map<String, Integer> attemptFrequency;

    public UsernameChecker() {
        userDatabase = new HashMap<>();
        attemptFrequency = new HashMap<>();

        // Pre-populate with some taken usernames
        userDatabase.put("john_doe", 1001);
        userDatabase.put("jane_smith", 1002);
        userDatabase.put("admin", 1);
    }

    // Check if username is available
    public boolean checkAvailability(String username) {
        // Track attempt frequency
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);

        return !userDatabase.containsKey(username);
    }

    // Suggest alternative usernames if taken
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        // Append numbers
        for (int i = 1; i <= 3; i++) {
            String suggestion = username + i;
            if (!userDatabase.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // Replace underscore with dot
        if (username.contains("_")) {
            String suggestion = username.replace("_", ".");
            if (!userDatabase.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // Add random suffix
        String randomSuggestion = username + "_" + new Random().nextInt(1000);
        if (!userDatabase.containsKey(randomSuggestion)) {
            suggestions.add(randomSuggestion);
        }

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {
        String mostAttempted = null;
        int maxAttempts = 0;

        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > maxAttempts) {
                maxAttempts = entry.getValue();
                mostAttempted = entry.getKey();
            }
        }

        return mostAttempted + " (" + maxAttempts + " attempts)";
    }

    // Register a new username
    public boolean registerUsername(String username, int userId) {
        if (checkAvailability(username)) {
            userDatabase.put(username, userId);
            return true;
        }
        return false;
    }

    // Demo
    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();

        System.out.println("checkAvailability(\"john_doe\") → " + checker.checkAvailability("john_doe"));
        System.out.println("checkAvailability(\"jane_smith\") → " + checker.checkAvailability("jane_smith"));
        System.out.println("checkAvailability(\"new_user\") → " + checker.checkAvailability("new_user"));

        System.out.println("suggestAlternatives(\"john_doe\") → " + checker.suggestAlternatives("john_doe"));

        // Simulate multiple attempts
        for (int i = 0; i < 5; i++) checker.checkAvailability("admin");
        for (int i = 0; i < 3; i++) checker.checkAvailability("john_doe");

        System.out.println("getMostAttempted() → " + checker.getMostAttempted());
    }
}