
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.io.File;


public class myClient {
    public static String fetch_index(){
        String url="http://localhost:9000/one";
        try{
            HttpResponse<String> res=Unirest.get(url).asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
        }
        catch(Exception ex){
            return ex.toString();
        }
    }
    public static String Login(String email,String pass){
        String url="http://localhost:9000/Login";
        try{
             HttpResponse<String> res=Unirest.get(url)
                     .queryString("email",email)
                     .queryString("pass",pass)
                     .asString();
             if(res.getStatus()==200)return res.getBody();
             else return "Server Error";
        }
        catch(Exception ex){
            return ex.toString();
        }
       
    }
    public static String Signup(String email,String pass,String mobile,String address,File ph){
        String url="http://localhost:9000/Signup";
        try{
             HttpResponse<String> res=Unirest.post(url)
                .queryString("email",email)
                .queryString("pass",pass)
                .queryString("mobile",mobile)
                .queryString("address",address)
                .field("ph", ph).asString();
             if(res.getStatus()==200){
                 return res.getBody();
             }
             else{
                 return "Server error";
             }
        }
        catch(Exception ex){
            return ex.toString();
        }
       
    }
    
    public static String fetchcat(){
        String url="http://localhost:9000/fetchcat";
        
        try{
            HttpResponse<String> res=Unirest.get(url).asString();
            if(res.getStatus()==200){
                
                return res.getBody();
            }
            else{
                return "Server error";
            }
        }
        catch(Exception ex){
            System.out.println("Exception");
            return ex.toString();
        }
        
    }
    
    public static String courses(String category){
        String url="http://localhost:9000/courses";
        try{
            
           HttpResponse<String> res=Unirest.get(url)
                   .queryString("category",category)
                   .asString();
               
            
            if(res.getStatus()==200){
               
                return res.getBody();
            }
            else{
                return "Server error";
            }
        }
        catch(Exception ex){
            
            return ex.toString();
        }
        
    }
    
    
    public static String fetchlectures(int course_id)
    {
        try{
            HttpResponse<String> res=Unirest.get("http://localhost:9000/fetchlectures")
                    .queryString("course_id",course_id)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
        }
        catch(Exception ex){
            return ex.toString();
        }
    }
    
    public static String fetchdetails(int id){
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/fetchdetails")
                    .queryString("id",id)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
            
        } catch (Exception e) {
            
            return e.toString();
        }
    }
    
    public static String adminLogin(String username,String password){
        try{
            HttpResponse<String>res=Unirest.get("http://localhost:9000/adminlogin")
                    .queryString("username",username)
                    .queryString("password",password)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
        }
        catch(Exception ex){
            return ex.toString();
        }
    }
    
    public static String addCategory(String name,File ph){
        try {
            System.out.println(name);
            System.out.println(ph);
             HttpResponse<String> res=Unirest.post("http://localhost:9000/addcategory")
                .queryString("category",name)
                .field("f1",ph)
                .asString();
             if(res.getStatus()==200)return res.getBody();
             else return "Server error";
        } catch (Exception e) {
            return e.toString();
        }
       
    }
    
    public static String deletecat(String name){
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/deletecat")
                    .queryString("name",name)
                    .asString();
            
        if(res.getStatus()==200)return res.getBody();
        else return "Server error";
        } 
        catch (Exception e) {
            return e.toString();
        }
    }
    
    public static String fetchCategories(){
        
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/fetchcategories").asString();
             System.out.println("hello world");
            
        if(res.getStatus()==200)return res.getBody();
        else return "Server error";
        } 
        catch (Exception e) {
            System.out.println("exception");
            return e.toString();
        }
    }
    
    public static String addCourse(String category,String course_name,String course_desc,File ph){
        try {
            HttpResponse<String> res=Unirest.post("http://localhost:9000/addcourse")
                    .queryString("category",category)
                    .queryString("course_name",course_name)
                    .queryString("course_desc",course_desc)
                    .field("ph", ph)
                    .asString();
             if(res.getStatus()==200)return res.getBody();
              else return "Server error";
            
        } catch (Exception e) {
            return e.toString();
        }
        
    }
    
    public static String fetchCourses(String category){
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/fetchCourses")
                    .queryString("category",category)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    public static String deleteCourse(int id){
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/deleteCourse")
                    .queryString("id",id)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    public static String loadCourses(){
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/loadcourses").asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
            
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    
    public static String addLecture(String name,String desc,String duration,String trailer,int course_id,File lecture,File ph){
        try {
            HttpResponse<String> res=Unirest.post("http://localhost:9000/addlecture")
                    .queryString("name",name)
                     .queryString("desc",desc)
                     .queryString("duration",duration)
                     .queryString("trailer",trailer)
                     .queryString("course_id",course_id)
                     .field("ph",ph)
                     .field("lecture",lecture)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
                    
            
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    
    public static String fetch_lectures(int course_id){
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/fetchlectures1")
                    .queryString("course_id",course_id)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
            
        } 
        catch (Exception e) {
            return e.toString();
        }
    }
    
    public static String deleteLecture(int id){
         try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/deletelecture")
                    .queryString("id",id)
                    .asString();
            if(res.getStatus()==200)return res.getBody();
            else return "Server error";
            
        } 
        catch (Exception e) {
            return e.toString();
        }
    }
}
