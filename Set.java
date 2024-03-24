public class Set<T>{
        private LinearNode<T> setStart;
        private int size=0;

    //Initialize setStart to null
    public Set(){
        setStart = null;
    }

    //Create a new node and add the new node to linked list
    public void add(T element){
        LinearNode<T> newNode=new LinearNode<T>(element);
        newNode.setNext(setStart);
        setStart=newNode;
        size++;
    }

    //Return the items in the linked list
    public int getLength(){
        return size;
    }

    //Return the element stored in linked list
    public T getElement(int i){
        if (i<0 || i>=size){
            throw new IndexOutOfBoundsException("Index wrong");
        }
        LinearNode<T> temp = setStart;
        for (int a = 0;a<i;a++){
            temp=temp.getNext();
        }
        return temp.getElement();
    }

    //Return true or false while check linked list
    public boolean contains(T element){
        LinearNode<T> temp = setStart;
        while (null != temp.getNext() && temp.getElement() != element) {
            temp = temp.getNext();
        }
        if (temp.getElement() == element) {
            return true;
        }
        return false;
    }

    //Return string
    public String toString(){
        String storeString = "";
        LinearNode<T> temp = setStart;
        for (int a = 0;a<size;a++){
            storeString=storeString+temp.getElement()+" ";
            temp=temp.getNext();
        }
        return storeString;
    }

}

  


