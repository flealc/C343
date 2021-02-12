import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;

class WordsTest {

    // write your own tests timing how long it takes to build a hashset with a Listof String and then a Listof Word
    // refer to Lab 3 tests if you do not remember how to time tests using methods of the Instant & Duration class

    Duration HashCodeDuration() {
        Instant start = Instant.now();
        HashSet<Word> myList = new HashSet(Words.slist);

        Instant end = Instant.now();
        return Duration.between(start, end);
    }


    Duration MyHashFunctDuration() {
        Instant start = Instant.now();
        HashSet<Word> myList = new HashSet(Words.wlist);

        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void timeW () {
        Duration d1 = HashCodeDuration();
        System.out.printf("Initializing HashSet from collection slist using the default hashCode; time in ms = %d%n",
                d1.toMillis());

        Duration d2 = MyHashFunctDuration();
        System.out.printf("Initializing HashSet from collection wlist using my randomly created hash funct; time in ms = %d%n",
                d2.toMillis());


    }
    }


