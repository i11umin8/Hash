package datastructures;

//import javax.annotation.NonNull;
import java.util.Iterator;

/**
 * Created by i11umin8 on 7/10/17.
 */
//implement list, iterable
public class SinglyLinkedList<T> implements Iterable<T> {

    private Link<T> start;
    private int size = 0;

    public SinglyLinkedList(T t){
        start = new Link<T>(t);
    }
    public SinglyLinkedList(Link<T> t){
        start = t;
    }
    public SinglyLinkedList(){}

    public void add(Link<T> link){
        if(start == null){
            start = link;
        } else if(start.getNextLink() == null){
            start.setNextLink(link);
        } else{
            add(start.getNextLink(),link);
        }
        computeSize();
    }

    public void add(T t){
        add(new Link<>(t));
    }

    private void add(Link<T> link, Link<T> linkToAdd){
        if(link.getNextLink() == null){
            link.setNextLink(linkToAdd);
        } else {
            add(link.getNextLink(),linkToAdd);
        }
    }

    public boolean contains(T t){
        for(T link : this){
            if(link.equals(t)){
                return true;
            }
        }
        return false;
    }

    public void append(SinglyLinkedList<T> list){
        add(list.start);
    }

    public void remove(T t){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            T next = it.next();
            if(next.equals(t)){
                it.remove();
            }
        }
        computeSize();
    }

    public int getSize(){
        return size;
    }

    private void computeSize() {
        size = 0;
        for(T t : this){
            size++;
        }
    }

//    @NonNull
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator<T>(this);
    }

    public static class SinglyLinkedListIterator<T> implements Iterator<T>{

        private SinglyLinkedList<T> list;
        private Link<T> currentElement;

        private boolean start;
        public SinglyLinkedListIterator(SinglyLinkedList<T> l){
            list = l;
            start = true;
        }

        public boolean hasNext() {
            //beginning of list
            if(start){
                if(list.start == null){
                    return false;
                } else {
                    return true;
                }
            } else if (currentElement.getNextLink() != null){
                return true;
            }
            return false;
        }

        public T next() {
            if(start){
                start = false;
                currentElement = list.start;
            } else{
                currentElement = currentElement.getNextLink();
            }
            return currentElement.data;
        }

        public void remove() {
            if(list.start == null)
                return;

            T toRemove = currentElement.data;
            if(list.start.data.equals(toRemove)){
                list.start = list.start.getNextLink();
                list.size--;
            } else{
                remove(list.start, toRemove);
            }
        }
        private void remove(Link<T> link, T toRemove){
            Link<T> next = link.getNextLink();
            if(next != null){
                if(next.data.equals(toRemove)){
                    link.setNextLink(next.getNextLink());
                }else{
                    remove(next,toRemove);
                }
            }
        }
    }

    private
    static class Link<T> {

        private Link<T> nextLink;
        private T data;

        public Link(T t){
            data = t;
        }

        public T getData(){
            return data;
        }

        public void setData(T t){
            this.data = t;
        }

        public Link<T> getNextLink(){
            return nextLink;
        }

        private void setNextLink(T t){
            nextLink = new Link<T>(t);
        }
        private void setNextLink(Link<T> t){
            nextLink = t;
        }

        public Iterator<T> iterator() {
            return null;
        }

        public boolean equals(Link<T> compareTo){
            return this.data.equals(compareTo.data) && this.nextLink.equals(compareTo.nextLink);
        }
    }
}


