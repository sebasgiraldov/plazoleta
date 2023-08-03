package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T15:59:24-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {

    @Override
    public UserEntity toEntity(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userModel.getId() );
        userEntity.name( userModel.getName() );
        userEntity.lastName( userModel.getLastName() );
        userEntity.idNumber( userModel.getIdNumber() );
        userEntity.phone( userModel.getPhone() );
        userEntity.email( userModel.getEmail() );
        userEntity.password( userModel.getPassword() );
        userEntity.rolId( rolModelToRolEntity( userModel.getRolId() ) );

        return userEntity.build();
    }

    @Override
    public UserModel toUserModel(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.id( userEntity.getId() );
        userModel.name( userEntity.getName() );
        userModel.lastName( userEntity.getLastName() );
        userModel.idNumber( userEntity.getIdNumber() );
        userModel.phone( userEntity.getPhone() );
        userModel.email( userEntity.getEmail() );
        userModel.password( userEntity.getPassword() );
        userModel.rolId( rolEntityToRolModel( userEntity.getRolId() ) );

        return userModel.build();
    }

    protected RolEntity rolModelToRolEntity(RolModel rolModel) {
        if ( rolModel == null ) {
            return null;
        }

        RolEntity rolEntity = new RolEntity();

        rolEntity.setId( rolModel.getId() );
        rolEntity.setName( rolModel.getName() );
        rolEntity.setDescription( rolModel.getDescription() );

        return rolEntity;
    }

    protected RolModel rolEntityToRolModel(RolEntity rolEntity) {
        if ( rolEntity == null ) {
            return null;
        }

        RolModel rolModel = new RolModel();

        rolModel.setId( rolEntity.getId() );
        rolModel.setName( rolEntity.getName() );
        rolModel.setDescription( rolEntity.getDescription() );

        return rolModel;
    }
}
