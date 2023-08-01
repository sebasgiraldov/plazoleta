package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T12:14:59-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230622-1425, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {

    @Override
    public UserEntity toEntity(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.email( userModel.getEmail() );
        userEntity.id( userModel.getId() );
        userEntity.idNumber( userModel.getIdNumber() );
        userEntity.lastName( userModel.getLastName() );
        userEntity.name( userModel.getName() );
        userEntity.password( userModel.getPassword() );
        userEntity.phone( userModel.getPhone() );
        userEntity.rolId( rolModelToRolEntity( userModel.getRolId() ) );

        return userEntity.build();
    }

    @Override
    public UserModel toUserModel(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.email( userEntity.getEmail() );
        userModel.id( userEntity.getId() );
        userModel.idNumber( userEntity.getIdNumber() );
        userModel.lastName( userEntity.getLastName() );
        userModel.name( userEntity.getName() );
        userModel.password( userEntity.getPassword() );
        userModel.phone( userEntity.getPhone() );
        userModel.rolId( rolEntityToRolModel( userEntity.getRolId() ) );

        return userModel.build();
    }

    protected RolEntity rolModelToRolEntity(RolModel rolModel) {
        if ( rolModel == null ) {
            return null;
        }

        RolEntity rolEntity = new RolEntity();

        rolEntity.setDescription( rolModel.getDescription() );
        rolEntity.setId( rolModel.getId() );
        rolEntity.setName( rolModel.getName() );

        return rolEntity;
    }

    protected RolModel rolEntityToRolModel(RolEntity rolEntity) {
        if ( rolEntity == null ) {
            return null;
        }

        RolModel rolModel = new RolModel();

        rolModel.setDescription( rolEntity.getDescription() );
        rolModel.setId( rolEntity.getId() );
        rolModel.setName( rolEntity.getName() );

        return rolModel;
    }
}
