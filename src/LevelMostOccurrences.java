import java.util.ArrayDeque;

/**
 * LevelMostOccurrences contains one static method for getting the level with most occurrences of a given Integer.
 */

public class LevelMostOccurrences {
    /**
     * Returns the level with most occurrences of a given Integer in a binary tree.
     * @param node - the root of the binary tree.
     * @param num - number to return level with most occurrences of.
     * @return level with most occurrences of a given Integer.
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        ArrayDeque<BinNode> elements = new ArrayDeque<>(); // elements per level
        ArrayDeque<Integer> finalCounter = new ArrayDeque<>(); // to be transformed to array, each index represents a level its values represent number of occurrences.
        int count = 0; // count how many times we encountered NUM at a certain level.
        elements.add(node);
        while(!elements.isEmpty()) // while level is not empty, count how many times our number appears in it.
        {
            int levelSize = elements.size();
            for(int i=0; i<levelSize; i++){
                BinNode current = elements.remove();
                if((Integer)current.getData() == num)
                    count++;
                if(current.getLeft() != null)
                    elements.add(current.getLeft());
                if(current.getRight() != null)
                    elements.add(current.getRight());
            }
            finalCounter.add(count); // Add the level count to the final counter array.
            count = 0;
        }
        Object[] finalCounterArray = finalCounter.toArray();
        // Find index of maximum value in our array.
        int maxIndex = 0;
        int maxVal = 0;
        for (int i=0; i<finalCounterArray.length; i++) {
            if ((int)finalCounterArray[i] > maxVal) {
                maxVal = (int)finalCounterArray[i];
                maxIndex = i;
            }
        }
        if(maxVal == 0) // Number is not found at any level of binary tree.
            return -1;
        return maxIndex;
    }
}