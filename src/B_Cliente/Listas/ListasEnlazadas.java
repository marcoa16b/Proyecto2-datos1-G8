package B_Cliente.Listas;



/**
 * Listas enlazadas
 * @param <Object>
 */
public class ListasEnlazadas<Object>{
    private Nodo head;
    private Nodo last;
    private int size;

    // constructor
    public ListasEnlazadas(){
        head = null;
        last = null;
        size = 0;
    }

    public Object gett(int index){
        int cont = 0;
        Nodo temp = head;
        while (cont < index){
            //System.out.println(cont +" "+index);
            //System.out.println(temp );
            //System.out.println(index +" "+ (getSize()-1) );
            if (index <= getSize()-1) {
                temp = temp.getNext();
                cont++;
            }
            else {
                index--;
            }


        }
        return (Object) temp.getvalue();

    }

    public Nodo gettt(int index){
        int cont = 0;
        Nodo temp = head;
        while (cont < index){
            temp = temp.getNext();
            cont++;
        }
        return temp;
    }

    public void insertHead(Object obj) {
        if (head == null) {
            head = new Nodo(obj,null,null);
            last = head;
        } else {
            Nodo temp = head;
            Nodo neww = new Nodo(obj,null,head);
            neww.setNext(temp);
            head = neww;
        }
        size++;
    }

    public void insertLast(Object obj){
        if (head == null){
            last = new Nodo(obj, null, null);
            head = last;
        } else {
            Nodo neww = new Nodo(obj, null, head);
            last.setPrevious(neww);
            last = neww;
        }
        size++;
    }

    public void insertMiddle(Object obj, int index){
        int cont = 0;
        Nodo temp = head;
        if (head == null){
            insertHead(obj);
        } else if (index >= size-1) {
            insertLast(obj);
        } else {
            while (cont < index) {
                temp = temp.getNext();
                cont++;
            }
        }
    }

    public void delete(int index){
        if (index == 0) {
            head = head.getNext();
        } else {
            int cont = 0;
            Nodo temp = head;
            while(cont < index-1){
                //System.out.println(index);
                //System.out.println(temp.getNext());
                temp = temp.getNext();
                cont++;
            }
            temp.setNext(temp.getNext().getNext());
        }
        size--;
    }

    public boolean estaVacia(){
        return (head == null)?true:false;
    }

    public int getSize(){
        return size;
    }
}
