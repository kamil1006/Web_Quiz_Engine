package engine.repository;

import engine.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
//extends CrudRepository<Quiz,Integer> {

    List<Quiz> findAll();
    Quiz findById(int id);
    Page findAll(Pageable pageable);

    //u.options
    //@Query(value = "SELECT u.id , u.title , u.text FROM Quiz u")
    //Page findAllWithoutAnswersAndOwner(Pageable pageable);
  //  @Query(value = "SELECT u.id , u.title , u.text , y.options FROM Quiz AS u LEFT JOIN QUIZ_OPTIONS AS y ON u.id=y.quiz_id", nativeQuery = true)
    @Query(value = "SELECT u.* FROM Quiz AS u",
            countQuery = "SELECT count(*) FROM Quiz",
            nativeQuery = true)

    Page <Quiz> findAllWithoutAnswersAndOwner(Pageable pageable);

    /*
    @Modifying(flushAutomatically = true)
    @Query()
    void update(@Param(value = "id") long id, Quiz quiz);


    @Modifying(flushAutomatically = true)
    @Query("update Quiz u set u.completions = false where u.lastLoginDate < :date")
    void updateCompletions(Quiz pobrany);

    // void save(Quiz quiz);
*/
}
