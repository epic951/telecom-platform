package com.epic951.utilities;

import com.epic951.data.entities.Operator;
import com.epic951.data.entities.Product;
import com.epic951.data.entities.TelecomService;

public class TestUtilities {

	public static Product createTestProduct(int productId, String productName, String productDescription, int minPrice,
			int maxPrice, String imageUrl, float rating) {
		Product testProduct = new Product();
		testProduct.setProductName(productName);
		testProduct.setProductId(productId);
		testProduct.setProductDescription(productDescription);
		testProduct.setMinPrice(minPrice);
		testProduct.setMaxPrice(maxPrice);
		testProduct.setImageUrl(imageUrl);
		testProduct.setRating(rating);
		return testProduct;
	}

	public static Operator createTestOperator(int operatorId, String operatorName, String operatorCountry,
			String imageUrl, float rating) {
		Operator testOperator = new Operator();
		testOperator.setOperatorId(operatorId);
		testOperator.setOperatorCountry(operatorCountry);
		testOperator.setOperatorName(operatorName);
		testOperator.setImageUrl(imageUrl);
		testOperator.setRating(rating);
		return testOperator;
	}

	public static TelecomService createTestTelecomService(int telecomServiceId, String telecomServiceName,
			String operatorName, boolean telecomServiceType, int operatorId, int operatorServiceId,
			int operatorPackageId, String imageUrl, float rating) {
		TelecomService testTelecomService = new TelecomService();
		testTelecomService.setTelecomServiceId(telecomServiceId);
		testTelecomService.setOperatorName(operatorName);
		testTelecomService.setTelecomServiceName(telecomServiceName);
		testTelecomService.setTelecomServiceType(telecomServiceType);
		testTelecomService.setOperatorPackageId(operatorPackageId);
		testTelecomService.setOperatorServiceId(operatorServiceId);
		testTelecomService.setOperatorId(operatorId);
		testTelecomService.setImageUrl(imageUrl);
		testTelecomService.setRating(rating);
		return testTelecomService;
	}
}
