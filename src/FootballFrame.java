import javax.swing.*;
import java.awt.*;

public class FootballFrame extends JFrame {
    private int firstTeamGoals = 0;
    private int secondTeamGoals = 0;

    private final JLabel titleLabel = new JLabel(TITLE_LABEL);
    private final JButton firstTeamButton = new JButton(FIRST_TEAM_NAME);
    private final JButton secondTeamButton = new JButton(SECOND_TEAM_NAME);
    private final JLabel resultLabel = new JLabel(formattedResult(firstTeamGoals, secondTeamGoals));
    private final JLabel lastScorerLabel = new JLabel(LAST_SCORER_LABEL + "N/A");
    private final JLabel winnerLabel = new JLabel(WINNER_LABEL + Winner.DRAW);

    public FootballFrame() {
        super("Football game");
        setBounds(100, 100, 400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupUi();
        setupActions();
    }

    private void setupUi() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(titleLabel);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(firstTeamButton);
        buttonPanel.add(secondTeamButton);
        mainPanel.add(buttonPanel);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(resultLabel);
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(lastScorerLabel);
        lastScorerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(winnerLabel);
        winnerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(mainPanel);
    }

    private void setupActions() {
        firstTeamButton.addActionListener(e -> updateUi(Winner.AC_MILAN));
        secondTeamButton.addActionListener(e -> updateUi(Winner.REAL_MADRID));
    }

    private void updateUi(Winner winner) {
        switch (winner) {
            case AC_MILAN -> {
                resultLabel.setText(formattedResult(++firstTeamGoals, secondTeamGoals));
                lastScorerLabel.setText(formattedLastScorer(FIRST_TEAM_NAME));
            }
            case REAL_MADRID -> {
                resultLabel.setText(formattedResult(firstTeamGoals, ++secondTeamGoals));
                lastScorerLabel.setText(formattedLastScorer(SECOND_TEAM_NAME));
            }
        }
        winnerLabel.setText(WINNER_LABEL + getWinner());
    }

    private String getWinner() {
        if (firstTeamGoals > secondTeamGoals)
            return FIRST_TEAM_NAME;
        else if (secondTeamGoals > firstTeamGoals)
            return SECOND_TEAM_NAME;
        return Winner.DRAW.name();
    }

    private String formattedResult(int first, int second) {
        return RESULT_LABEL + first + " x " + second;
    }

    private String formattedLastScorer(String winner) {
        return LAST_SCORER_LABEL + winner;
    }

    private static final String TITLE_LABEL = "Who scored the goal?";
    private static final String FIRST_TEAM_NAME = "AC Milan";
    private static final String SECOND_TEAM_NAME = "Real Madrid";
    private static final String RESULT_LABEL = "Result: ";
    private static final String LAST_SCORER_LABEL = "Last Scorer: ";
    private static final String WINNER_LABEL = "Winner: ";

    private enum Winner {
        DRAW, AC_MILAN, REAL_MADRID
    }
}
