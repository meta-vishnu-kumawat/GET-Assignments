public class Search {
    
    public int linearSearch(int arr[] ,int key,int index){
        if(index +1==arr.length && arr[index]!=key){
            return -1;
        }
        if(arr[index]==key){
            return index;
        }
        return linearSearch(arr, key, index+1);
    }

    public int binarySearch(int arr[],int start,int end,int key){
        if (start<=end) {
            int mid = (start+end)/2;
            if(arr[mid]==key){
                return mid;
            }
            else if(arr[mid]>key){
                end = mid-1;
            }
            else{
                start = mid+1;
            }
            return binarySearch(arr, start, end, key);
        }
        return -1;
     
    }
    
}