package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IDishRepository extends PagingAndSortingRepository<DishEntity, Long> {
    @Query("SELECT d FROM DishEntity d WHERE d.restaurantId.id = :restaurantId")
    Page<DishEntity> findAllByRestaurantId(@Param("restaurantId") Long restaurantId, Pageable pageable);

    Page<DishEntity> findAll(Pageable pageable);
}
