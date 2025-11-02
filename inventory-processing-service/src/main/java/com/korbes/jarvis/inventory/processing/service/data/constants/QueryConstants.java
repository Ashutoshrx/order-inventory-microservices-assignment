package com.korbes.jarvis.inventory.processing.service.data.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryConstants {
  public static final String FETCH_VALID_INVENTORY= """
          SELECT ib.*
          FROM inventory_batch ib
          INNER JOIN product p ON ib.product_id = p.product_id
          WHERE p.product_id = :productId
            AND ib.expiry_date > CURRENT_DATE
            AND ib.quantity_available >= :quantity
          ORDER BY ib.expiry_date ASC LIMIT 1;
          """;
}
