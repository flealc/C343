import java.util.Hashtable;

public class Trie implements Words {
    private boolean endsHere;
    private final Hashtable<Character,Trie> children;

    Trie () {
        this.endsHere = false;
        this.children = new Hashtable<>();
    }

    Hashtable<Character, Trie> getChildren () {return this.children;}

    /**
     * Inserts the string s in the current trie.
     */
    void insert(String s) {
        // TODO
    }

    /**
     * Checks whether the string s is a full word
     * stored in the current trie.
     */
    public boolean contains(String s) {
        if (s.length() == 0) {
            return this.endsHere;
        }
        char first = s.charAt(0); // (first string)
        String rest = s.substring(1); // (rest string)
        Trie t = children.get(first);
        return children.containsKey(first) && t.contains(rest);
    }

    /**
     * Checks whether the string s is a prefix
     * of at least one word in the current trie.
     */
    public boolean possiblePrefix (String s) {
        if (s.length() == 0) {
            return true;
        }
        char first = s.charAt(0); // (first string)
        String rest = s.substring(1); // (rest string)
        Trie t = children.get(first); // this is fine, if it is null then and will short-circuit
        return children.containsKey(first) && t.possiblePrefix(rest); // this, null.contains(cdr) would never happen
    }

    public String toString () {
        return children.toString();
    }
}
