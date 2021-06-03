package engine.service;

import engine.entity.Completion;
import engine.entity.CompletionTiny;
import engine.repository.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompletionService {

    @Autowired
    CompletionRepository completionRepository;

    public Map<String,Object> getComletionByUserPages(Integer pageNo, Integer pageSize, String completedBy) {


        System.out.println("kursy zrealizowane przez"+completedBy);
        System.out.println("i chcemy numer strony -> "+pageNo);

      //  if(pageNo>0) pageNo--;
        Pageable paging = PageRequest.of(pageNo, pageSize);
       Page<Completion> pagedResult = completionRepository.findAllByCompletedByPaging(completedBy,paging);//
        //Page<Quiz> pagedResult =repository.findAll(paging);
        int k=0;

        Map<String,Object> mapa = new LinkedHashMap<>();
        mapa.put("totalPages",pagedResult.getTotalPages());
        mapa.put("totalElements",pagedResult.getTotalElements());
        mapa.put("last",pagedResult.isLast());
        mapa.put("first",pagedResult.isFirst());
        mapa.put("empty",pagedResult.isEmpty());
        List<Completion> content = pagedResult.getContent();
        List<CompletionTiny> contentReloaded=new ArrayList<>();
        for(Completion q:content){

            contentReloaded.add(new CompletionTiny(q.getKwis().getId(),q.getCompletedAt()));
        }

        //mapa.put("content",pagedResult.getContent());
        mapa.put("content",contentReloaded);
        System.out.println("******");
        System.out.println(content);

        System.out.println("******");
        System.out.println(contentReloaded);
        System.out.println("******");
       // mapa.put("content",pagedResult.getContent());

        System.out.println(mapa);

        return mapa;
    }




}
