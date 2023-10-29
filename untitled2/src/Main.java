import java.util.*;
public class Main{
public static void main(String[] args) {
        //初始化管理员的登录系统

        Admin admin = new Admin();

        // 调用管理员登录方法
        Manager manager = new Manager();
        FrontDesk frontDesk = new FrontDesk();
        Custom custom = new Custom();
        System.out.println("欢迎来到影院管理系统！");
        int count ;
        Scanner countNumber = new Scanner(System.in);
        int i;
        //循环
        for (i=0;i<5;) {
            System.out.println("******************");
            System.out.println("输入您想进入的功能界面：");
            System.out.println("1.管理员界面");
            System.out.println("2.经理界面");
            System.out.println("3.前台界面");
            System.out.println("4.用户界面");
            System.out.println("5.退出");
            System.out.println("******************");

            count = countNumber.nextInt();
            i=count;
            switch (count) {
                case 1:
                    String name;
                    System.out.println("请输入预先设定的管理员的用户名：");
                    name = countNumber.next();
                    System.out.println("输入预先设定的管理员的密码");
                    admin.userNumber = countNumber.next();
                    if (admin.login(name, admin.userNumber)) {
                        admin.main();
                    }
                    break;
                case 2:
                      String name_1,managerNumber;
                      System.out.println("请输入预先设定的经理的用户名：");

                      name_1 = countNumber.next();
                      System.out.println("输入预先设定的经理的密码");
                      managerNumber = countNumber.next();
                       if (manager.login(name_1,managerNumber)){
                           manager.main();
                       }
                    break;
                case 3:
                    String name_2,FrontDeskNumber;
                    System.out.println("请输入预先设定的前台的用户名：");
                    name_2 = countNumber.next();
                    System.out.println("输入预先设定的前台的密码");
                    FrontDeskNumber = countNumber.next();

                    if (frontDesk.login(name_2,FrontDeskNumber)){
                        frontDesk.main();
                    }
                    break;
                case 4:
                    custom.main();
                    break;
                default: {
                    break;
                }
            }
            System.out.println("******************");
        }
    }
}
