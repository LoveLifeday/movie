
import java.util.*;
public class Manager extends Admin{
    Scanner scanner = new Scanner(System.in);
     List<Movie> movies = new ArrayList<>();
     List<Screening> screenings = new ArrayList<>();

    public Manager() {
        // 初始化电影数据
        addMovie("流浪地球", "郭帆", "吴京、李光洁", "在不久的将来，太阳急剧膨胀，人类必须寻找新的家园。", 125);
        addMovie("复仇者联盟", "乔·罗素", "小罗伯特·唐尼、克里斯·埃文斯", "地球面临前所未有的威胁，复仇者联盟集结保卫地球。", 143);
        addMovie("哈利·波特与魔法石", "克里斯·哥伦布", "丹尼尔·雷德克里夫、艾玛·沃森", "天天过着苦命日子的哈利·波特发现自己被选为魔法学校的学生。", 152);
        addScreening(movies.get(0), "放映厅A", "10:00", 50.0);
        addScreening(movies.get(0), "放映厅B", "13:00", 50.0);
        addScreening(movies.get(1), "放映厅C", "14:30", 60.0);
        addScreening(movies.get(2), "放映厅D", "18:00", 55.0);
    }

    public void main() {
        int number_one;
        int number_two;
        int number_three;
        for (number_one = 0; number_one < 3; ) {
            System.out.println("******************");
            System.out.println("输入您想进入的功能界面：");
            System.out.println("1.登录");
            System.out.println("2.影片管理");
            System.out.println("3.排片管理");
            System.out.println("4.退出");
            System.out.println("******************");
            int count = scanner.nextInt();
            number_one = count;
            if (number_one == 4) {
                return;
            }
            switch (count) {
                case 1, 2 -> {
                    for (number_two = 0; number_two < 6; ) {
                        System.out.println("******************");
                        System.out.println("输入您想使用的功能：");
                        System.out.println("1.列出所有正在上映影片的信息");
                        System.out.println("2.添加影片的信息");
                        System.out.println("3.修改电影的信息");
                        System.out.println("4.删除影片的信息");
                        System.out.println("5.查询影片的信息");
                        System.out.println("6.退出");
                        System.out.println("******************");
                        count = scanner.nextInt();
                        number_two = count;

                        switch (count) {
                            case 1 -> listAllMovies();
                            case 2 -> addMovie();
                            case 3 -> modifyMovie();
                            case 4 -> deleteMovie();
                            case 5 -> searchMovies();
                            default -> {
                            }
                        }
                    }
                }
                case 3 -> {
                    for (number_three = 0; number_three < 5; ) {
                        System.out.println("******************");
                        System.out.println("输入您想使用的功能：");
                        System.out.println("1.增加场次");
                        System.out.println("2.修改场次");
                        System.out.println("3.删除场次");
                        System.out.println("4.列出所有场次信息");
                        System.out.println("5.退出");
                        System.out.println("******************");
                        count = scanner.nextInt();
                        number_three = count;
                        if (number_three == 5) {
                            return;
                        }
                        switch (count) {
                            case 1 -> addScreening();
                            case 2 -> modifyScreening();
                            case 3 -> deleteScreening();
                            case 4 -> listAllScreenings();
                            default -> {
                            }
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

    public boolean login(String name_1, String password) {
        if (name_1.equals("admin") && password.equals("admin")) {
            System.out.println("经理登录成功！");
            return true;
        } else {
            System.out.println("经理登录失败，请检查用户名和密码！");
            return false;
        }
    }


    public static class Movie {
          String title;
        private String director;
        private String actors;
        private String plot;
        private int duration;

        public Movie(String title, String director, String actors, String plot, int duration) {
            this.title = title;
            this.director = director;
            this.actors = actors;
            this.plot = plot;
            this.duration = duration;
        }

        public String getTitle() {
            return title;
        }

        public String getDirector() {
            return director;
        }

        public String getActors() {
            return actors;
        }

        public String getPlot() {
            return plot;
        }

        public int getDuration() {
            return duration;
        }


        public void setDirector(String director) {
            this.director = director;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public void setPlot(String plot) {
            this.plot = plot;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }

    // 创建电影对象并设置属性
    public void listAllMovies() {
        System.out.println("正在上映影片的信息:");
        for (Movie movie : movies) {
            System.out.println("片名: " + movie.getTitle());
            System.out.println("导演: " + movie.getDirector());
            System.out.println("主演: " + movie.getActors());
            System.out.println("剧情简介: " + movie.getPlot());
            System.out.println("时长: " + movie.getDuration() + "分钟");
            System.out.println("--------------------");
        }
    }

    public void addMovie() {
        System.out.println("请输入影片信息:");
        System.out.print("片名: ");
        String title = scanner.next();
        System.out.print("导演: ");
        String director = scanner.next();
        System.out.print("主演: ");
        String actors = scanner.next();
        System.out.print("剧情简介: ");
        String plot = scanner.next();
        System.out.print("时长（分钟）: ");
        int duration = scanner.nextInt();

        Movie movie = new Movie(title, director, actors, plot, duration);
        movies.add(movie);

        System.out.println("影片信息添加成功!");
    }

    public void modifyMovie() {
        System.out.println("请输入要修改的影片片名:");
        String title = scanner.next();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                System.out.println("请输入新的影片信息:");
                System.out.print("导演: ");
                movie.setDirector(scanner.next());
                System.out.print("主演: ");
                movie.setActors(scanner.next());
                System.out.print("剧情简介: ");
                movie.setPlot(scanner.next());
                System.out.print("时长（分钟）: ");
                movie.setDuration(scanner.nextInt());
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("影片信息修改成功!");
        } else {
            System.out.println("未找到该影片!");
        }
    }

    public void deleteMovie() {
        System.out.println("请输入要删除的影片片名:");
        String title = scanner.next();

        Movie movieToRemove = null;
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                movieToRemove = movie;
                break;
            }
        }

        if (movieToRemove != null) {
            System.out.println("找到影片，是否继续删除(Y/N)：");
            String choice = scanner.next();
            if(choice.equals("Y")){
            movies.remove(movieToRemove);
            System.out.println("影片信息删除成功!");}
            else{
                System.out.println("不删除\n");
            }
        } else {
            System.out.println("未找到该影片!");
        }
    }

    public void searchMovies() {
        System.out.println("请输入查询关键词:");
        String keyword = scanner.next();

        System.out.println("查询结果:");
        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getTitle().contains(keyword)
                    || movie.getDirector().contains(keyword)
                    || movie.getActors().contains(keyword)) {

                System.out.println("导演: " + movie.getDirector());
                System.out.println("主演: " + movie.getActors());
                System.out.println("剧情简介: " + movie.getPlot());
                System.out.println("时长: " + movie.getDuration() + "分钟");
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("未找到匹配的影片信息");
        }
    }

    public void addMovie(String title, String director, String actors, String plot, int duration) {
        movies.add(new Movie(title, director, actors, plot, duration));
    }

    public  static class Screening {

        private Movie movie;
        private String hall;
        private String time;
        private double price;

        public Screening(Movie movie, String hall, String time, double price) {
            this.movie = movie;
            this.hall = hall;
            this.time = time;
            this.price = price;

        }

        public Movie getMovie() {
            return movie;
        }

        public String getHall() {
            return hall;
        }

        public String getTime() {
            return time;
        }

        public double getPrice() {
            return price;
        }

        // Setter methods

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        public void setHall(String hall) {
            this.hall = hall;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public void addScreening() {
        System.out.println("请输入场次信息:");
        System.out.print("电影片名: ");
        String movieTitle = scanner.next();

        Movie foundMovie = null;
        for (Movie movie : movies) {
            if (movie.getTitle().equals(movieTitle)) {
                foundMovie = movie;
                break;
            }
        }

        if (foundMovie == null) {
            System.out.println("未找到该影片!");
            return;
        }

        // Add screening details
        System.out.print("放映厅: ");
        String hall = scanner.next();
        System.out.print("时间（格式：hh:mm）: ");
        String time = scanner.next();
        System.out.print("价格: ");
        double price = scanner.nextDouble();

        // Create a new screening object
        Screening screening = new Screening(foundMovie, hall, time, price);
        screenings.add(screening);
        System.out.println("场次信息添加成功!");
    }

    public void modifyScreening() {
        System.out.println("请输入要修改的场次信息:");
        System.out.print("电影片名: ");
        String movieTitle = scanner.next();

        System.out.print("时间（格式：hh:mm）: ");
        String time = scanner.next();

        Screening foundScreening = null;
        for (Screening screening : screenings) {
            if (screening.getMovie().getTitle().equals(movieTitle) && screening.getTime().equals(time)) {
                foundScreening = screening;
                break;
            }
        }

        if (foundScreening == null) {
            System.out.println("未找到该场次!");
            return;
        }

        // Modify screening details
        System.out.print("新放映厅（或空白）: ");
        String hall = scanner.next();
        System.out.print("新电影片名（或空白）: ");
        String newMovieTitle = scanner.next();
        System.out.print("新时间（格式：hh:mm）（或空白）: ");
        String newTime = scanner.next();
        System.out.print("新价格（或0）: ");
        double price = scanner.nextDouble();

        if (!hall.isEmpty()) {
            foundScreening.setHall(hall);
        }
        if (!newMovieTitle.isEmpty()) {
            Movie newMovie = null;
            for (Movie movie : movies) {
                if (movie.getTitle().equals(newMovieTitle)) {
                    newMovie = movie;
                    break;
                }
            }
            if (newMovie != null) {
                foundScreening.setMovie(newMovie);
            } else {
                System.out.println("未找到新电影片名!");
            }
        }
        if (!newTime.isEmpty()) {
            foundScreening.setTime(newTime);
        }
        if (price != 0) {
            foundScreening.setPrice(price);
        }

        System.out.println("场次信息修改成功!");
    }

    public void deleteScreening() {
        System.out.println("请输入要删除的场次信息:");
        System.out.print("电影片名: ");
        String movieTitle = scanner.next();

        System.out.print("时间（格式：hh:mm）: ");
        String time = scanner.next();

        Screening screeningToRemove = null;
        for (Screening screening : screenings) {
            if (screening.getMovie().getTitle().equals(movieTitle) && screening.getTime().equals(time)) {
                screeningToRemove = screening;
                break;
            }
        }

        if (screeningToRemove != null) {
            screenings.remove(screeningToRemove);
            System.out.println("场次信息删除成功!");
        } else {
            System.out.println("未找到该场次!");
        }
    }

    public void listAllScreenings() {
        System.out.println("所有场次信息:");
        for (Screening screening : screenings) {
            System.out.println("电影片名: " + screening.getMovie().getTitle());
            System.out.println("放映厅: " + screening.getHall());
            System.out.println("时间: " + screening.getTime());
            System.out.println("价格: " + screening.getPrice());
            System.out.println("--------------------");
        }
    }
    public List<Screening> getScreening(){
        return screenings;
    }
    public void addScreening(Movie movie, String hall, String time, double price) {
        screenings.add(new Screening(movie,hall,time,price));
    }

}
