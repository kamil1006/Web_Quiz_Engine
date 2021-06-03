package engine.service;

import engine.entity.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuizService {

    @Autowired
    QuizRepository repository;


    @Autowired
    EntityManager em;
    //---------------------------------------------------------------------------
    public List<Quiz> getAllQuizes(Integer pageNo, Integer pageSize) {
/*
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Quiz> pagedResult = repository.findAllWithoutAnswersAndOwner(paging);//repository.findAll(paging);

        int k=0;

        Map<String,Object> mapa = new LinkedHashMap<>();
        mapa.put("totalPages",pagedResult.getTotalPages());
        mapa.put("totalElements",pagedResult.getTotalElements());
        mapa.put("last",pagedResult.isLast());
        mapa.put("first",pagedResult.isFirst());
        mapa.put("sort",pagedResult.getSort());
        mapa.put("number",pagedResult.getNumber());
        mapa.put("numberOfElements",pagedResult.getNumberOfElements());
        mapa.put("size",pagedResult.getSize());
        mapa.put("empty",pagedResult.isEmpty());
        mapa.put("pageable",pagedResult.getPageable());
        mapa.put("content",pagedResult.getContent());

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Quiz>();
        }

 */
        return null;
    }
    //---------------------------------------------------------------------------
    public Map<String,Object> getAllQuizesPages(Integer pageNo, Integer pageSize) {

       // if(pageNo>0) pageNo--;
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Quiz> pagedResult = repository.findAllWithoutAnswersAndOwner(paging);//
        //Page<Quiz> pagedResult =repository.findAll(paging);
        int k=0;

        Map<String,Object> mapa = new LinkedHashMap<>();
        mapa.put("totalPages",pagedResult.getTotalPages());
        mapa.put("totalElements",pagedResult.getTotalElements());
        mapa.put("last",pagedResult.isLast());
        mapa.put("first",pagedResult.isFirst());
        mapa.put("sort",pagedResult.getSort());
        mapa.put("number",pagedResult.getNumber());
        mapa.put("numberOfElements",pagedResult.getNumberOfElements());
        mapa.put("size",pagedResult.getSize());
        mapa.put("empty",pagedResult.isEmpty());
        mapa.put("pageable",pagedResult.getPageable());
        List<Quiz> content = pagedResult.getContent();
        List<Quiz> contentReloaded=new ArrayList<>();
        for(Quiz q:content){

            contentReloaded.add(new Quiz(q.getId(),q.getTitle(),q.getText(),q.getOptions()));
        }

        //mapa.put("content",pagedResult.getContent());
        mapa.put("content",contentReloaded);

        System.out.println(mapa);

        if (pagedResult.hasContent()) {
            return mapa;//pagedResult.getContent();
        } else {
           // return  new LinkedHashMap<>();
            return mapa;
        }
    }
    //---------------------------------------------------------------------------
        public void update(Quiz q){

           // em.persist(q);
            System.out.println("niby zapis");
            em.merge(q);
            em.flush();
        /*
         em.merge(q);
        em.persist(q);
        em.flush();
        */
        }


    //---------------------------------------------------------------------------
}
