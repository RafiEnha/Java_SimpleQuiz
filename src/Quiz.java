import java.util.List;

public interface Quiz {
    void setQuestions(List<Question> questions);
    void setAnswers(List<String[]> options);
    boolean checkAnswer(String answer);
    boolean hasNextQuestion();
    void nextQuestion();
    String getScore();
    Question getCurrentQuestion();
    String[] getCurrentOptions();
}