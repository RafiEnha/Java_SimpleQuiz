public class QuizFactory {
    public static Quiz createQuiz(String type, String topic) {
        if (type.equalsIgnoreCase("multiple")) {
            MultipleChoiceQuiz quiz = new MultipleChoiceQuiz();
            quiz.setQuestions(DatabaseManager.getQuestions(type, topic));
            quiz.setAnswers(DatabaseManager.getOptions(type, topic));
            return quiz;
        } else {
            FillInTheBlankQuiz quiz = new FillInTheBlankQuiz();
            quiz.setQuestions(DatabaseManager.getQuestions(type, topic));
            return quiz;
        }
    }
}