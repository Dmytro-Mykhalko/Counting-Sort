import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int volume = Integer.MAX_VALUE - 2;
        int maxValue = 999;
        int[] nums = createAndFillArray(volume, maxValue);

        System.out.println("Before:");
        for (int num: nums) System.out.printf("%s,", num);

        customCountingSort(nums, 999);

        System.out.println("After:");
        for (int num: nums) System.out.printf("%s,", num);
    }

    // Changed the Counting Sort to avoid using extra array for sorted values
    private static void customCountingSort(int[] arr, int maxValue) {

        // initializing array that consist of counting the amount of repeating values [0; 999]
        int[] count = new int[maxValue + 1];

        // filling the COUNT ARRAY with the count of repeated values
        for (int k : arr) ++count[k];

        // filling arr with sorted values
        for (int i = 0, j = 0; i < arr.length && j < count.length ; i++, j++) {
            if (count[j] > 0) {
                arr[i] = j;
                --count[j];
                j--; // so in new iteration j will have the same value
            } else i--; // so we won't miss to fill arr[i] in the next iteration
        }
    }

    public static int[] createAndFillArray(int volume, int maxValue) {
        Random random = new Random();
        int[] result = new int[volume];

        for (int i = 0; i < result.length; i++)
            result[i] = random.nextInt(maxValue + 1);

        return result;
    }

    
}