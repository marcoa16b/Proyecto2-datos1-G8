package B_Cliente.Listas;

/**
 * Lista doblemente enlazada
 * @param <Object>
 */
public class DoubleLinkedList<Object>{
    private DoubleNode<Object> head;
    private int size;

    public DoubleLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Insertar valor
     * @param value
     */
    public void add(Object value) {
        DoubleNode<Object> newNode = new DoubleNode<Object>();
        newNode.setData(value);
        if(head == null) {
            head = newNode;
            size++;
            return;
        }
        DoubleNode<Object> current = head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        newNode.setPrev(current);
        current.setNext(newNode);
        size++;
    }

    /**
     * Remove aliens
     * @param index
     */
    public void remove(int index) {
        if(index == 0 && index < size) {
            head = head.getNext();
            size--;
            return;
        }
        DoubleNode<Object> current = head;
        int counter = 0;
        while(counter < index-1 && current.getNext() != null) {
            current = current.getNext();
            counter++;
        }
        if(counter == size-2) {
            current.setNext(null);
            size--;
            return;
        } else {
            current.setNext(current.getNext().getNext());
            size--;
            return;
        }
    }

    /**
     * Método get
     * @param index
     * @return
     */
    public Object get(int index) {
        if(index > size-1)
            return null;
        DoubleNode<Object> current = head;
        for(int c = 0; c < index; c++) {
            current = current.getNext();
        }
        return current.getData();
    }
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Método size
     * @return
     */
    public int size() {
        return size;
    }
    public void swap(int index1, int index2) {
        Object d1 = this.get(index1);
        Object d2 = this.get(index2);
        DoubleNode<Object> current = head;
        for(int c = 0; c < size; c++) {
            if(this.get(c) == d1) {
                current.setData(d2);
            } else if(this.get(c) == d2) {
                current.setData(d1);
            }
            current = current.getNext();
        }
    }
}
