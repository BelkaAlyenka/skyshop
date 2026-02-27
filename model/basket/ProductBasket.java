package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> mapProducts = new HashMap<>();

    // Метод добавления продукта
    public void addProduct(Product product, UUID id) {
        if (product == null || id == null) {
            return;
        }

        mapProducts.computeIfAbsent(id, key -> 0);
        mapProducts.put(id, mapProducts.get(id) + 1);
    }

    // Метод получения всех продуктов
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(new HashMap<>(mapProducts));
    }
}
