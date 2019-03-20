public class ArrayTraversal {

    static void traverse1(int[] numbers){
        for(int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }

    static void traverse2(int[] numbers) {
        for (int num:numbers) {
            System.out.println(num);
        }
    }

    static void traverseBackward(int[] numbers) {
        for(int i=numbers.length - 1; i>=0; i--){
            System.out.println(numbers[i]);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {4, 8, 15, 16, 23, 42};
        traverseBackward(numbers);
    }
}
