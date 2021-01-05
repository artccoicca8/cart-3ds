package com.alignet.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Cart3dsApplicationTests {

	@Test
	public void contextLoads() {
		int arreglo[] = { 1, 2, 3, 4, 5, 6 };
		for (int i = 0; i < arreglo.length; i++) {
			System.out.println(i);
		}

	}

}
