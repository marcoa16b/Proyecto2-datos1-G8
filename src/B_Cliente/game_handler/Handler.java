package B_Cliente.game_handler;

import B_Cliente.objects.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tempObject;

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public int getindexObject(GameObject obj){
        return object.indexOf(obj);
    }

    public GameObject getObject(GameObject obj){
        return object.get(getindexObject(obj));
    }

}
