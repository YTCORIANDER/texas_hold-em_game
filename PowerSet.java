public class PowerSet<T> {
    private Set<T>[] set;

    public PowerSet(T[] elements) {
        int result = (int) Math.pow(2, elements.length);
        set = new Set[result];
        for (int i = 0; i < result; i++) {
            String binary = Integer.toBinaryString(i);
            binary = String.format("%" + elements.length + "s", binary).replace(' ', '0');
            set[i] = new Set<>();
            for (int j = 0; j < elements.length; j++) {
                if (binary.charAt(j) == '1') {
                    //Store set
                    set[i].add(elements[j]);
                }
            }
        }
    }

    //Return yhe items in array
       public int getLength() {
        return set.length;
    }

    //Return Set stored at array
    public Set<T> getSet(int i) {
        return set[i];
    }
}


