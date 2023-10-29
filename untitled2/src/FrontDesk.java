import java.util.*;
public class FrontDesk extends Manager{

Scanner scanner = new Scanner(System.in);
    public void main(){
        int number_one;
        for(number_one = 0;number_one<6;){
            System.out.println("******************");
            System.out.println("输入您想使用的功能：");
            System.out.println("1.登录");
            System.out.println("2.列出所有正在上映影片的信息");
            System.out.println("3.列出所有正在上映影片的信息");
            System.out.println("4.列出指定电影和场次的信息");
            System.out.println("5.售票功能");
            System.out.println("6.退出");
            System.out.println("******************");
            int count = scanner.nextInt(); // 用于记录使用的功能
            number_one = count;
            if(number_one == 6){
                return;
            }
            switch (count) {
                case 1, 2 -> listAllMovies();
                case 3 -> listAllScreenings();
                case 4 -> listGetScreenings();
                case 5 -> sellTicket();
                default -> {
                }
            }
        }
    }
    public boolean login(String name_2, String FrontDeskNumber) {
        if (name_2.equals("admin") && FrontDeskNumber.equals("admin")) {
            System.out.println("前台登录成功！");
            return true;
        } else {
            System.out.println("前台登录失败，请检查用户名和密码！");
            return false;
        }
    }
    @Override
    public void listAllScreenings() {
        List<Screening> newScreenings;
        newScreenings = getScreening();
        System.out.println("所有场次信息:");
        for (Screening screening : newScreenings) {
            System.out.println("电影片名: " + screening.getMovie().getTitle());
            System.out.println("放映厅: " + screening.getHall());
            System.out.println("时间: " + screening.getTime());
            System.out.println("价格: " + screening.getPrice());
            System.out.println("--------------------");
        }
    }

    @Override
    public void listAllMovies() {
        super.listAllMovies();
    }
    public void listGetScreenings() {
        // ... existing code for listing all screenings ...

        // Add the functionality to list specific movie and screening information
        System.out.println("4.列出指定电影和场次的信息");
        System.out.print("请输入电影片名：");
        String movieTitle = scanner.next();
        System.out.print("请输入场次时间：");
        String screeningTime = scanner.next();

        boolean foundScreening = false;
        for (Screening screening : screenings) {
            Movie movie = screening.getMovie();
            if (movie.getTitle().equals(movieTitle) && screening.getTime().equals(screeningTime)) {
                foundScreening = true;
                System.out.println("影片片名：" + movie.getTitle());
                System.out.println("放映厅：" + screening.getHall());
                System.out.println("时间：" + screening.getTime());
                System.out.println("价格：" + screening.getPrice());
                System.out.println("座位信息：");
                displaySeatInformation(screening);
                System.out.println("--------------------");
                break;
            }
        }

        if (!foundScreening) {
            System.out.println("未找到该电影和场次的信息！");
        }
    }

     public void displaySeatInformation(Screening screening) {

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

    public void sellTicket() {
        System.out.println("5.售票功能");
        System.out.print("请输入电影片名：");
        String movieTitle = scanner.next();
        System.out.print("请输入场次时间：");
        String screeningTime = scanner.next();
        System.out.print("请输入用户名/手机号：");
        String userName = scanner.next();
        System.out.print("请输入支付金额：");
        double paymentAmount = scanner.nextDouble();

        Screening targetScreening = null;
        for (Screening screening : screenings) {
            Movie movie = screening.getMovie();
            if (movie.getTitle().equals(movieTitle) && screening.getTime().equals(screeningTime)) {
                targetScreening = screening;
                break;
            }
        }

        if (targetScreening != null) {
            // 生成电子票的ID编号
            String ticketId = generateTicketId();

            // 创建电影票对象

            // 输出电影票信息
            System.out.println("已售出电影票信息：");
            System.out.println("片名：" + targetScreening.getMovie().getTitle());
            System.out.println("场次时间：" + targetScreening.getTime());
            System.out.println("电子ID编号：" + ticketId);
            System.out.println("用户名/手机号：" + userName);
            System.out.println("支付金额：" + paymentAmount);
            System.out.println("--------------------");

            // 可以将电影票对象添加到相应的列表或保存到数据库中，以供后续使用

        } else {
            System.out.println("未找到该电影和场次的信息！");
        }
    }

    private String generateTicketId() {
        // 这里使用简单的方式生成电子票的ID编号，你可以根据实际需求设计更复杂的算法
        return UUID.randomUUID().toString();
    }
}
