package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.CustomerOrder;

public interface CustomerOrderRepo  extends JpaRepository<CustomerOrder, Long>{
}
