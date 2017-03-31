package dp.generater;

import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<ONSPage, Long> {

    ONSPage findByUri(String uri);
}
