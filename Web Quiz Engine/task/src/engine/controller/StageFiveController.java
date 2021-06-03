package engine.controller;

import engine.WebQuizEngine;
import engine.entity.Completion;
import engine.entity.Quiz;
import engine.entity.User;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class StageFiveController {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    CompletionRepository completionRepository;

    @Autowired
    private UserService userService;
    private Map<String,String> roles;

    private Logger logger = Logger.getLogger(getClass().getName());
    //-----------------------------------------------------------------------
    @PostConstruct
    protected void loadRoles(){
        //// could be stored in database
        roles= new LinkedHashMap<String,String>();
        //key role, value display to user
        roles.put("ADMIN","admin");
        roles.put("USER","uzykownik");
    }
    //-----------------------------------------------------------------------
    private boolean validateEmail(String email){
         Pattern pattern;
         Matcher matcher;
         String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        EMAIL_PATTERN="^(.+)@(.+)$";
        EMAIL_PATTERN= "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //-----------------------------------------------------------------------
    private boolean validatePassword(String pas){
        if(pas.length()>4) return true;
        else return false;
    }
    //-----------------------------------------------------------------------
    @PostMapping(path="/api/register",  consumes = "application/json" )
    public ResponseEntity<String> registerNewUser(@RequestBody com.fasterxml.jackson.databind.JsonNode inf, Model model) {

       // UserDto userDto = new UserDto();
        User user= new User();
        String emalia = inf.get("email").asText();
        String password = inf.get("password").asText();

        System.out.println(emalia +" "+password);
        if(validateEmail(emalia)&&validatePassword(password)){

            //****************************************************
            // check the database if user already exists
            User existing = userService.findByUserName(emalia);
           // userService.save(userDto);
            //userService.saveWithRole(userDto);
            //userService.save(user);
            //****************************************************

            boolean jest=false;
           // for(UserDto u: WebQuizEngine.lista){
            for(User u: WebQuizEngine.lista){
                if(u.getUsername()==emalia || u.getUsername().equals(emalia)){
                    jest=true;
                }
            }
            if(!jest){
                user.setUsername(emalia);
                user.setPassword(password);
                //WebQuizEngine.lista.add(user);
                userService.save(user);
                System.out.println("zapisano do listy"+user);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Content-Type", "application/json");
                return ResponseEntity.ok()
                        .headers(responseHeaders)
                        .body("");
            }else {
                //zwroc blad bo jest juz emalia
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }else{
            // zwroc blad bo nieprawidlowa walidacja
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        //check if email exists
        //validate email
        // password min 5 znakow
        // bad request 400
    }
    //-----------------------------------------------------------------------
    @DeleteMapping(path="/api/quizzes/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable("id") int id, Authentication authentication) {

        // gdy ok to 204 (No content)

        // gdy nie ma to 404 (Not found)
        //gdy user is not the author of this quiz, the response is the 403 (Forbidden)
        Quiz kwis = null;
        try {
            //kwis = qwizy.get(id - 1);
            kwis=quizRepository.findById(id);
            System.out.println(authentication.getName()+" wziol" +id + " pobrano kwis do skasowania " + kwis);
            if(authentication.getName().equals(kwis.getWlasciciel()) || authentication.getName()==(kwis.getWlasciciel())){

                List<Completion> allByKwis = completionRepository.findAllByKwis(kwis);
                for(Completion c:allByKwis){
                    completionRepository.delete(c);
                }


                quizRepository.deleteById(id);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }



        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (NullPointerException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
     }
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    }
