package engine.repository;

import engine.entity.Completion;
import engine.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompletionRepository extends PagingAndSortingRepository<Completion,Integer> {
    List<Completion> findAll();


    List<Completion> findAllByCompletedBy(String completed_by);

    @Query(value = "SELECT u.* FROM Completion AS u WHERE u.completed_By = ?1 ORDER BY u.completed_At DESC ",
            //countQuery = "SELECT count(*) FROM Completion",
            nativeQuery = true)
    Page<Completion> findAllByCompletedByPaging(String completedBy,Pageable pageable);

    List<Completion> findAllByKwis(Quiz kwis);
}
