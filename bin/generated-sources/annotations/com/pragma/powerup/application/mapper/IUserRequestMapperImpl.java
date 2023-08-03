package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-03T10:55:35-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public UserModel toUser(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.email( userRequestDto.getEmail() );
        userModel.id( userRequestDto.getId() );
        userModel.idNumber( userRequestDto.getIdNumber() );
        userModel.lastName( userRequestDto.getLastName() );
        userModel.name( userRequestDto.getName() );
        userModel.password( userRequestDto.getPassword() );
        userModel.phone( userRequestDto.getPhone() );
        userModel.rolId( rolRequestDtoToRolModel( userRequestDto.getRolId() ) );

        return userModel.build();
    }

    protected RolModel rolRequestDtoToRolModel(RolRequestDto rolRequestDto) {
        if ( rolRequestDto == null ) {
            return null;
        }

        RolModel rolModel = new RolModel();

        rolModel.setDescription( rolRequestDto.getDescription() );
        rolModel.setName( rolRequestDto.getName() );

        return rolModel;
    }
}
