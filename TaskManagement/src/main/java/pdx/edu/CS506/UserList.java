package main.java.pdx.edu.CS506;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> list;

    public UserList(){
        list = new ArrayList<>();
    }

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

    public void addList(ArrayList<User> l){
       for(User u:l){
           checkNAdd(u.getName(), u.getEmail(), u.getPassword());
       }
    }

    public User searchUser(String user, String password){
        for(User u:list){
            if(u.getName().equals(user) || u.getEmail().equals(user)){
               if(u.getPassword().equals(password))
                   return u;
            }
        }
        return null;
    }

    public ArrayList<User> getList(){
        return list;
    }

}
