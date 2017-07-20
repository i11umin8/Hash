package datastructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by i11umin8 on 7/10/17.
 */
public class HashTest {

    private Hash<String,String> hash;
    private Hash<Object,Object> objHash;
    SinglyLinkedList<String> list;
    private List<String> keys;
    private List<String> vals;

    @Before
    public void init(){
        hash = new Hash<String,String>();
        objHash = new Hash<Object,Object>();
        hash.put("Test","One");
        hash.put("This","Two");
        hash.put("Hash","Three");
        list = new SinglyLinkedList<>();
        objHash.put("Key", list);
        list.add("Test");
        keys = new ArrayList<String>();
        keys.add("Test");
        keys.add("This");
        keys.add("Hash");
        vals = new ArrayList<>();
        vals.add("One");
        vals.add("Two");
        vals.add("Three");
    }

    @Test
    public void testGet(){
        Assert.assertEquals(hash.get("Test"),"One");
        Assert.assertEquals(hash.get("This"),"Two");
        Assert.assertEquals(hash.get("Hash"),"Three");
        Assert.assertEquals(objHash.get("Key").equals(list),true );
    }

    @Test
    public void testPut(){
        String value = hash.put("testData","testPut");
        Assert.assertEquals(value, "testPut");
        Assert.assertEquals(hash.get("testData"), "testPut");
    }

    @Test
    public void testRemove(){
        hash.remove("Test","One");
        hash.remove("Hash","Three");
        objHash.remove("Key");
        Assert.assertEquals(hash.get("Test"),null);
        Assert.assertEquals(hash.get("This"),"Two");
        Assert.assertEquals(hash.get("Hash"),null);
        Assert.assertEquals(objHash.get("Key"),null);
    }

    @Test
    public void testContainsKey(){
        Assert.assertEquals(hash.containsKey("Test"),true);
        Assert.assertEquals(hash.containsKey("This"),true);
        Assert.assertEquals(hash.containsKey("Hash"),true);
        Assert.assertEquals(objHash.containsKey("Key"),true);
    }

    @Test
    public void testContainsValue(){
        Assert.assertEquals(hash.containsValue("One"), true );
        Assert.assertEquals(hash.containsValue("Two"),true);
        Assert.assertEquals(hash.containsValue("Three"),true);
        Assert.assertEquals(objHash.containsValue(list),true);
    }

    @Test
    public void testIterator(){
        Iterator<Hash.KVPair<String, String>> it = hash.iterator();
        Map.Entry<String, String> kvPair;
        int count = 0;
        while(it.hasNext()){
            kvPair = it.next();
            Assert.assertTrue(keys.contains(kvPair.getKey()));
            Assert.assertTrue(vals.contains(kvPair.getValue()));
            count++;
        }
        Assert.assertEquals(count,hash.size());
    }

    @Test
    public void testEntrySet(){
        HashSet<Map.Entry<String,String>> set = (HashSet<Map.Entry<String,String>>)hash.entrySet();
        int count = 0;
        for(Map.Entry<String, String> element : set){
            Assert.assertTrue(keys.contains(element.getKey()));
            Assert.assertTrue(vals.contains(element.getValue()));
            count++;
        }
        Assert.assertEquals(count,hash.size());
    }

    @Test
    public void testValues(){
        HashSet<String> set = (HashSet<String>)hash.values();
        int count = 0;
        for(String element : set) {
            Assert.assertTrue(vals.contains(element));
            count++;
        }
        Assert.assertEquals(count,hash.size());
    }

    @Test
    public void testKeys(){
        HashSet<String> set = (HashSet<String>)hash.keySet();
        int count = 0;
        for(String element : set) {
            Assert.assertTrue(keys.contains(element));
            count++;
        }
        Assert.assertEquals(count,hash.size());
    }

    @Test
    public void testClear(){
        hash.clear();
        Assert.assertEquals(0,hash.size());
    }

    @Test
    public void testPutAll(){
        HashMap<String,String> map = new HashMap<>();
        map.put("Map1","Entry");
        map.put("Map2","Entry");
        hash.putAll(map);
        Assert.assertEquals(hash.get("Map1"), "Entry");
        Assert.assertEquals(hash.get("Map2"), "Entry");
        Assert.assertEquals(hash.size(),5);
    }
}
