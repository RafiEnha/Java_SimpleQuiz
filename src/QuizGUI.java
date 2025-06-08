import javax.swing.*;
import java.awt.*;

public class QuizGUI extends JFrame {
    private Quiz quiz;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JTextField answerField;
    private JButton nextButton;
    private ButtonGroup group;

    public QuizGUI(String type, String topic) {
        quiz = QuizFactory.createQuiz(type, topic);
        initUI(type);
        loadQuestion();
    }

    private void initUI(String type) {
        setTitle("Simple Quiz");
        if (type.equalsIgnoreCase("multiple")) {
            setSize(600, 300);
        } else {
            setSize(600, 200);
        }
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(questionLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        optionButtons = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            group.add(optionButtons[i]);
            centerPanel.add(optionButtons[i]);
        }

        answerField = new JTextField();
        answerField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        centerPanel.add(answerField);

        add(centerPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            String answer = "";

            if (quiz instanceof MultipleChoiceQuiz) {
                for (JRadioButton btn : optionButtons) {
                    if (btn.isSelected()) {
                        answer = btn.getText();
                        break;
                    }
                }
            } else {
                answer = answerField.getText();
            }

            quiz.checkAnswer(answer);

            if (quiz.hasNextQuestion()) {
                quiz.nextQuestion();
                loadQuestion();
            } else {
                JOptionPane.showMessageDialog(this, "Quiz Finished! Score: " + quiz.getScore());
                dispose();
            }
        });
        add(nextButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadQuestion() {
        Question q = quiz.getCurrentQuestion();
        questionLabel.setText("<html><div style='width:550;'>" + q.getQuestion() + "</div></html>");

        if (quiz instanceof MultipleChoiceQuiz) {
            String[] options = ((MultipleChoiceQuiz) quiz).getCurrentOptions();
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setVisible(true);
            }
            answerField.setVisible(false);
        } else {
            for (JRadioButton btn : optionButtons) {
                btn.setVisible(false);
            }
            answerField.setVisible(true);
        }

        group.clearSelection();
        answerField.setText("");
    }
}