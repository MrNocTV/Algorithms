package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Linear.DoubleLinkedList;

public class TestDoubleLinkedList {

	@Test
	public void testInsert() {
		DoubleLinkedList<Integer> dl = new DoubleLinkedList<Integer>();
		dl.insertFirst(1);
		dl.insertFirst(2);
		dl.insertLast(3);
		assertEquals("2 1 3", dl.toString());
	}
	
	@Test
	public void testRemoveLast() {
		DoubleLinkedList<Integer> dl = new DoubleLinkedList<Integer>();
		dl.insertFirst(1);
		dl.insertFirst(2);
		dl.insertLast(3);
		dl.removeLast()
	}
}
