class MedianFinder {

    private List<Integer> stream;

    public MedianFinder() {
        stream = new ArrayList<>();
    }
    
    public void addNum(int num) {
        int insertPoint = Collections.binarySearch(stream, num);
        
        // binarySearch returns a negative number if the element isn't found. 
        // The formula to convert it to the correct insertion index is -(insertionPoint + 1)
        if (insertPoint < 0) {
            insertPoint = -(insertPoint + 1);
        }
        
        // HEAVY LIFTING: This forces the JVM to physically shift all subsequent elements to the right!
        stream.add(insertPoint, num);
    }
    
    public double findMedian() {
        int n = stream.size();
        
        // If the size is odd, the median is just the exact middle element
        if (n % 2 == 1) {
            return stream.get(n / 2);
        } else {
            // If the size is even, the median is the average of the two middle elements
            // We cast to double to ensure we don't lose the decimal precision!
            return ((double) stream.get(n / 2 - 1) + stream.get(n / 2)) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */