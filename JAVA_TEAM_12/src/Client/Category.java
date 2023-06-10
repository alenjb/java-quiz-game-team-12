package Client;

import java.util.*;

public class Category {
    public String area = "";
    public List<Person> peopleList = new ArrayList();

    public Category() {
        // TODO Auto-generated constructor stub
    }

    public List<Person> getArea(String area, int num) {
        Random r = new Random();
        int range = 10; // 랜덤 숫자의 범위 (0 ~ 9)
        Set<Integer> randomNumbers = new HashSet();
        while (randomNumbers.size() < num) {
            int randomNumber = r.nextInt(range);
            randomNumbers.add(randomNumber);
        }
        switch (area) {
            case "스포츠 선수": {
                List<Person> list = new ArrayList();
                String[] name = {"기성용", "김민재", "김연경", "류현진", "박세리", "박지성", "박찬호", "박태환", "손흥민", "조현우"};
                String[] photo = {"/Category/스포츠/기성용.jpg", "/Category/스포츠/김민재.jpg", "/Category/스포츠/김연경.jpg", "/Category/스포츠/류현진.jpg", "/Category/스포츠/박세리.jpg", "/Category/스포츠/박지성.jpg", "/Category/스포츠/박찬호.jpg", "/Category/스포츠/박태환.jpg", "/Category/스포츠/손흥민.jpg", "/Category/스포츠/조현우.jpg"};
                for (int i : randomNumbers) {
                    list.add(new Person(name[i], photo[i]));
                }
                return list;
            }
            case "배우": {
                List<Person> list = new ArrayList();
                String[] name = {"강동원", "김태희", "마동석", "손예진", "송혜교", "신민아", "유해진", "이정재", "전지현", "최민식"};
                String[] photo = {"/Category/배우/강동원.jpg", "/Category/배우/김태희.jpg", "/Category/배우/마동석.jpg", "/Category/배우/손예진.jpg", "/Category/배우/송혜교.jpg", "/Category/배우/신민아.jpg", "/Category/배우/유해진.jpg", "/Category/배우/이정재.jpg", "/Category/배우/전지현.jpg", "/Category/배우/최민식.jpg"};
                for (int i : randomNumbers) {
                    list.add(new Person(name[i], photo[i]));
                }
                return list;

            }
            case "가수": {
                List<Person> list = new ArrayList();
                String[] name = {"뷔", "송민호", "싸이", "아이유", "윤도현", "이선희", "장원영", "지수", "키", "홍진영"};
                String[] photo = {"/Category/가수/뷔.jpg", "/Category/가수/송민호.jpg", "/Category/가수/싸이.jpg", "/Category/가수/아이유.jpg", "/Category/가수/윤도현.jpg", "/Category/가수/이선희.jpg", "/Category/가수/장원영.jpg", "/Category/가수/지수.jpg", "/Category/가수/키.jpg", "/Category/가수/홍진영.jpg"};
                for (int i : randomNumbers) {
                    list.add(new Person(name[i], photo[i]));
                }
                return list;

            }
            case "예능": {
                List<Person> list = new ArrayList();
                String[] name = {"강호동", "김구라", "김민경", "김병만", "김준현", "박나래", "신동엽", "유재석", "이경규", "이수근"};
                String[] photo = {"/Category/예능/강호동.jpg", "/Category/예능/김구라.jpg", "/Category/예능/김민경.jpg", "/Category/예능/김병만.jpg", "/Category/예능/김준현.jpg", "/Category/예능/박나래.jpg", "/Category/예능/신동엽.jpg", "/Category/예능/유재석.jpg", "/Category/예능/이경규.jpg", "/Category/예능/이수근.jpg"};
                for (int i : randomNumbers) {
                    list.add(new Person(name[i], photo[i]));
                }
                return list;

            }
        }
        return this.peopleList;
    }
}
