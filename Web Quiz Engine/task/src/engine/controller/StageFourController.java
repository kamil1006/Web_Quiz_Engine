package engine.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import engine.entity.Completion;
import engine.entity.Odpowiedz;
import engine.entity.Quiz;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StageFourController {
    //-----------------------------------------------------------------------
    private Quiz quiz;
    private List<String> opcje;
    //private List<Boolean> opcjeOdpowiedzi;
    Quiz odpowiedz;
    List<Quiz> qwizy;
    int licznik;
    //-----------------------------------------------------------------------
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizService quizService;

    @Autowired
    CompletionRepository completionRepository;
    //-----------------------------------------------------------------------

    public StageFourController() {
        qwizy=new ArrayList<>();
    }


    //-----------------------------------------------------------------------
    @PostMapping(path="/api/quizzes",  consumes = "application/json" )
    public ResponseEntity<String> insertKwiz(@RequestBody com.fasterxml.jackson.databind.JsonNode inf, Model model, Authentication authentication) {

        String title = inf.get("title").asText();
        String text = inf.get("text").asText();
        JsonNode options = null;
        options = inf.get("options");

        List<String> opcje= new ArrayList<>();
        if(options!=null) {
            if (options.isArray()) {
                ArrayNode arrayNode = (ArrayNode) options;
                for (int i = 0; i < arrayNode.size(); i++) {
                    opcje.add(arrayNode.get(i).asText());
                }
            }
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //int answer = Integer.parseInt(inf.get("answer").asText());

        List<Integer> odpowiedzi= new ArrayList<>();
        JsonNode answers=null;
        answers = inf.get("answer");
        if(answers!=null) {
            if (answers.isArray()) {
                ArrayNode arrayNode = (ArrayNode) answers;
                for (int i = 0; i < arrayNode.size(); i++) {
                    odpowiedzi.add(arrayNode.get(i).intValue());
                }
            }
        }



        boolean czyOk=false;
        if(title.length()>0 && text.length()>0&& opcje.size()>1) czyOk=true;

        if(czyOk) {
            licznik++;
//            quiz = new Quiz(licznik, title, text, opcje, answer);
            //quiz = new Quiz(licznik, title, text, opcje, odpowiedzi);
            quiz = new Quiz( title, text, opcje, odpowiedzi);
            quiz.setWlasciciel(authentication.getName());

            System.out.println("wprowadzono " + quiz);
            qwizy.add(quiz);
            quizRepository.save(quiz);

            // return null;
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type", "application/json");

            Gson gson = new Gson();
            //gson.


//            Quiz kwis = qwizy.get(licznik - 1);
  //          String s = gson.toJson(kwis);
            String s= gson.toJson(quiz);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(s);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    //-----------------------------------------------------------------------
    @PostMapping(path="/api/quizzes/{id}/solve",consumes = "application/json")
    public Object solveQuiz(@PathVariable("id") int id, @RequestBody JsonNode answer, Model model,Authentication authentication) {
        //return quiz;
        System.out.println(answer);
        Odpowiedz odpowiedz = new Odpowiedz();
        List<Integer> prawidloweOdpowiedzi=null;
        List<Integer> prawidloweOdpowiedzi2=new ArrayList<>();
        Quiz pobrany=quizRepository.findById(id);
        System.out.println("pobrano do sprawdzenia-->  "+pobrany);
        try {
            //prawidloweOdpowiedzi = qwizy.get(id - 1).getAnswer();
            prawidloweOdpowiedzi=pobrany.getAnswer();
            for(int k:prawidloweOdpowiedzi){
                prawidloweOdpowiedzi2.add(k);
            }
            prawidloweOdpowiedzi=prawidloweOdpowiedzi2;
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }


        JsonNode answers = answer.get("answer");
        List<Integer> odpowiedzi= new ArrayList<>();
        boolean czyOk=false;
        if(answers.isArray()){
            ArrayNode arrayNode = (ArrayNode) answers;
            for (int i = 0; i < arrayNode.size(); i++) {
                odpowiedzi.add(arrayNode.get(i).intValue());
            }



        }

        czyOk=prawidloweOdpowiedzi.equals(odpowiedzi);

        if(czyOk){
            odpowiedz.setSuccess(true);
            odpowiedz.setFeedback("Congratulations, you're right!");

            //--------------------------------------------------------
            LocalDateTime localDateTime = LocalDateTime.now();
            String name = authentication.getName();
            int idQwizu=id;
            List<Completion> completions;
            completions = pobrany.getCompletions();
            if(completions==(null)){
                completions=new ArrayList<>();
            }
            Completion completion= new Completion(pobrany,localDateTime,name);
            completions.add(completion);
            pobrany.setCompletions(completions);
            System.out.println(completion);
            //   System.out.println("do zapisania lista: "+completions);
           // quizRepository.update(idQwizu,completions);
          //  System.out.println("pobrany zmodyfikowany: "+pobrany);

           // quizService.update(pobrany);
            completionRepository.save(completion);


            //--------------------------------------------------------
        }
        else {
            odpowiedz.setSuccess(false);
            odpowiedz.setFeedback("Wrong answer! Please, try again.");

        }

        return odpowiedz;
    }
    //-----------------------------------------------------------------------
  //  @GetMapping("/api/quizzes")
    public List<Quiz> getQuizybyId() {

        System.out.println("wszystkie kwizy");
        List<Quiz> listaQuizow = quizRepository.findAll();
        return listaQuizow;
        //return qwizy;
    }
    //-----------------------------------------------------------------------
    @GetMapping("/api/quizzes/{id}")
    public Object getQuizybyId(@PathVariable("id") int id) {
        Quiz kwis = null;
        try {
            //kwis = qwizy.get(id - 1);
            kwis=quizRepository.findById(id);
            System.out.println(id + " pobrano kwis" + kwis);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
       if (kwis!=null)
        return kwis;
       else
           return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    //-----------------------------------------------------------------------


}
