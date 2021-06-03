package engine.controller;

import com.google.gson.Gson;
import engine.entity.Completion;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import engine.service.CompletionService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StageSixController {

    //-----------------------------------------------------------------------
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    CompletionRepository completionRepository;

    @Autowired
    QuizService service;

    @Autowired
    CompletionService completionService;

    //-----------------------------------------------------------------------
    @GetMapping("/api/quizzes")
    //public List<Quiz> getQuizy(@RequestParam(defaultValue = "0") Integer page) {
    public ResponseEntity<String> getQuizy(@RequestParam(defaultValue = "0") Integer page) {


        System.out.println("wszystkie kwizy");
        System.out.println("stronka ->>>"+page);
        //List<Quiz> listaQuizow = quizRepository.findAll();
        //List<Quiz> listaQuizow = service.getAllQuizesPages(page,10);

        Map<String, Object> listaQuizow = service.getAllQuizesPages(page, 10);

        Gson gson = new Gson();
        String s;
       /* if(listaQuizow.get("empty")=="true")
        {
            s="[]";
        }else*/

         s = gson.toJson(listaQuizow);

        System.out.println(s);

        //JSONObject json = new JSONObject(listaQuizow);

       // return  s;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
         return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(s);
       // return listaQuizow;
        //return qwizy;
    }
    //-----------------------------------------------------------------------
    @GetMapping("/api/quizzes/completed")
    public ResponseEntity<String> getQuizyKompletneByUsera(@RequestParam(defaultValue = "0") Integer page, Authentication authentication){

        System.out.println("----------");
        String name = authentication.getName();
        List<Completion> listaRozwiazan = completionRepository.findAll();
        System.out.println(listaRozwiazan);
        System.out.println("----------");
        listaRozwiazan = completionRepository.findAllByCompletedBy(name);

        Map<String, Object> listaRozwiazan2=completionService.getComletionByUserPages(page, 10,name);

        System.out.println(listaRozwiazan2);
        System.out.println("----------");
        Gson gson = new Gson();
        //String s = gson.toJson(listaRozwiazan);
        String s = gson.toJson(listaRozwiazan2);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(s);


        //return null;
    }

    //-----------------------------------------------------------------------
}
