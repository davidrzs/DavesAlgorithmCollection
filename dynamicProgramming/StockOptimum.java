
public class StockOptimum {

    public static void main(String[] args){
        //System.out.println(getMax(new int[] {1, 3, 1, 2},2));
        System.out.println(stockmax(new int[] {5 ,3,2}));
        System.out.println(stockmax(new int[] {5,4,3,4,5}));

    }
    static int stockmax(int[] prices) {
        int profit = 0;
        int nrBoughtSoFar = 0;
        int maxSoFar = 0;
        int pMax = 0;
        int nextMaxPtr = getMax(prices,0);
        for(int i = 0; i < prices.length; i++){
            if(i == nextMaxPtr){
                // sell all shares
                profit += (nrBoughtSoFar*prices[i])-maxSoFar;
                //System.out.println("we are at a max " + prices[i] +"  "+ Integer.toString((nrBoughtSoFar*prices[i])-maxSoFar));
                nextMaxPtr = getMax(prices,i+1);
                if(nextMaxPtr == i+1){
                    if(i+1 < prices.length){
                        i++;
                    }
                }
                nrBoughtSoFar = 0;
                maxSoFar = 0;
            } else{
                nrBoughtSoFar += 1;
                maxSoFar += prices[i];
                System.out.println("we are at " + i + " " + maxSoFar + " " + prices[i]);
            }


        }
        return profit;

    }

    static int getMax(int[] arr, int startPtr){
        int maxIndex = Integer.MIN_VALUE;
        int currMax = Integer.MIN_VALUE;
        if(startPtr == arr.length){
            return startPtr;
        }
        //System.out.println("sptr" + startPtr);
        for(int i = startPtr; i < arr.length; i++){
            if(arr[i] > currMax){
                currMax = arr[i];
                maxIndex = i;
            }
        }
        //System.out.println("maxindex: " + maxIndex);
        return maxIndex;
    }

}
