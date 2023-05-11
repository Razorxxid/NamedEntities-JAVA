package namedEntity;

import java.util.ArrayList;
import java.util.List;


public class EntityList {
	private List<NamedEntity> entityList;

    //list builder
    public EntityList() {
		super();
		this.entityList = new ArrayList<NamedEntity>();
	}
    // get size 
    public int size(){
        return this.entityList.size();
    }   
    // get entity
    public NamedEntity get(int i){
        return this.entityList.get(i);
    }
    // remove entity
    public void remove(int i){
        this.entityList.remove(i);
    }
    // index of named entity
    public int indexNE(NamedEntity ne){
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).eq(ne)) {
                return i;
            }
        }
        return -1;
    }

    // adder
    public void add(NamedEntity ne){
        // if it exists, increment frequency, else add it
        int indx = this.indexNE(ne);
        if (indx != -1) {
            this.entityList.get(indx).incFrequency();
        } else {
            this.entityList.add(ne);
        }
    }

}
     
