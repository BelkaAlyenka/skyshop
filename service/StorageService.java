package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService(Map<UUID, Article> mergedMap) {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();

        testProducts();
    }

    public Map<UUID, Product> getAllProducts() {
        return products;
    }

    public Map<UUID, Article> getAllArticles() {
        return articles;
    }

    public Collection<Searchable> getAllSearchable() {
        return Stream.of(
                        products.values().stream(),
                        articles.values().stream()
                )
                .flatMap(stream -> stream)
                .collect(Collectors.toList());
    }

    private void testProducts() {
        UUID firstProductId = UUID.randomUUID();
        Product firstProduct = new SimpleProduct(firstProductId, "смартфон", 13500);
        products.put(firstProductId, firstProduct);

        UUID secondProductId = UUID.randomUUID();
        Product secondProduct = new FixPriceProduct(secondProductId, "наушники");
        products.put(secondProductId, secondProduct);

        UUID thirdProductId = UUID.randomUUID();
        Product thirdProduct = new DiscountedProduct(thirdProductId,"весы", 600, 20);
        products.put(thirdProductId, thirdProduct);

        UUID firstArticleId = UUID.randomUUID();
        Article firstArticle = new Article(firstArticleId, "про весы", "С помощью весов можно взвешивать не только твердые и сыпучие продукты, но и любые жидкости. Прибор умеет измерять объем воды или молока, а также обладает функцией тарирования");
        articles.put(firstArticleId, firstArticle);

        UUID secondArticleId = UUID.randomUUID();
        Article secondArticle = new Article(secondArticleId,"беспроводные наушники", "«Настоящие беспроводные» наушники — это Bluetooth-модели, которые работают без какого-либо провода между наушниками и источником звука (смартфоном, ноутбуком и так далее)");
        articles.put(secondArticleId, secondArticle);
    }
}
