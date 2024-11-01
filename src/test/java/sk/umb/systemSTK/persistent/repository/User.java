package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.repository.CrudRepository;

public interface User extends CrudRepository<sk.umb.systemSTK.persistent.entity.User, Long> {
}
