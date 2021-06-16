package game.Listas;

/**
 * Clase Nodo de la lista enlazada
 */
public class Nodo {
    public Nodo next;
    private Nodo previous;
    public Object fact;

    public Nodo(Object obj, Nodo ant, Nodo sig){
        next = sig;
        previous = ant;
        fact = obj;
    }

    public Nodo getNext(){
        return next;
    }

    public void setNext(Nodo next){
        this.next = next;
    }

    public Nodo getPrevious(){
        return previous;
    }

    public void setPrevious(Nodo previous){
        this.previous = previous;
    }

    public Object getFact(){
        return fact;
    }

    public void setFact( Object fact){
        this.fact = fact;
    }

    public Object getvalue(){
        return fact;
    }
}

