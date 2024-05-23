package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.yrgo.domain.OrderProduct;

public interface OrderProductRepo extends JpaRepository<OrderProduct, Long> {
}
