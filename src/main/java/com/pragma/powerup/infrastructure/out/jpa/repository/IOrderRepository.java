package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.domain.model.OrderState;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o WHERE o.orderState = :orderState AND o.restaurantId.id = :restaurantId")
    List<OrderEntity> findAllByOrderState(OrderState orderState, Long restaurantId);

    Optional<OrderEntity> findByClientIdAndOrderStateIn(Long clientId, List<OrderState> orderStateList);

}