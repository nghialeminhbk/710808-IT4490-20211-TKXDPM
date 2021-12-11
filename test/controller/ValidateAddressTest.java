package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateAddressTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"Hanoi VietNam, true",
			"102319 HaNoi VietNam, true",
			"PhuTho VietNam, true"
	})
	public void test(String address, boolean expected) {
		boolean isValided = placeOrderController.validateAddress("Hanoi VietNam");
		assertEquals(true, isValided);
	}

}
