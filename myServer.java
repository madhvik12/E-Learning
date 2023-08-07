import com.vmm.JHTTPServer;
import java.security.interfaces.RSAKey;
import java.util.Properties;
import java.sql.*;
import javax.mail.Flags;
public class myServer extends JHTTPServer {
    Response res=null;
    myServer(int port) throws Exception{
        super(port);
    }
    @Override
    
   
    public Response serve(String uri,String method ,Properties header,Properties params,Properties files){
        
        if(uri.equals("/")){
            String ans=Math.random()+"";
             res=new Response(HTTP_OK,"text/plain",ans);
            return res;
        }
        else if(uri.equals("/one")){
             res=new Response(HTTP_OK,"text/plain","Hello World");
            return res;
        }
        else if(uri.equals("/Login")){
            String email=params.getProperty("email");
            String pass=params.getProperty("pass");
            try{
                 ResultSet rs=DBLoader.executeQuery("select * from users where email='"+email+"' and password='"+pass+"'");
                 if(rs.next()){
                     String ans="Success";
                     res=new Response(HTTP_OK,"text/plain",ans);
                     return res;
                 }
                 else{
                      String ans="Failed";
                     res=new Response(HTTP_OK,"text/plain",ans);
                     return res;
                 }
            }
           catch(Exception ex){
               ex.printStackTrace();
           }
        }
        else if(uri.equals("/Signup")){
            String email=params.getProperty("email");
            String pass=params.getProperty("pass");
            String mobile=params.getProperty("mobile");
            String address=params.getProperty("address");
            
            String photoname=saveFileOnServerWithRandomName(files, params, "ph", "src/uploads/");
            try{
                ResultSet rs=DBLoader.executeQuery("select * from users where email='"+email+"'");
                if(rs.next()){
                    res=new Response(HTTP_OK,"text/plain","exist");
                    return res;
                }
                else{
                    rs.moveToInsertRow();
                    rs.updateString("email",email);
                    rs.updateString("password",pass);
                    rs.updateString("mobile",mobile);
                    rs.updateString("address",address);
                    rs.updateString("photo", "src/uploads/"+photoname);
                    rs.insertRow();
                    res=new Response(HTTP_OK,"text/plain","success");
                    return res;
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        else if(uri.equals("/fetchcat")){
           
            try {
                
                ResultSet rs=DBLoader.executeQuery("select * from category");
                String ans="";
                while(rs.next()){
                    String name=rs.getString("name");
                    String photo=rs.getString("photo");
                    String row=name+"$"+photo;
                    ans=ans+row+";;";
                
                }
                
                 res=new Response(HTTP_OK,"text/plain",ans);
                 return res;
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(uri.equals("/courses")){
            
            String category=params.getProperty("category");
            try {
                String ans="";
               
                ResultSet rs=DBLoader.executeQuery("select * from courses where category='"+category+"'");
                while(rs.next()){
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String photo=rs.getString("photo");
                    String row=id+"$"+name+"$"+photo;
                    ans=ans+row+";;";
                }
                
                return new Response(HTTP_OK,"text/plain",ans);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if(uri.equals("/fetchlectures")){
            int course_id=Integer.parseInt(params.getProperty("course_id"));
            try {
                ResultSet rs=DBLoader.executeQuery("select * from lectures where course_id="+course_id);
                String ans="";
                while(rs.next()){
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String photo=rs.getString("photo");
                    String row=id+"$"+name+"$"+photo;
                    ans=ans+row+";;";
                }
                return new Response(HTTP_OK,"text/plain",ans);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if(uri.equals("/fetchdetails")){
            int id=Integer.parseInt(params.getProperty("id"));
            String ans="";
            try {
                ResultSet rs=DBLoader.executeQuery("select * from lectures where id="+id);
                if(rs.next()){
                    int id1=rs.getInt("id");
                    String name=rs.getString("name");
                    String description=rs.getString("description");
                    String duration=rs.getString("duration");
                   String photo=rs.getString("photo"); 
                   int course_id=rs.getInt("course_id");
                   String trailer=rs.getString("trailer");
                    String video=rs.getString("video");
                    ans=id1+"$"+name+"$"+description+"$"+duration+"$"+photo+"$"+course_id+"$"+trailer+"$"+video;
                    return new Response(HTTP_OK,"text/plain",ans);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(uri.equals("/adminlogin")){
            String username=params.getProperty("username");
            String password=params.getProperty("password");
            try {
                ResultSet rs=DBLoader.executeQuery("select * from admin where username='"+username+"' and password='"+password+"'" );
                if(rs.next())return new Response(HTTP_OK,"text/plain","success");
                else return new Response(HTTP_OK,"text/plain","fail");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if(uri.equals("/addcategory")){
            String category=params.getProperty("category");
            
            String photoname=saveFileOnServerWithRandomName(files, params, "f1", "src/uploads/");
            
            try {
                ResultSet rs=DBLoader.executeQuery("select * from category where name='"+category+"'");
                if(rs.next())return new Response(HTTP_OK,"text/plain","exists");
                else {
                    System.out.println("hello");
                    rs.moveToInsertRow();
                    rs.updateString("name", category);
                    rs.updateString("photo", "src/uploads/"+photoname);
                    rs.insertRow();
                    return new Response(HTTP_OK,"text/plain","success");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else if(uri.equals("/deletecat")){
            String name=params.getProperty("name");
            try {
                ResultSet rs=DBLoader.executeQuery("select * from category where name='"+name+"'");
                if(rs.next()){
                    rs.deleteRow();
                    return new Response(HTTP_OK,"text/plain","success");
                }
                else{
                    return new Response(HTTP_OK,"text/plain","fail");
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        else if(uri.equals("/fetchcategories")){
            try {
                String ans="";
                ResultSet rs=DBLoader.executeQuery("select * from category");
                while(rs.next()){
                    String name=rs.getString("name");
                    ans=ans+name+";;";
                }
                return new Response(HTTP_OK,"text/plain",ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if(uri.equals("/addcourse")){
            String category=params.getProperty("category");
            String course_name=params.getProperty("course_name");
            String course_desc=params.getProperty("course_desc");
            String photoname=saveFileOnServerWithRandomName(files, params, "ph", "src/uploads/");
            try {
                ResultSet rs=DBLoader.executeQuery("select * from courses");
                rs.moveToInsertRow();
                rs.updateString("name", course_name);
                rs.updateString("description", course_desc);
                rs.updateString("photo", "src/uploads/"+photoname);
                rs.updateString("category", category);
                rs.insertRow();
                return new Response(HTTP_OK,"text/plain","success");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else if(uri.equals("/fetchCourses")){
            String category=params.getProperty("category");
            try {
                String ans="";
                ResultSet rs=DBLoader.executeQuery("select * from courses where category='"+category+"'");
                while(rs.next()){
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String desc=rs.getString("description");
                    String photo=rs.getString("photo");
                    String row=id+"$"+name+"$"+desc+"$"+photo;
                    ans=ans+row+";;";
                }
                return new Response(HTTP_OK,"text/plain",ans);
                
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
        
        else if(uri.equals("/deleteCourse")){
            int id=Integer.parseInt(params.getProperty("id"));
            System.out.println("hello");
            try {
                ResultSet rs=DBLoader.executeQuery("select * from courses where id="+id);
                if(rs.next()){
                    rs.deleteRow();
                    return new Response(HTTP_OK,"text/plain","success");
                }
                else return new Response(HTTP_OK,"text/plain","fail");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        else if(uri.equals("/loadcourses")){
            try {
                String ans="";
                ResultSet rs=DBLoader.executeQuery("select * from courses");
                while(rs.next()){
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String row=id+"$"+name;
                    ans=ans+row+";;";
                }
                
                return new Response(HTTP_OK,"text/plain",ans);
                
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
        
        else if(uri.equals("/addlecture")){
            try {
                System.out.println("inside");
                String name=params.getProperty("name");
                 String desc=params.getProperty("desc");
                  String duration=params.getProperty("duration");
                   String trailer=params.getProperty("trailer"); 
                  int course_id=Integer.parseInt(params.getProperty("course_id"));
                   String videoname=saveFileOnServerWithRandomName(files, params, "lecture", "src/Lectures/");
                     String photoname=saveFileOnServerWithRandomName(files, params, "ph", "src/uploads/");
                     
                     ResultSet rs=DBLoader.executeQuery("select * from lectures ");
                     rs.moveToInsertRow();
                     rs.updateString("name", name);
                     rs.updateString("description", desc);
                     rs.updateString("duration", duration);
                     rs.updateString("photo", "src/uploads/"+photoname);
                     rs.updateInt("course_id",course_id );
                     rs.updateString("trailer", trailer);
                     rs.updateString("video", "src/Lectures/"+videoname);
                     rs.insertRow();
                     return new Response(HTTP_OK,"text/plain","success");
                      
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if(uri.equals("/fetchlectures1")){
            try {
                int course_id=Integer.parseInt(params.getProperty("course_id"));
                ResultSet rs=DBLoader.executeQuery("select * from lectures where course_id="+course_id);
                String ans="";
                while(rs.next()){
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String desc=rs.getString("description");
                    String photo=rs.getString("photo");
                    String row=id+"$"+name+"$"+desc+"$"+photo;
                    ans=ans +row+";;";
                }
                
                return new Response(HTTP_OK,"text/plain",ans);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if(uri.equals("/deletelecture")){
            try {
                int id=Integer.parseInt(params.getProperty("id"));
                ResultSet rs=DBLoader.executeQuery("select * from lectures where id="+id);
                if(rs.next()){
                    rs.deleteRow();
                     return new Response(HTTP_OK,"text/plain","success");
                }
                else    return new Response(HTTP_OK,"text/plain","fail");
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
        else  {
            Response res=new Response(HTTP_OK,"text/plain","Invalid URI");
            return res;
        }
        return res;
    }
   

//public static void main(String args[]){
//    try{
//          myServer obj =new myServer(9000);
//          Thread.sleep(1000000000);
//    }
//    catch(Exception ex){
//        ex.printStackTrace();
//    }
//      
//}
}