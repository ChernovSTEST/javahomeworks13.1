import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AviaSoulsTest {

    @Test
    public void testTicketTimeComparator() {
        Ticket ticket1 = new Ticket("A", "B", 100, 10, 20);
        Ticket ticket2 = new Ticket("C", "D", 200, 15, 25);
        Ticket ticket3 = new Ticket("E", "F", 150, 5, 15);

        TicketTimeComparator comparator = new TicketTimeComparator();

        // Проверяем, что ticket1 меньше чем ticket2
        assertEquals("ticket1 должен быть меньше ticket2", false, comparator.compare(ticket1, ticket2) < 0);

        // Проверяем, что ticket2 больше чем ticket1
        assertEquals("ticket2 должен быть больше ticket1", false, comparator.compare(ticket2, ticket1) > 0);

        // Проверяем, что ticket1 равен ticket3 (одинаковое время полета)
        assertEquals("ticket1 должен быть равен ticket3", 0, comparator.compare(ticket1, ticket3));
    }
    @Test
    public void testSearchAndSortBy() {
        Ticket ticket1 = new Ticket("A", "B", 100, 10, 20);
        Ticket ticket2 = new Ticket("C", "D", 200, 15, 25);
        Ticket ticket3 = new Ticket("E", "F", 150, 5, 15);

        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        TicketTimeComparator comparator = new TicketTimeComparator();

        // Ищем и сортируем по времени полета с помощью TicketTimeComparator
        Ticket[] result = aviaSouls.searchAndSortBy("A", "B", comparator);

        // Ожидаем, что результат будет содержать только ticket1
        Ticket[] expected = { ticket1 };

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testSearchMultipleTickets() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("A", "B", 100, 10, 12);
        Ticket ticket2 = new Ticket("A", "B", 150, 11, 13);
        Ticket ticket3 = new Ticket("A", "B", 200, 12, 14);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] result = aviaSouls.search("A", "B");

        Assert.assertEquals(3, result.length);
        Assert.assertArrayEquals(new Ticket[]{ticket1, ticket2, ticket3}, result);
    }

    @Test
    public void testSearchSingleTicket() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("A", "B", 100, 10, 12);
        Ticket ticket2 = new Ticket("A", "C", 150, 11, 13);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] result = aviaSouls.search("A", "B");

        Assert.assertEquals(1, result.length);
        Assert.assertArrayEquals(new Ticket[]{ticket1}, result);
    }

    @Test
    public void testSearchNoTickets() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("A", "C", 100, 10, 12);
        Ticket ticket2 = new Ticket("A", "D", 150, 11, 13);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] result = aviaSouls.search("A", "B");

        Assert.assertEquals(0, result.length);
        Assert.assertArrayEquals(new Ticket[0], result);
    }
}
