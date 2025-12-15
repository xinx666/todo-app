import org.example.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {
    private TodoList t;

    @BeforeEach
    void setUp() {
        t = new TodoList();
    }

    @Test
    void addAndList() {
        t.add(" task1 ");
        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().getFirst());
    }

    @Test
    void remove() {
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        t.add(" ");
        assertEquals(0, t.size());
    }

    @Test
    void testClear() {
        t.add("Task 1");
        t.add("Task 2");
        t.clear();
        assertEquals(0, t.size(), "List should be empty after clear");
    }

    @Test
    void testMarkDone() {
        t.add("Task 1");
        assertTrue(t.markDone(0), "Marking valid task should return true");
        assertEquals("[DONE] Task 1", t.getAll().getFirst(), "Task should be marked as done");
        assertFalse(t.markDone(1), "Marking invalid index should return false");
    }

    @Test
    void testSearch() {
        t.add("Buy milk");
        t.add("Write code");
        List<String> results = t.search("milk");
        assertEquals(1, results.size(), "Search should find one task");
        assertEquals("Buy milk", results.get(0), "Search should return correct task");
        results = t.search("MILK");
        assertEquals(1, results.size(), "Search should be case-insensitive");
        results = t.search("nonexistent");
        assertEquals(0, results.size(), "Search for nonexistent string should return empty list");
        results = t.search("");
        assertEquals(0, results.size(), "Search for empty string should return empty list");
        results = t.search(null);
        assertEquals(0, results.size(), "Search for null should return empty list");
    }
}