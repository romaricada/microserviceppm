package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.Etape;
import mena.gov.bf.domain.UserNotification;
import mena.gov.bf.service.dto.EtapeDTO;
import mena.gov.bf.service.dto.UserNotificationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link UserNotification} and its DTO {@link UserNotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserNotificationMapper extends EntityMapper<UserNotificationDTO, UserNotification> {

    default UserNotification fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserNotification userNotification = new UserNotification();
        userNotification.setId(id);
        return userNotification;
    }
}
