package golmohammadi.repository;

import golmohammadi.entity.LinkAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkAddressRepository extends CrudRepository<LinkAddress, Long> {
        public List<LinkAddress> findByUrl(String url);
}
