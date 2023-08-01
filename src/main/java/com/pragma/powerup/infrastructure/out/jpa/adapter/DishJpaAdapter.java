package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.exception.DishNotFoundException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public DishModel saveDish(DishModel dishModel) {
        DishModel dish = dishModel;

        dish.setActive(true);

        dishRepository.save(dishEntityMapper.toEntity(dish));
        return dish;
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> dishEntityList = (List<DishEntity>) dishRepository.findAll();

        if (dishEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return dishEntityMapper.toDishModelList(dishEntityList);
    }

    @Override
    public DishModel getDish(Long dishId) {
        return dishEntityMapper.toDishModel(dishRepository.findById(dishId).orElseThrow(DishNotFoundException::new));
    }


    @Override
    public void updateDish(DishModel dishModel) {
        dishRepository.save(dishEntityMapper.toEntity(dishModel));
    }

    @Override
    public List<DishModel> getAllDishesByRestaurant(int pageN, int size, Long restaurantId) {
        Pageable pagingSort = PageRequest.of(pageN, size, Sort.by("categoryId.name"));
        Page<DishEntity> page = dishRepository.findAllByRestaurantId(restaurantId, pagingSort);
        List<DishEntity> dishEntityList = page.getContent();

        if (dishEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return dishEntityMapper.toDishModelList(dishEntityList);
    }
}
