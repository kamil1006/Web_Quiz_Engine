package engine.controller;

import engine.entity.Odpowiedz;
import engine.entity.Quiz;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RestController
public class StageTwoController {
    //-----------------------------------------------------------------------
    private Quiz quiz;
    private List<String> opcje;
    //private List<Boolean> opcjeOdpowiedzi;
    Quiz odpowiedz;
    List<Quiz> qwizy;
    int licznik;

    //-----------------------------------------------------------------------
    public StageTwoController() {
        opcje = Arrays.asList("Robot","Tea leaf","Cup of coffee","Bug");
      //  opcjeOdpowiedzi = Arrays.asList(false,false,true,false);
//        quiz=new Quiz("The Java Logo","What is depicted on the Java logo?",opcje,opcjeOdpowiedzi);
      //  quiz=new Quiz("The Java Logo","What is depicted on the Java logo?",opcje,2);

        qwizy=new ArrayList<>();


    }
    //-----------------------------------------------------------------------
    @GetMapping("/api/quiz")
    public Quiz getQuizApi() {
        return quiz;
    }
    //-----------------------------------------------------------------------
    @PostMapping("/api/quiz")
    public Odpowiedz checkAnswer(@RequestParam("answer") int answer) {
        //return quiz;
        System.out.println(answer);
        Odpowiedz odpowiedz = new Odpowiedz();
        if(answer==2){
            odpowiedz.setSuccess(true);
            odpowiedz.setFeedback("Congratulations, you're right!");
        }
      else {
            odpowiedz.setSuccess(false);
            odpowiedz.setFeedback("Wrong answer! Please, try again.");

        }

        return odpowiedz;
    }
    //-----------------------------------------------------------------------
    @GetMapping("/api/quizzes/{id}")
    public Object getQuizybyId(@PathVariable("id") int id) {
        Quiz kwis = null;
        try {
             kwis = qwizy.get(id - 1);
            System.out.println(id + " pobrano kwis" + kwis);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return kwis;

    }
    //-----------------------------------------------------------------------
    @GetMapping("/api/quizzes")
    public List<Quiz> getQuizybyId() {

        System.out.println("wszystkie kwizy");

        return qwizy;
    }
    //-----------------------------------------------------------------------
  /*  @PostMapping(path="/api/quizzes",  consumes = "application/json" )
    public ResponseEntity<String> insertKwiz(@RequestBody com.fasterxml.jackson.databind.JsonNode inf, Model model) {

        String title = inf.get("title").asText();
        String text = inf.get("text").asText();
        JsonNode options = inf.get("options");
        int answer = Integer.parseInt(inf.get("answer").asText());
        List<String> opcje= new ArrayList<>();
        if(options.isArray()){
            ArrayNode arrayNode = (ArrayNode) options;
            for (int i = 0; i < arrayNode.size(); i++) {
                opcje.add(arrayNode.get(i).asText());
            }


        }
       licznik++;

       quiz=new Quiz(licznik,title,text,opcje,answer);

       System.out.println("wprowadzono "+quiz);
        qwizy.add(quiz);

       // return null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","application/json");

        Gson gson = new Gson();
            //gson.


        Quiz kwis=qwizy.get(licznik-1);
        String s = gson.toJson(kwis);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(s);

    }
 */   //-----------------------------------------------------------------------
   // @PostMapping("/api/quizzes/{id}/solve")
  /*  public Odpowiedz solveQuiz(@PathVariable("id") int id, @RequestParam("answer") int answer) {
        //return quiz;
        System.out.println(answer);
        Odpowiedz odpowiedz = new Odpowiedz();
        if(answer==qwizy.get(id-1).getAnswer()){
            odpowiedz.setSuccess(true);
            odpowiedz.setFeedback("Congratulations, you're right!");
        }
        else {
            odpowiedz.setSuccess(false);
            odpowiedz.setFeedback("Wrong answer! Please, try again.");

        }

        return odpowiedz;
    }
*/

    //-----------------------------------------------------------------------


    //-----------------------------------------------------------------------
}
