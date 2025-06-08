import java.util.*;

public class FillInTheBlankQuiz implements Quiz {
    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;

    @Override
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean checkAnswer(String answer) {
        if (questions.get(currentIndex).getAnswer().equalsIgnoreCase(answer)) {
            score++;
        }
        return true;
    }

    @Override
    public boolean hasNextQuestion() {
        return currentIndex < questions.size() - 1;
    }

    @Override
    public void nextQuestion() {
        currentIndex++;
    }

    @Override
    public String getScore() {
        return Integer.toString(score) + " / " + questions.size();
    }

    @Override
    public Question getCurrentQuestion() {
        return questions.get(currentIndex);
    }

    @Override
    public void setAnswers(List<String[]> options){}
    
    @Override
    public String[] getCurrentOptions(){return null;}
}