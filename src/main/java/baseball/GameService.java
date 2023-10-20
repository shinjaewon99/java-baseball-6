package baseball;

import java.util.List;

public class GameService {
    OutPutView outPutView = new OutPutView();
    InputView inputView = new InputView();
    GameException exception = new GameException();
    StrikeBallCount strikeBallCount = new StrikeBallCount();
    Player player = new Player();
    Computer computer = new Computer();

    public void playBaseBallGame(){
        player.settingPlayerNumber(inputPlayerNumber());
        computer.settingComputerRandomNumbers();
        computerNumberComparePlayerInputNumber(player, computer.computer);
        printStrikeBallCount();
    }
    public int[] inputPlayerNumber() {
        outPutView.printRequestInputNumberMessage();
        String inputPlayerNumber = inputView.inputPlayerNumber();
        return toIntArray(inputPlayerNumber);
    }

    public void computerNumberComparePlayerInputNumber(Player player, List<Integer> computer) {
        for (int i = 0; i < computer.size(); i++) {
            if (computer.get(i) == player.getPlayerNumber(i)) {
                strikeBallCount.plusStringCount();
                continue;
            }
            if (computer.contains(player.getPlayerNumber(i))) {
                strikeBallCount.plusBallCount();
            }
        }
    }

    public int getStrikeCount() {
        return strikeBallCount.getStrike();
    }

    public int getBallCount() {
        return strikeBallCount.getBall();
    }

    public void printStrikeBallCount() {
        outPutView.printStrikeBallCountMessage(getStrikeCount(), getBallCount());
    }

    public int[] toIntArray(String inputPlayerNumber) {
        int[] playerNumbers = new int[inputPlayerNumber.length()];
        for (int i = 0; i < inputPlayerNumber.length(); i++) {
            char storeNumber = inputPlayerNumber.charAt(i);
            exception.playerNumberDuplicateException(playerNumbers, storeNumber);
            playerNumbers[i] = toInt(storeNumber);
        }
        return playerNumbers;
    }

    static int toInt(char storeNumber) {
        return storeNumber - '0';
    }
}
