import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.SecureDirectoryStream;


public class BloomFilter<String> {
    //Declares global variable data (the array of longs that makes up the bloom filter)
    long[] data;

    public BloomFilter() {
        //Constructor, initializes data
        data = new long[1024];
    }

    public void main(String x) {
        //Main method calls add() & mightContain()
        add(x);
        mightContain(x);
    }

    public static long getLowOrder(int bin) {
        //Creates the low order hash function
        long mask = (1L << 16) - 1;
        long h1 = (bin & mask);
        return h1;
    }

    public static long getHighOrder(int bin){
        //Creates the high order hash function
        long h2 = bin >>> 16;
        return h2;
    }

    public void add(String x){
        //Adds an element to the Bloom filter
        //Converts the string to hashcode using the built in method .hashCode()
        int bin = x.hashCode();
        //Creates the low order and high order hash functions by calling the earlier methods
        long h1 = getLowOrder(bin);
        long h2 = getHighOrder(bin);
        //Finds the long we want to add to by dividing by 64 then using modulo to find the specific bit
        int longIndex = ((int)h1/64);
        int indexWithinLong = ((int)h1%64);
        int longIndex2 = ((int)h2/64);
        int indexWithinLong2 = ((int)h2%64);
        //Uses bitwise OR to turn on the two new bits
        data[longIndex] = data[longIndex] | (1L << indexWithinLong);
        data[longIndex2] = data[longIndex2] | (1L << indexWithinLong2);
    }

    public boolean mightContain(String x){
        //Checks if the two bits for the string x are turned on to see if x might be a bad site
        //Converts the string to hashcode using the built in method .hashCode()
        int bin = x.hashCode();
        long h1 = getLowOrder(bin);
        long h2 = getHighOrder(bin);
        //Finds the long we want to add to by dividing by 64 then using modulo to find the specific bit
        int longIndex = ((int)h1/64);
        int indexWithinLong = ((int)h1%64);
        int longIndex2 = ((int)h2/64);
        int indexWithinLong2 = ((int)h2%64);
        //Checks if the specific bit within the longs for hash 1 and hash 2 are turned on
        if (data[longIndex] == (data[longIndex] | (1L << indexWithinLong))){
            if (data[longIndex2] == (data[longIndex2] | (1L << indexWithinLong2))){
                return true;
            }
        }
        return false;
    }

    public int trueBits() {
        //Checks how many bits are true in data (the table of longs)
        int counter = 0;
        for (int i = 0; i < data.length ; i++){
            //Uses the built in method .bitCount() to see how many bits are true in each long
            counter += Long.bitCount(data[i]);
        }
        return counter;
    }
}