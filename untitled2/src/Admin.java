
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    String userNumber;
    Scanner scanner = new Scanner(System.in);

    public void main() {
        int number_one;
        int number_two;
        int number_three;
        for (number_one = 0; number_one < 3; ) {
            System.out.println("******************");
            System.out.println("欢迎来到管理员界面！");
            System.out.println("1.密码管理");
            System.out.println("2.用户管理");
            System.out.println("3.退出登录");
            System.out.println("******************");
            System.out.println("请输入您想使用的功能：");
            int count = scanner.nextInt();
            number_one = count;

            if (number_one == 3) {
                return;
            }

            switch (count) {
                case 1:
                    for (number_two = 0; number_two < 3; ) {
                        System.out.println("******************");
                        System.out.println("1.修改自身的密码");
                        System.out.println("2.重置指定用户的密码");
                        System.out.println("3.退出");
                        System.out.println("******************");
                        System.out.println("请选择：");
                        count = scanner.nextInt();
                        number_two = count;
                        switch (count) {
                            case 1 -> {
                                String oldNumber, newNumber;
                                System.out.println("请输入原密码：");
                                oldNumber = scanner.next();
                                System.out.println("请输入新的密码：");
                                newNumber = scanner.next();
                                passwordManagement(userNumber, newNumber, oldNumber);
                                System.out.println("密码修改完成");
                            }
                            case 2 -> {
                                System.out.println("请输入您想重置的用户的ID:");
                                String id = scanner.next();
                                userManagement(id, getUsers());
                            }
                        }
                    }
                    break;
                case 2:
                    for (number_three = 0; number_three < 4; ) {
                        System.out.println("******************");
                        System.out.println("1.列出所有用户信息");
                        System.out.println("2.删除用户信息");
                        System.out.println("3.查询用户信息");
                        System.out.println("4.退出");
                        System.out.println("******************");
                        System.out.println("请选择：");
                        count = scanner.nextInt();
                        number_three = count;
                        switch (count) {
                            case 1 -> listAllUsers(getUsers());  // 调用父类的方法列出所有用户信息
                            case 2 -> {
                                System.out.println("请输入要删除的用户ID：");
                                String deleteId = scanner.next();
                                boolean result = deleteUser(deleteId);// 调用父类的方法根据ID删除用户信息
                                if (result) {
                                    System.out.println("删除成功");
                                } else {
                                    System.out.println("删除失败");
                                }
                            }
                            case 3 -> {
                                System.out.println("请选择查询关键字还是全部查询（关键字 1，全部 2）");
                                int choice;
                                choice = scanner.nextInt();
                                if (choice == 1) {
                                    System.out.println("请输入查询关键字：");
                                    String query = scanner.next();
                                    searchUser(getUsers(), query);  // 调用父类的方法根据关键字查询用户信息
                                } else {
                                    listAllUsers(getUsers());
                                }
                            }
                        }
                    }
                default:
                    break;
            }
        }
    }

    public boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("ynuinfo#777")) {
            System.out.println("管理员登录成功！");
            return true;
        } else {
            System.out.println("管理员登录失败，请检查用户名和密码！");
            return false;
        }
    }

    public void passwordManagement(String username, String newNumber, String oldNumber) {
        if (oldNumber.equals(username)) {
            this.userNumber = newNumber;
            System.out.println("密码修改成功！");
        } else {
            System.out.println("当前密码不正确，无法修改密码！");
        }
    }

    public void userManagement(String id, List<User> users) {
        // 重置指定用户的密码的逻辑，在子类中实现
        for (User user : users) {
            int index = user.userId.indexOf(id);
            if (index != -1) {
                // 找到指定用户的位置
                String userName = user.userName.get(index);
                System.out.println("用户" + userName + "的密码已重置为默认密码。");
                // 通过User类的方法重置密码
                return;
            }
        }
        System.out.println("未找到指定用户。");
    }

    public void listAllUsers(List<User> users) {
        // 列出所有用户信息的逻辑，在子类中实现
        System.out.println("所有用户信息:");
        for (User user : users) {
            for (int i = 0; i < user.userId.size(); i++) {
                if (isUserDeleted(user.userId, i)) {
                    continue;  // 跳过已删除的用户信息
                }
                System.out.println("用户ID: " + user.userId.get(i));
                System.out.println("用户名: " + user.userName.get(i));
                System.out.println("密码: " + user.passWord.get(i));
                System.out.println("邮箱: " + user.email.get(i));
                System.out.println("等级: " + user.level.get(i));
                System.out.println("注册时间: " + user.time.get(i));
                System.out.println("消费金额: " + user.costMoney.get(i));
                System.out.println("消费次数: " + user.costCount.get(i));
                System.out.println("联系电话: " + user.phoneNumber.get(i));
                System.out.println("--------------------");
            }
        }
    }
    private boolean isUserDeleted(List<String> userIds, int index) {
        // 检查用户是否已删除
        // 判断索引是否超出范围或者用户ID为空（已删除）
        return index >= userIds.size() || userIds.get(index).isEmpty();
    }
    public boolean deleteUser(String userId) {
        // 根据ID删除用户信息的逻辑，在子类中实现
        for (User user : getUsers()) {
            int index = user.userId.indexOf(userId);
            if (index != -1) {
                // 找到指定用户的位置
               user.userId.remove(index);
                user.userName.remove(index);
                user.passWord.remove(index);
                user.email.remove(index);
                user.level.remove(index);
                user.time.remove(index);
                user.costMoney.remove(index);
                user.costCount.remove(index);
                user.phoneNumber.remove(index);

                System.out.println("删除成功");
                return true;
            }
        }
        System.out.println("未找到指定用户");
        return false;
    }

    public void searchUser(List<User> users, String query) {
        // 根据用户名或者ID查询用户信息的逻辑，在子类中实现
        for (User user : users) {
            for (int i = 0; i < user.userId.size(); i++) {
                if (user.userId.get(i).equals(query) || user.userName.get(i).equals(query)) {
                    System.out.println("User ID: " + user.userId.get(i));
                    System.out.println("Username: " + user.userName.get(i));
                    System.out.println("Password: " + user.passWord.get(i));
                    System.out.println("Email: " + user.email.get(i));
                    System.out.println("Level: " + user.level.get(i));
                    System.out.println("Time: " + user.time.get(i));
                    System.out.println("Cost Money: " + user.costMoney.get(i));
                    System.out.println("Cost Count: " + user.costCount.get(i));
                    System.out.println("Phone Number: " + user.phoneNumber.get(i));
                }
            }
        }
    }
}
