import java.util.Random;

/*
* I chose to use Counting Sort because it has linear complexity
* and is perfectly suitable to sort objects according to the keys that are small numbers (n > k)
*
* In our task we had an int array of 2,147,483,645 length with randomly distributed values from 0 to 999,
*
* */
public class CountingSort {

    /*
    * Changed the Counting Sort to avoid using extra array for sorted values
    * because there would be huge data usage.
    *
    * I have added original Counting Sort algorithm below
    * */
    private static void customSort(int[] arr, int maxValue) {
        int[] count = new int[maxValue + 1];

        for (int k : arr) ++count[k];

        for (int i = 0, j = 0; i < arr.length && j < count.length ; i++, j++) {
            if (count[j] > 0) {
                arr[i] = j;
                --count[j];
                j--; // so in new iteration j will have the same value
            } else i--; // so we won't miss to fill arr[i] in the next iteration
        }
    }

    /*
    * Default implementation of Counting Sort.
    * As a disadvantage it uses a huge amount of data because of creating an output array
    * that should contain the same amount of elements as an inout one
    * */
    public static void sort(int[] input, int maxValue) {
        int[] output = new int[input.length];
        int[] count = new int[maxValue + 1];

        for (int k : input) ++count[k];

        for (int i = 0; i < count.length - 1; i++)
            count[i + 1] += count[i];

        for (int i = 0; i < output.length ; i++) {
            output[count[input[i]] - 1] = input[i];
            --count[input[i]];
        }

        for (int i = 0; i < output.length; i++)
            input[i] = output[i];
    }

    public static void main(String[] args) {
        int volume = Integer.MAX_VALUE - 2;
        int maxValue = 999;
        int[] nums = createAndFillArray(volume, maxValue);
        System.out.printf("Array is sorted: %s%n", isSorted(nums));

        customSort(nums, maxValue);
        System.out.printf("Array is sorted: %s", isSorted(nums));
    }

    private static int[] createAndFillArray(int volume, int maxValue) {
        Random random = new Random();
        int[] result = new int[volume];

        for (int i = 0; i < result.length; i++)
            result[i] = random.nextInt(maxValue + 1);

        return result;
    }

    private static boolean isSorted(int[] arr) {
        if (arr == null)
            throw new IllegalArgumentException("Input array can't be null");

        for (int i = 1; i < arr.length; i++)
            if(arr[i] < arr[i-1])
                return false;

        return true;
    }
}