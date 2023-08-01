package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T12:42:11-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public UserModel toUser(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.id( userRequestDto.getId() );
        userModel.name( userRequestDto.getName() );
        userModel.lastName( userRequestDto.getLastName() );
        userModel.idNumber( userRequestDto.getIdNumber() );
        userModel.phone( userRequestDto.getPhone() );
        userModel.email( userRequestDto.getEmail() );
        userModel.password( userRequestDto.getPassword() );
        userModel.rolId( rolRequestDtoToRolModel( userRequestDto.getRolId() ) );

        return userModel.build();
    }

    protected RolModel rolRequestDtoToRolModel(RolRequestDto rolRequestDto) {
        if ( rolRequestDto == null ) {
            return null;
        }

        RolModel rolModel = new RolModel();

        rolModel.setName( rolRequestDto.getName() );
        rolModel.setDescription( rolRequestDto.getDescription() );

        return rolModel;
    }
}
