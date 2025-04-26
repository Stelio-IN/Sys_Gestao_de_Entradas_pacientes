
package Controller;


public class Controller_Login {
    public boolean login(String nome, String password){
            if(nome.equals("admin")&&password.equals("1234")){            
           return true;
        }else{            
           return false; 
        }          
    }
}
