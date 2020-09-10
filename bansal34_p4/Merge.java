public class Merge {
    /* implement merge sort with auxiliary array, keep track of number of array accesses */

    //declare variables
    private int count;

    public Merge(){
        //initialize variable
        count = 0;
    }

    public int sort(int arr[])
    {
        sort(arr, 0, arr.length - 1);
        return count;
    }

    private void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            int m = (l+r)/2;
            sort(arr, l, m);
            sort(arr , m+1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int [n1];
        int R[] = new int [n2];

        for (int i=0; i<n1; ++i) {
            L[i] = arr[l + i];
            count += 2;
        }
        for (int j=0; j<n2; ++j) {
            R[j] = arr[m + 1 + j];
            count += 2;
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                count += 4;
                i++;
            }
            else
            {
                arr[k] = R[j];
                count += 4;
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            arr[k] = L[i];
            count += 2;
            i++;
            k++;
        }

        while (j < n2)
        {
            arr[k] = R[j];
            count += 2;
            j++;
            k++;
        }
    }
}

