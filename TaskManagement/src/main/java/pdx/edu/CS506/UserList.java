package main.java.pdx.edu.CS506;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> list;

    public UserList(){
        list = new ArrayList<>();
    }

//    public User checkNAdd(User user){
//        String name = user.getName();
//        String email = user.getEmail();
//        String password = user.getPassword();
//
//        for(User u:list){
//            if(u.getEmail().equalsIgnoreCase(email) || u.getName().equals(name)){
//                if(u.getPassword().equals(password))
//                    return u;
//            }
//        }
//        User newuser = new User(name,email,password);
//        list.add(newuser);
//        return newuser;
//    }
    public User checkNAdd(String name, String email, String password){
        for(User u:list){
            if(u.getEmail().equalsIgnoreCase(email) || u.getName().equals(name)){
                if(u.getPassword().equals(password))
                   return u;
            }
        }
        User newuser = new User(name,email,password);
        list.add(newuser);
        return newuser;
    }

    public ArrayList<User> getList(){
        return list;
    }

}
