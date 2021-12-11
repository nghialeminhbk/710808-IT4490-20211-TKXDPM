package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateNameTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"Le Minh Nghia, true",
			"Tran Van Nghia, true",
			"Le Tran Minh Nghia, true"
	})
	public void test(String name, boolean expected) {
		boolean isValided = placeOrderController.validateName("Le Nghia");
		assertEquals(true, isValided);
	}

}
