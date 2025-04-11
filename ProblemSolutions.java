/******************************************************************
 *
 *   Abdul Shad / 272
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    public void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending) {
        int n = values.length;

        for (int i = 0; i < n - 1; i++) {
            int idx = i;

            // loop to find the index of the min/max element
            for (int j = i + 1; j < n; j++) {
                if (ascending) {
                    if (values[j] < values[idx]) {
                        idx = j;
                    }
                } else {
                    if (values[j] > values[idx]) {
                        idx = j;
                    }
                }
            }

            // swap values[i] with values[idx]
            int temp = values[i];
            values[i] = values[idx];
            values[idx] = temp;
        }
    }

    public void mergeSortDivisibleByKFirst(int[] values, int k) {
        if (k == 0) return;
        if (values.length <= 1) return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length - 1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right) {
        // merge the two sorted halves into a temp array
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, t = 0;

        // add numbers divisible by k from both sides first
        while (i <= mid) {
            if (arr[i] % k == 0) temp[t++] = arr[i];
            i++;
        }
        while (j <= right) {
            if (arr[j] % k == 0) temp[t++] = arr[j];
            j++;
        }

        // reset i and j to go through both halves again
        i = left;
        j = mid + 1;

        // add the rest (not divisible by k) in sorted order
        while (i <= mid) {
            if (arr[i] % k != 0) temp[t++] = arr[i];
            i++;
        }
        while (j <= right) {
            if (arr[j] % k != 0) temp[t++] = arr[j];
            j++;
        }

        // copy temp back to arr
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // sort the asteroids so planet can try smallest ones first
        Arrays.sort(asteroids);

        for (int a : asteroids) {
            if (mass >= a) {
                mass += a; // destroy asteroid and gain mass
            } else {
                return false; // too big to destroy
            }
        }

        return true; // all destroyed
    }

    public static int numRescueSleds(int[] people, int limit) {
        Arrays.sort(people); // sort people by weight

        int i = 0, j = people.length - 1; // two pointers
        int sleds = 0;

        while (i <= j) {
            // try to pair the lightest and heaviest person
            if (people[i] + people[j] <= limit) {
                i++; // both can go together
            }
            j--; // heavier person goes in any case
            sleds++; // used one sled
        }

        return sleds;
    }

}
