import java.util.*;

public class Counter {
    private PowerSet<Card> cardps;
    private Card Starter;

    private int point=0;

    //Initialize the starter and use the PowerSet constructor
    public Counter(Card[] hand, Card starter){
        Starter = starter;
        cardps = new PowerSet<Card>(hand);
    }

    //Calculate points
    public int countPoints(){
        for (int i = 0; i < cardps.getLength(); i++) {
            countFifteens(cardps.getSet(i));
            countPairs(cardps.getSet(i));
            if(cardps.getSet(i).getLength()>=3){
                countRuns(cardps.getSet(i));
            }
            if (cardps.getSet(i).getLength()==5) {
                countFlush(cardps.getSet(i));
                countHisKnobs(cardps.getSet(i));
            }
        }
      return point;
    }
    
    //Two cards with the same label are a pair and it is worth 2 points
    private int countPairs(Set cards) {
        Map<Integer, Integer> rankCount = new HashMap<Integer, Integer>();
        for (int i = 0; i < cards.getLength(); i++) {
            Card card = (Card) cards.getElement(i);
            int key = card.getRunRank();
            if (rankCount.containsKey(key)) {
                int count = rankCount.get(key);
                if (count == 1) {
                    point++;
                } else if (count == 2) {
                    point++;
                    point += 2;
                }
                rankCount.put(key, count + 1);
            } 
                rankCount.put(key, 1);
            }
        return point;
    }
    
    //Three or more cards with consecutive ranks
    private int countRuns(Set cards){
        int longestRun = 0;
        int runLength = cards.getLength();
        if (isRun(cards)){
            if (runLength > longestRun) {
                longestRun = runLength;
                point = runLength;
            } else if (runLength == longestRun) {
                point += runLength;    
            }
        }
        point += longestRun;
        return point;
    }

    //Any combination that add up to 15 will score 2 points
    private int countFifteens(Set cards){
        int sum=0;
        for (int i = 0; i < cards.getLength(); i++) {
            Card card = (Card) cards.getElement(i);
            sum=sum+ card.getFifteenRank();
        }
        if (sum==15){
            point=point+2;
        }
        return point;
    }

    //If all 4 cards in the hand have the same suit, it is a flush worth 4 or 5 points
    private int countFlush(Set cards) {
        Card temp = (Card) cards.getElement(4);
        String tempSuit = temp.getSuit();
        for (int i = 1; i < cards.getLength(); i++) {
            Card card = (Card) cards.getElement(i);
            if (card.getSuit()!=tempSuit){
                return point;
            }
          }

        point = point+4;
        if (Starter.getSuit().equals(tempSuit)){
            point = point+1;
        }
        return point;
        
    }

    // If there is a Jack within the hand and its suit matches the suit of the starter card and is worth 1 point.
    private int countHisKnobs(Set cards){
        for (int i = 1; i < cards.getLength(); i++) {
            Card card = (Card) cards.getElement(i);
            if (card.getLabel()=="J"){
                if(card.getSuit()== Starter.getSuit()){
                    point++;
                }
            }
        }
        return point;
    }





    private boolean isRun (Set<Card> set) {
        // In this method, we are going through the given set to check if it constitutes a run of 3 or more
        // consecutive cards. To do this, we are going to create an array of 13 cells to represent the
        // range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
        // each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
        // An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
        // contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
        // Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.

        int n = set.getLength();

        if (n <= 2) return false; // Run must be at least 3 in length.

        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.

        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank()-1] += 1;
        }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) maxStreak = streak;
            } else {
                streak = 0;
            }
        }
        //System.out.println("maxStreak="+maxStreak);
        if (maxStreak == n) { // Check if this is the maximum streak.
            return true;
        } else {
            return false;
        }

    }

}