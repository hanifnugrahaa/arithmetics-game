package midexam;

public class Player {
    private static final int INITIAL_LIVES = 3;

    private final String name;
    private int score = 0;
    private int lives = INITIAL_LIVES;

    public Player(String name) {
        this.name = name;
    }

   
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void subtractScore(int points) {
        this.score -= points;
    }

    
    public void loseLife() {
        this.lives--;
    }

    public boolean isAlive() {
        return this.lives > 0;
    }

    public boolean hasReachedScore(int targetScore) {
        return this.score >= targetScore;
    }
}