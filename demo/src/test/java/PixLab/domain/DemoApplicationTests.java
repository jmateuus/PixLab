package PixLab.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DemoApplicationTests {

	@Test
	void shouldCreatePixKeyWithExpectedFields() {
		PixKey pixKey = new PixKey("id-1", PixKeyType.RANDOM, "f2d8-1234", "Maria");

		assertEquals("id-1", pixKey.getId());
		assertEquals(PixKeyType.RANDOM, pixKey.getTipo());
		assertEquals("f2d8-1234", pixKey.getValor());
		assertEquals("Maria", pixKey.getTitular());
	}

	@Test
	void shouldRejectPixKeyWithBlankValue() {
		assertThrows(IllegalArgumentException.class, () -> new PixKey("id-1", PixKeyType.CPF, " ", "Maria"));
	}
}
