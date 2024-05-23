package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.yrgo.domain.Product;

public interface ProductRepo extends JpaRepository<Product, String> {
}
