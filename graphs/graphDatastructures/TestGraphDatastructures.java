package graphDatastructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGraphDatastructures {

	@Test
	void testUnionFind() {
		UnionFind uf = new UnionFind(6);
		uf.make(0);
		uf.make(2);
		uf.make(1);
		uf.make(5);
		uf.make(4);
		uf.make(3);
		
		//check init
		assertEquals(uf.find(4),4);
		assertEquals(uf.find(0),0);
		assertEquals(uf.find(5),5);

		//check union
		uf.union(0, 1);
		uf.union(3, 5);
		uf.union(5, 0);
		assertEquals(uf.find(1),uf.find(5));
		uf.union(1, 1);
		uf.union(4, 3);
		assertEquals(uf.find(0),uf.find(5));
		assertNotEquals(uf.find(3),uf.find(2));
		assertEquals(uf.find(1),uf.find(3));

	}

}
