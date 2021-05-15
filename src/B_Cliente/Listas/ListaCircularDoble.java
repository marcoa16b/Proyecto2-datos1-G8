package B_Cliente.Listas;

/**
 * Lista Circular Doble
 * @param <Object>
 */
public class ListaCircularDoble<Object>{
    private DoubleNode<Object> primero;
    private  DoubleNode<Object> ultimo;
    private int size;

    public ListaCircularDoble(){
        primero=null;
        ultimo=null;
    }

    public void insertNode(Object nodo2){
        DoubleNode nuevo=new DoubleNode();
        nuevo.setData(nodo2);
        if(primero==null){
            primero=nuevo;
            primero.next=primero;
            nuevo.prev=ultimo;
            ultimo=nuevo;
            size++;
        }else {
            ultimo.next=nuevo;
            nuevo.next=primero;
            nuevo.prev=ultimo;
            ultimo=nuevo;
            primero.prev=ultimo;
            size++;
        }
    }

    public void remove(int index) {
        if(index == 0) {
            primero.getPrev().setNext(primero.getNext());
            primero.getNext().setPrev(primero.getPrev());
            primero = primero.getNext();
            --size;
        } else if(index <= size/2) {
            DoubleNode<Object> current = primero;
            for(int c = 0; c <= index; c++) {
                current = current.getNext();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        } else if(index > size/2) {
            DoubleNode<Object> current = primero;
            for(int c = size; c >= index; c--) {
                current = current.getPrev();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
    }

    public Object get(int index) {
        if(index > size-1)
            return null;
        DoubleNode<Object> current = primero;
        for(int c = 0; c < index; c++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public void printt(){
        DoubleNode actual= new DoubleNode();
        actual=primero;
        do {
            System.out.println(actual.getData());
            actual=actual.next;
        }while (actual!=primero);
    }

    public int getSize(){
        return size;
    }

    /*    public void remove(int index) {
        if(index == 0 && index < size) {
            primero = primero.getNext();
            size--;
            return;
        }
        DoubleNode<Object> current = primero;
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
    }*/
    /*    public void BuscarNDoubleNode(Object nodo2){
        DoubleNode actual=new DoubleNode();
        actual=primero;
        do{
            if(actual.getData()==nodo2){
            }
            actual=actual.next;
        }while (actual!=primero);
    }*/
  /*    public void FindDoubleNode(Object node2){
        DoubleNode actual=new DoubleNode();
        actual=ultimo;
        boolean encontrado=false;
        do {
            if (actual.getData()==node2){
                encontrado=true;
            }
        }while (actual!=ultimo);
        if (encontrado==true){
            System.out.println("Nodo find");
        }else{
            System.out.println("No");
        }
    }*/
}
