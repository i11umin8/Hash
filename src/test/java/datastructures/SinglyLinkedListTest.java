package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by i11umin8 on 7/10/17.
 */
public class SinglyLinkedListTest {

    private SinglyLinkedList<String> list;

    @Before
    public void init(){
        list = new SinglyLinkedList<String>();
        list.add("One");
        list.add("Two");
        list.add("Three");
    }

    @Test
    public void TestAddToList(){
        Assert.assertEquals(list.getSize(),3);
    }

    @Test
    public void TestListContains(){
        Assert.assertEquals(true,list.contains("One"));
        Assert.assertEquals(true,list.contains("Two"));
        Assert.assertEquals(true,list.contains("Three"));
        Assert.assertEquals(false,list.contains("Four"));
    }

    @Test
    public void TestDeletion(){
        list.remove("Two");
        Assert.assertEquals(true,list.contains("One"));
        Assert.assertEquals(false,list.contains("Two"));
        Assert.assertEquals(true,list.contains("Three"));
        init();
        list.remove("One");
        Assert.assertEquals(false,list.contains("One"));
        Assert.assertEquals(true,list.contains("Two"));
        Assert.assertEquals(true,list.contains("Three"));
        init();
        list.remove("Three");
        Assert.assertEquals(true,list.contains("One"));
        Assert.assertEquals(true,list.contains("Two"));
        Assert.assertEquals(false,list.contains("Three"));
    }

    @Test
    public void TestDeletionWithIterator(){
        Assert.assertEquals(true,list.contains("One"));
        Assert.assertEquals(true,list.contains("Two"));
        Assert.assertEquals(true,list.contains("Three"));
        Iterator<String> it = list.iterator();
        it.next();
        it.remove();
        Assert.assertEquals(false,list.contains("One"));
        Assert.assertEquals(true,list.contains("Two"));
        Assert.assertEquals(true,list.contains("Three"));
        init();
        it = list.iterator();
        it.next();
        it.next();
        it.remove();
        Assert.assertEquals(true,list.contains("One"));
        Assert.assertEquals(false,list.contains("Two"));
        Assert.assertEquals(true,list.contains("Three"));
        init();
        it = list.iterator();
        it.next();
        it.next();
        it.next();
        it.remove();
        Assert.assertEquals(true,list.contains("One"));
        Assert.assertEquals(true,list.contains("Two"));
        Assert.assertEquals(false,list.contains("Three"));
        init();
    }

    @Test
    public void TestAppending(){
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
        list2.add("Hello");
        list2.add("World");
        list2.add("Java is great");
        list.append(list2);
        Assert.assertEquals(list.getSize(),6);
        Assert.assertEquals(list.contains("One"),true);
        Assert.assertEquals(list.contains("Two"),true);
        Assert.assertEquals(list.contains("Three"),true);
        Assert.assertEquals(list.contains("Hello"),true);
        Assert.assertEquals(list.contains("World"),true);
        Assert.assertEquals(list.contains("Java is great"),true);
        Assert.assertEquals(list.contains("KanyeWestDidNothingWrong"),false);
    }
}
