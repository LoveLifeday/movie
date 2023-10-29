import java.util.*;
import java.security.SecureRandom;

public class Custom extends FrontDesk{
    Scanner scanner = new Scanner(System.in);

   User user = new User();

   int number_login = 0;//记录登录是否成功
    public void main(){
        int number_one;
        int number_two;
        int number_three;
        System.out.println("欢迎来到用户登陆界面！");
        for(number_one =0;number_one<5;){
            System.out.println("******************");
            System.out.println("输入您想进入的功能界面：");
            System.out.println("1.注册");
            System.out.println("2.登录");
            System.out.println("3.退出登录");
            System.out.println("******************");
            int count = scanner.nextInt();
            number_one = count;
            if(number_one == 3){
                return;
            }
            switch (count) {
                case 1 -> register();
                case 2 -> {
                    login();
                    if (number_login == 1) {

                        for (number_two = 0; number_two < 3; ) {
                            System.out.println("******************");
                            System.out.println("1.用户密码管理");
                            System.out.println("2.购票");
                            System.out.println("3.退出");
                            System.out.println("******************");
                            int NumberOne = scanner.nextInt();
                            number_two = NumberOne;
                            if (number_two == 3) {
                                return;
                            }
                            switch (NumberOne) {
                                case 1:
                                    System.out.println("******************");
                                    System.out.println("1.修改自身密码");
                                    System.out.println("2.忘记密码");
                                    System.out.println("3.退出");
                                    System.out.println("******************");
                                    int NumberTwo = scanner.nextInt();


                                    switch (NumberTwo) {
                                        case 1:
                                            changePassword();
                                            break;
                                        case 2:
                                            resetPassword();
                                            break;
                                        case 0:
                                            break;
                                    }
                                    break;
                                case 2:
                                    for (number_three = 0; number_three < 6; ) {
                                        System.out.println("******************");
                                        System.out.println("1.查看所有电影放映信息");
                                        System.out.println("2.查看指定电影放映信息");
                                        System.out.println("3.购票");
                                        System.out.println("4.取票");
                                        System.out.println("5.查看购票历史");
                                        System.out.println("6.退出");
                                        System.out.println("******************");
                                        count = scanner.nextInt();
                                        number_three = count;
                                        switch (count) {
                                            case 1:
                                                listAllMovies();
                                                break;
                                            case 2:
                                                listGetMovie();
                                                break;
                                            case 3:
                                                TicketBookingSystem ticketBookingSystem = new TicketBookingSystem();
                                                ticketBookingSystem.run();
                                                break;
                                            case 4:
                                                TicketBookingSystem ticketBookingSystem1 = new TicketBookingSystem();
                                                ticketBookingSystem1.retrieveTicket();
                                                break;
                                            case 5:
                                                TicketBookingSystem ticketBookingSystem2 = new TicketBookingSystem();
                                                ticketBookingSystem2.viewPurchaseHistory();
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    break;
                            }

                        }
                    }
                }
                default -> {
                }
            }
        }
    }

    @Override
    public void listAllUsers(List<User> users) {
        super.listAllUsers(users);
    }

    public void register() {
        System.out.print("请输入用户名（不少于5个字符）：");
        String username = scanner.next();

        System.out.print("请输入密码（大于8个字符，必须包含大小写字母、数字和标点符号）：");
        String password = scanner.next();

        User user = getUsers().get(0); // 假设列表中只有一个用户
        LinkedList<String> userIdList = user.getUserId();

        // 检查用户名是否已被使用
        if (userIdList.contains(username)) {
            System.out.println("用户名已存在！");
            return;
        }

        // 验证用户名和密码
        if (username.length() >= 5 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            user.addUser("ID", username, password, "user@example.com", "1",
                    "2023-10-05", "0", "0", "1234567890"); // 为其他用户信息添加虚拟值
            System.out.println("注册成功！");
        } else {
            System.out.println("用户名或密码不符合要求！");
        }
    }

    public void login() {

        System.out.print("请输入用户名：");
        String enteredUsername = scanner.next();

        System.out.print("请输入密码：");
        String enteredPassword = scanner.next();

        User user = getUsers().get(0); // 假设列表中只有一个用户
        LinkedList<String> userNameList = user.getUserName();

        LinkedList<String> passwordList = user.getPassWord();

        if (userNameList.contains(enteredUsername)) {
            int index = userNameList.indexOf(enteredUsername);
            String storedPassword = passwordList.get(index);

            if (enteredPassword.equals(storedPassword)) {
                System.out.println("登录成功！");
                number_login = 1;

            } else {
                System.out.println("密码错误！");
                number_login = 0;
            }
        } else {
            System.out.println("用户名不存在！");
        }


    }

    public void changePassword() {
        System.out.print("请输入当前密码：");
        String currentPassword = scanner.next();
        User currentUser = getUsers().get(0); // 假设列表中只有一个用户
        LinkedList<String> passwordList = currentUser.getPassWord();
        String storedPassword = passwordList.get(0); // 假设密码列表中只有一个密码

        if (currentPassword.equals(storedPassword)) {
            System.out.print("请输入新密码：");
            String newPassword = scanner.next();

            if (newPassword.length() >= 8 && newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                passwordList.set(0, newPassword);
                System.out.println("密码修改成功！");
            } else {
                System.out.println("新密码格式不符合要求！");
            }
        } else {
            System.out.println("当前密码错误！");
        }
    }

    public void resetPassword() {
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入注册时使用的邮箱地址：");
        String email = scanner.next();

        User currentUser = getUsers().get(0); // 假设列表中只有一个用户
        LinkedList<String> usernameList = currentUser.getUserName();
        LinkedList<String> emailList = currentUser.getEmail();
        LinkedList<String> passwordList = currentUser.getPassWord();
        if (usernameList.contains(username)) {
            int index = usernameList.indexOf(username);
            String storedEmail = emailList.get(index);

            if (email.equals(storedEmail)) {
                String newPassword = generateRandomPassword();
                passwordList.set(index, newPassword); // 更新密码列表中的密码

                System.out.println("密码已重置，并发送到您的邮箱。请登录到邮箱查看新密码。");
                System.out.println("新密码：" + newPassword);
            } else {
                System.out.println("用户名和邮箱地址不匹配！");
            }
        } else {
            System.out.println("用户名不存在！");
        }
    }

    private String generateRandomPassword() {
        // 生成随机密码的代码
            final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$!%*?&";
            final int PASSWORD_LENGTH = 10;

            StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
            SecureRandom random = new SecureRandom();

            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(randomIndex);
                sb.append(randomChar);
            }

            return sb.toString();
        }


    @Override
    public void listAllScreenings() {
        super.listAllScreenings();
    }

    @Override
    public void listAllMovies() {
        super.listAllMovies();
    }


    public void listGetMovie(){
        System.out.print("请输入电影片名：");
        String movieTitle = scanner.next();
        boolean foundScreening = false;
        for (Screening screening : screenings) {
            Movie movie = screening.getMovie();
            if (movie.getTitle().equals(movieTitle) ) {
                foundScreening = true;
                System.out.println("影片片名：" + movie.getTitle());
                System.out.println("放映厅：" + screening.getHall());
                System.out.println("时间：" + screening.getTime());
                System.out.println("价格：" + screening.getPrice());
                System.out.println("--------------------");
                break;
            }
        }

        if (!foundScreening) {
            System.out.println("未找到该电影和场次的信息！");
        }
    }
public void payBill(Screening screening, String ticketId, int numberOfTickets) {
    String userLevel = String.valueOf(user.getLevel());

    // 根据用户级别确定折扣
    double discount;
    if (userLevel.equalsIgnoreCase("金牌")) {
        discount = 0.88;
        System.out.println("你是金牌用户\n");
    } else if (userLevel.equalsIgnoreCase("银牌")) {
        discount = 0.95;
        System.out.println("你是银牌用户\n");
    } else {
        discount = 1.0;
        System.out.println("你是铜牌用户\n");
    }

    // 计算购票总价
    double totalPrice = screening.getPrice() * discount * numberOfTickets;

    // 选择支付方式
    System.out.println("请选择支付方式：");
    System.out.println("1. 支付宝");
    System.out.println("2. 微信");
    System.out.println("3. 银行卡");
    int paymentMethod = scanner.nextInt();

    // 根据支付方式进行支付操作
    switch (paymentMethod) {
        case 1:
            System.out.println("原价是：" + totalPrice / discount);
            System.out.println("使用支付宝支付成功！");
            break;
        case 2:
            System.out.println("原价是：" + totalPrice / discount);
            System.out.println("使用微信支付成功！");
            break;
        case 3:
            System.out.println("原价是：" + totalPrice / discount);
            System.out.println("使用银行卡支付成功！");
            break;
    }

        // 模拟支付操作
        // 生成购票历史记录
    String purchaseRecord = "时间：" + screening.getTime() + "\n" +
            "电影：" + screening.getMovie().getTitle() + "\n" +
            "电影票编号：" + ticketId + "\n" +
            "购票数量：" + numberOfTickets + "\n" +
            "总价：" + totalPrice + "\n";

        // 将购票历史记录添加到购票历史列表
        TicketBookingSystem ticketBookingSystem = new TicketBookingSystem();
        ticketBookingSystem.purchaseHistory.add(purchaseRecord);

        System.out.println("支付成功！生成电影票的电子ID编号：" + ticketId);
        System.out.println("请在2分钟内完成取票，否则将自动取消订单。");
        // 生成电影票的电子ID编号
        System.out.println("支付成功！生成电影票的电子ID编号：" + ticketId);
    }

          String generateTicketId() {
        // 生成电子ID编号的逻辑，可以使用随机数、时间戳等方式生成唯一的编号
        // 此处使用随机数示例
        SecureRandom random = new SecureRandom();
        long ticketId = Math.abs(random.nextLong());
        return String.valueOf(ticketId);
    }
}


 class TicketBookingSystem extends Custom {
     Scanner scanner;
     List<Screening> screenings;
       List<String> purchaseHistory = new ArrayList<>();
     public TicketBookingSystem() {
         scanner = new Scanner(System.in);
         screenings = new ArrayList<>();
         // 初始化放映信息
         // TODO: 添加放映信息到 screenings 列表中
         screenings = getScreening();
     }
public void run() {
        System.out.print("请输入电影片名：");
        String movieTitle = scanner.nextLine();

        // 查找电影对应的放映信息
        Screening selectedScreening = findScreeningByMovieTitle(movieTitle);
        if (selectedScreening == null) {
            System.out.println("找不到相关的放映信息！");
            return;
        }

        // 显示座位信息
        displaySeatStatus(selectedScreening);

        // 获取用户输入的座位号
        System.out.print("请输入座位号（例如：3-4）：");
        String seatNumberInput = scanner.nextLine();

        System.out.print("请输入购票数量：");
        int numberOfTickets = scanner.nextInt();
        // 解析座位号
        String[] seatNumbers = seatNumberInput.split("-");
        int startRow = Integer.parseInt(seatNumbers[0]);
        int endRow = Integer.parseInt(seatNumbers[1]);

        // 检查座位号的有效性和是否已被占用
        if (!isValidSeatNumber(startRow, endRow) || isSeatOccupied(selectedScreening, startRow, endRow)) {
            System.out.println("无效的座位号或该座位已被占用！");
            return;
        }

        // 锁定座位，标记为已售出状态
        lockSeats(selectedScreening, startRow, endRow);
        // 生成电影票的电子ID编号
        String ticketId = generateTicketId();
        System.out.println("购票成功！生成电影票的电子ID编号：" + ticketId);
        System.out.println("请在2分钟内完成支付，否则座位将被释放。");

        // 创建定时任务，2分钟后释放座位
        TimerTask releaseSeatsTask = new TimerTask() {

            public void run() {
                releaseSeats(selectedScreening, startRow, endRow);
                System.out.println("座位锁定时间已过，座位已释放！");
            }
        };
        Timer timer = new Timer();
        timer.schedule(releaseSeatsTask, 2 * 60 * 1000); // 2分钟后执行任务

        // 进入支付流程
        payBill(selectedScreening, ticketId, numberOfTickets);

        // 更新座位表，将选定的座位标记为已售出状态
        updateSeatMatrix(selectedScreening, startRow, endRow);
        // 显示更新后的座位信息
        displaySeatStatus(selectedScreening);
    }
     // 添加取票功能
     public void retrieveTicket() {
         System.out.print("请输入电影票的电子ID编号：");
         String ticketId = scanner.nextLine();

         // 检查购票历史记录中是否存在指定的电子ID编号
         boolean ticketFound = false;
         for (String record : purchaseHistory) {
             if (record.contains(ticketId)) {
                 ticketFound = true;
                 break;
             }
         }

         // 输出结果
         if (ticketFound) {
             System.out.println("该票已成功取出。");
         } else {
             System.out.println("无效的电子ID编号或该票尚未支付。");
         }
     }

     // 添加查看购票历史功能
     public void viewPurchaseHistory() {
         if (purchaseHistory.isEmpty()) {
         System.out.println("购票历史记录为空！");
     } else {
         System.out.println("购票历史记录：");
         for (String record : purchaseHistory) {
             System.out.println(record);
         }
     }
     }
     private void updateSeatMatrix(Screening screening, int startRow, int endRow) {
         List<List<String>> seatMatrix = getSeatMatrix(screening);

         for (int row = startRow - 1; row < endRow; row++) {
             for (int col = 0; col < seatMatrix.get(row).size(); col++) {
                 if (seatMatrix.get(row).get(col).equals("O")) {
                     seatMatrix.get(row).set(col, "X");
                 }
             }
         }
     }

     private Screening findScreeningByMovieTitle(String movieTitle) {
         for (Screening screening : screenings) {
             Movie movie = screening.getMovie();
             if (movie.getTitle().equals(movieTitle)) {
                 return screening;
             }
         }
         return null;
     }

     private void displaySeatStatus(Screening screening) {
         List<List<String>> seatMatrix = getSeatMatrix(screening);

         System.out.print("   ");
         for (int i = 1; i <= seatMatrix.get(0).size(); i++) {
             System.out.print(i + " ");
         }
         System.out.println();

         for (int row = 0; row < seatMatrix.size(); row++) {
             System.out.print(row + 1 + "  ");
             for (String seat : seatMatrix.get(row)) {
                 System.out.print(seat + " ");
             }
             System.out.println();
         }

         // 显示座位信息
         // TODO: 根据放映信息，显示座位状态

     }


List<List<String>> getSeatMatrix(Screening screening) {
         List<List<String>> seatMatrix = new ArrayList<>();
         seatMatrix.add(Arrays.asList("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"));
         seatMatrix.add(Arrays.asList("O", "O", "O", "O", "X", "X", "O", "O", "O", "O", "O", "O"));
         seatMatrix.add(Arrays.asList("O", "O", "O", "O", "X", "X", "O", "O", "O", "O", "O", "O"));
         seatMatrix.add(Arrays.asList("O", "O", "O", "X", "X", "X", "X", "O", "O", "O", "O", "O"));
         seatMatrix.add(Arrays.asList("O", "O", "O", "X", "X", "X", "X", "O", "O", "O", "O", "O"));
         seatMatrix.add(Arrays.asList("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"));
         seatMatrix.add(Arrays.asList("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"));
         return seatMatrix;
     }

     private boolean isValidSeatNumber(int startRow, int endRow) {
         // 检查座位号的有效性
           int MAX_ROWS = 7;
           int MAX_COLS = 12;
         return startRow >= 1 && startRow <= MAX_ROWS && endRow >= 1 && endRow <= MAX_COLS;

     }

     private boolean isSeatOccupied(Screening screening, int startRow, int endRow) {
         // 检查座位是否已被占用
         List<List<String>> seatMatrix = getSeatMatrix(screening);


         for (int row = startRow - 1; row < endRow; row++) {
             List<String> seatRow = seatMatrix.get(row);
             for (String s : seatRow) {
                 if (s.equals("X")) {
                     return true;
                 }
             }
         }

         return false;
     }

     private void lockSeats(Screening screening, int startRow, int endRow) {
         // 锁定座位，标记为已售出状态
         List<List<String>> seatMatrix = getSeatMatrix(screening);

         // Mark the seats between the start and end rows as occupied ('X')
         for (int row = startRow - 1; row < endRow; row++) {
             List<String> seatRow = seatMatrix.get(row);
             Collections.fill(seatRow, "X");
         }

     }

     private void releaseSeats(Screening screening, int startRow, int endRow) {
         // 释放座位，标记为可用状态
         List<List<String>> seatMatrix = getSeatMatrix(screening);

         for (int row = startRow - 1; row < endRow; row++) {
             List<String> seatRow = seatMatrix.get(row);
             Collections.fill(seatRow, "O");
         }
     }
 }