package ru.sleed.thridtask.repository;

import org.springframework.stereotype.Repository;
import ru.sleed.thridtask.model.User;

import java.util.ArrayList;

@Repository
public class UserRepository {
        private ArrayList<User> users = new ArrayList<>();


        {
            users.add(new User(1L, "Alex", 19));
            users.add(new User(2L, "Dima", 23));
            users.add(new User(3L, "Maks", 8));
            users.add(new User(4L, "Mike", 12));
            users.add(new User(5L, "Sergey", 73));
        }


        public ArrayList<User> getUsers() {
            return users;
        }

        public void setUsers(ArrayList<User> users) {
            this.users = users;
        }

}
