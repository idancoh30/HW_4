import java.util.ArrayDeque;

//hello idan

public class LevelMostOccurrences {
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        ArrayDeque<BinNode> elements = new ArrayDeque<>(); // elements per level
        ArrayDeque<Integer> finalCounter = new ArrayDeque<>(); // to be transformed to normal array with toArray();
        int count = 0; // count how many times we encountered GOOD elements
        elements.add(node);
        while(!elements.isEmpty())
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
            finalCounter.add(count);
            count = 0;
        }
        Object[] finalCounterArray = finalCounter.toArray();
        int maxIndex = 0;
        int maxVal = 0;
        for (int i=0; i<finalCounterArray.length; i++) {
            if ((int)finalCounterArray[i] > maxVal) {
                maxVal = (int)finalCounterArray[i];
                maxIndex = i;
            }
        }
        if(maxVal == 0)
            return -1;
        return maxIndex;
    }
}
