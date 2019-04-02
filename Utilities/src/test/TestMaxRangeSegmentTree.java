package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Tree.MaxRangeSegmentTree;

public class TestMaxRangeSegmentTree {

	@Test
	public void testGetMax() {
		int[] input = { 1, 3, 5, 7, 9, 11 };
		MaxRangeSegmentTree mrst = new MaxRangeSegmentTree(input);
		int max13 = mrst.getMax(1, 3);
		assertEquals(7, max13);
		
		mrst.updateValue(1, 8);
		max13 = mrst.getMax(1, 3);
		assertEquals(8, max13);
	}
}
