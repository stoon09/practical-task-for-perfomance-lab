import java.io.*;
import java.util.*;

public class DistributionCards {

    private static ArrayList<Integer> fillArrayFromTo(ArrayList<Integer> arr, int from, int to) {
        for (int i = from; i <= to; i++) {
            arr.add(i);
        }

        return arr;
    }

    private static ArrayList<String> fillArrayWithEmptyChars(ArrayList<String> arr, int size) {
        for (int i = 0; i < size; i++) {
            arr.add("");
        }

        return arr;
    }

    private static ArrayList<String> getAllCardsCombinations(ArrayList<String> colorsArr,
            ArrayList<Integer> valuesArr) {
        ArrayList<String> allCombinations = new ArrayList<String>();

        for (int i = 0; i < colorsArr.size(); i++) {
            for (int j = 0; j < valuesArr.size(); j++) {
                allCombinations.add(colorsArr.get(i) + valuesArr.get(j));
            }
        }

        return allCombinations;
    }

    public static ArrayList<String> startDistribution(
        ArrayList<String> allCards,
        ArrayList<String> playersCards,
         int playerCount,
          int cardsCountForOnePlayer) {
            // мешаем колоду
            Collections.shuffle(allCards);

            int currentCard = 0;
            int allCardsSize = allCards.size();
            playersCards = fillArrayWithEmptyChars(playersCards, playerCount);

            if(allCardsSize < playerCount * cardsCountForOnePlayer) {
                System.out.println("Невозможно раздать " + cardsCountForOnePlayer + " карт для " + 
                    playerCount + " игроков, количество доступных карт = " + allCardsSize + "\n" +
                    "Раздайте меньше карт или уменьшите количество игроков.");
                return new ArrayList<String>();
            }

            for (int i = 0; i < playerCount; i++) {
                for (int j = 0; j < cardsCountForOnePlayer; j++) {
                    playersCards.set(i, playersCards.get(i) + " " + allCards.get(currentCard));
                    currentCard++;
                }
            }             
            return playersCards;
    }

    public static void showPlayerCards(ArrayList<String> playersCards, int playerNumber) {
        if(playerNumber > playersCards.size())  {
            System.out.println("Игрока под номером " + playerNumber + " не существует, количество игроков = " + playersCards.size());
        } else {
            System.out.println(playerNumber + " " + playersCards.get(playerNumber - 1));
        }            
    }
        

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString;
        ArrayList<String> colors = new ArrayList<String>(Arrays.asList("R", "G", "B", "W"));
        ArrayList<Integer> values = fillArrayFromTo(new ArrayList<Integer>(), 1, 10);
        ArrayList<String> allCardsCombinations = getAllCardsCombinations(colors, values);
        ArrayList<String> playersCards = new ArrayList<String>();               
                
        while (!"exit".equals(inputString = reader.readLine())) {
            String operation = inputString.substring(0, inputString.indexOf(' '));
            String[] splitedInputString = inputString.split(" ");
            
            String[] argus = Arrays.copyOfRange(splitedInputString, 1, splitedInputString.length);
            if(argus.length > 1) {               
                if ("start".equals(operation)) startDistribution(allCardsCombinations, playersCards, Integer.parseInt(argus[0]), Integer.parseInt(argus[1]));
            } else {               
                if ("get-cards".equals(operation)) showPlayerCards(playersCards, Integer.parseInt(argus[0]));
            }
            
           }
        reader.close();
    }
}