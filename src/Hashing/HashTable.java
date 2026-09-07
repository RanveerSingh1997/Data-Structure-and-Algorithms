package Hashing;

import java.util.ArrayList;
import java.util.Objects;

public class HashTable {
    private final int size = 7;
    private final HashNode[] dataMap;

    HashTable() {
        dataMap = new HashNode[7];
    }

    static void main() {
        HashTable hashTable = new HashTable();
        hashTable.set("nails", 100);
        hashTable.set("tile", 50);
        hashTable.set("lumber", 80);

        hashTable.set("bolts", 200);
        hashTable.set("screws", 140);
        hashTable.printTable();

        System.out.println(hashTable.get("lumber"));
        System.out.println(hashTable.get("bolts"));
        System.out.println(hashTable.get("bolt"));

        System.out.println(hashTable.keys());
    }

    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {
            System.out.println(i + ":");
            HashNode temp = dataMap[i];
            while (temp != null) {
                System.out.println("{" + temp.key + "=" + temp.value + "}");
                temp = temp.next;
            }
        }
    }

    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int asciiValue : keyChars) {
            hash = (hash + asciiValue * 23) % dataMap.length;
        }
        return hash;
    }

    public void set(String key, int value) {
        int index = hash(key);
        HashNode newNode = new HashNode(key, value);
        if (dataMap[index] == null) {
            dataMap[index] = newNode;
        } else {
            HashNode temp = dataMap[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public int get(String key) {
        int index = hash(key);
        HashNode temp = dataMap[index];
        while (temp != null) {
            if (Objects.equals(temp.key, key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return 0;
    }

    public ArrayList keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (HashNode hashNode : dataMap) {
            HashNode temp = hashNode;
            while (temp != null) {
                allKeys.add(temp.key);
                temp = temp.next;
            }
        }
        return allKeys;
    }

    private class HashNode {
        int value;
        String key;
        HashNode next;

        HashNode(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
