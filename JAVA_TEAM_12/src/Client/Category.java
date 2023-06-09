package Client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Category {
	String area;
	List<Person> peopleList = new ArrayList();
	public Category() {
		// TODO Auto-generated constructor stub
	}
	public List<Person> getArea(String area, int num) {
		Random r= new Random();
		int range = 10; // 랜덤 숫자의 범위 (0 ~ 9)
		Set<Integer> randomNumbers = new HashSet();
		while (randomNumbers.size() < num) {
	            int randomNumber = r.nextInt(range);
	            randomNumbers.add(randomNumber);
	        }
		switch (area) {
		case "스포츠 선수": {
			String name[] = {"손흥민", "박지성", "기성용", "이청용", "김보경", "박주영", "황희찬", "차범근", "차두리", "김민재"};
			int age [] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	        for (int i : randomNumbers) {
		        	this.peopleList.add(new Person(name[i], age[i]));
	        }
			break;
		}case "배우": {
			String name[] = {"이승기", "한석규", "소주연", "김민재", "이종석", "김우빈", "한소희", "송강", "채수빈", "운여정"};
			int age [] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; 
	        for (int i : randomNumbers) {
	        	this.peopleList.add(new Person(name[i], age[i]));
        };
			
		}case "가수": {
			String name[] = {"지드래곤", "뷔", "태양", "대성", "진", "제니", "수지", "지수", "카리나", "카즈하"};
			int age [] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; 
	        for (int i : randomNumbers) {
	        	this.peopleList.add(new Person(name[i], age[i]));
        }
			break;
			
		}case "정치인": {
			String name[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
			int age [] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; 
	        for (int i : randomNumbers) {
	        	this.peopleList.add(new Person(name[i], age[i]));
        }	
			break;
			
			}
		}
		return this.peopleList;
	}
}
