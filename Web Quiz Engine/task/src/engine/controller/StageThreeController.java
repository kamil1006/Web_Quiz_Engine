package engine.controller;

//@RestController
public class StageThreeController {
 /*   //-----------------------------------------------------------------------
    private Quiz quiz;
    private List<String> opcje;
    //private List<Boolean> opcjeOdpowiedzi;
    Quiz odpowiedz;
    List<Quiz> qwizy;
    int licznik;
    //-----------------------------------------------------------------------

    public StageThreeController() {
        qwizy=new ArrayList<>();
    }


    //-----------------------------------------------------------------------
    @PostMapping(path="/api/quizzes",  consumes = "application/json" )
    public ResponseEntity<String> insertKwiz(@RequestBody com.fasterxml.jackson.databind.JsonNode inf, Model model) {

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
            quiz = new Quiz(licznik, title, text, opcje, odpowiedzi);


            System.out.println("wprowadzono " + quiz);
            qwizy.add(quiz);

            // return null;
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type", "application/json");

            Gson gson = new Gson();
            //gson.


            Quiz kwis = qwizy.get(licznik - 1);
            String s = gson.toJson(kwis);

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
    public Object solveQuiz(@PathVariable("id") int id, @RequestBody JsonNode answer, Model model) {
        //return quiz;
        System.out.println(answer);
        Odpowiedz odpowiedz = new Odpowiedz();
        List<Integer> prawidloweOdpowiedzi=null;
        try {
            prawidloweOdpowiedzi = qwizy.get(id - 1).getAnswer();
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
        }
        else {
            odpowiedz.setSuccess(false);
            odpowiedz.setFeedback("Wrong answer! Please, try again.");

        }

        return odpowiedz;
    }
    //-----------------------------------------------------------------------
    @GetMapping("/api/quizzes")
    public List<Quiz> getQuizybyId() {

        System.out.println("wszystkie kwizy");

        return qwizy;
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
 */   //-----------------------------------------------------------------------
}
