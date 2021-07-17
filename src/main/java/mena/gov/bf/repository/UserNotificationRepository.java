package mena.gov.bf.repository;

import mena.gov.bf.domain.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

    List<UserNotification> findUserNotificationsByUserIdAndDeletedIsFalse(Long userdId);

    // List<UserNotification> findUserNotificationsByUserIdAndDeletedIsFalse(Long userId);

    //List<UserNotification> findUserNotificationsByUserIdAndTacheEtapeIdAndDeletedIsFalse(Long userId, Long tacheId);
}
