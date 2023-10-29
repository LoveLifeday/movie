import java.util.LinkedList;
import java.util.List;

public class User {
    LinkedList<String> userId = new LinkedList<>();
    LinkedList<String> userName = new LinkedList<>();
    LinkedList<String> passWord = new LinkedList<>();
    LinkedList<String> email = new LinkedList<>();
    LinkedList<String> level = new LinkedList<>();
    LinkedList<String> time = new LinkedList<>();
    LinkedList<String> costMoney = new LinkedList<>();
    LinkedList<String> costCount = new LinkedList<>();
    LinkedList<String> phoneNumber = new LinkedList<>();


    public List<User> getUsers() {
        List<User> users = new LinkedList<>();
        User user = new User();

        user.addUser("1", "JohnSmith", "Password1@", "john@example.com", "铜牌",
                "2023-09-28", "100", "5", "1234567890");

        user.addUser("2", "JaneSmith", "examplePassword2@", "jane.smith@example.com",
                "银牌", "2023-10-04 11:00:00", "200.00", "5", "9876543210");
        user.addUser("3", "TomSmith", "123456sdAD@", "Tom@example.com",
                "铜牌", "2023-10-05 11:00:00", "0", "0", "123456789");


        users.add(user);  // 将当前用户加入用户列表
        return users;
    }

    public void addUser(String userId, String userName, String passWord, String email, String level,
                        String time, String costMoney, String costCount, String phoneNumber) {
        this.userId.add(userId);
        this.userName.add(userName);
        this.passWord.add(passWord);
        this.email.add(email);
        this.level.add(level);
        this.time.add(time);
        this.costMoney.add(costMoney);
        this.costCount.add(costCount);
        this.phoneNumber.add(phoneNumber);
    }

    public LinkedList<String> getUserId() {

        return userId;
    }

    public   LinkedList<String> getUserName() {
        return userName;
    }

    public   LinkedList<String> getPassWord() {
        return passWord;
    }
    public   LinkedList<String> getEmail(){
        return email;
    }

    public LinkedList<String> getLevel() {
        return level;
    }

}
