package dico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDico {
	

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void test()
	{
		fail("Not yet implemented");
	}
	
	@Test
	void testAddOneElementToEmptyDico()
	{
		assertEquals(1, dico.size());
	}

}
