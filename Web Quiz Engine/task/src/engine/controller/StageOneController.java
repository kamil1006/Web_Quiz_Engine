package engine.controller;

import engine.entity.Odpowiedz;
import engine.entity.Quiz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

//@RestController
public class StageOneController {

    private Quiz quiz;
    private List<String> opcje;
    //private List<Boolean> opcjeOdpowiedzi;
    Odpowiedz odpowiedz;


    public StageOneController() {
        opcje = Arrays.asList("Robot","Tea leaf","Cup of coffee","Bug");
      //  opcjeOdpowiedzi = Arrays.asList(false,false,true,false);
//        quiz=new Quiz("The Java Logo","What is depicted on the Java logo?",opcje,opcjeOdpowiedzi);
      //  quiz=new Quiz("The Java Logo","What is depicted on the Java logo?",opcje);

    }

    @GetMapping("/api/quiz")
    public Quiz getQuizApi() {
        return quiz;
    }

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

}
