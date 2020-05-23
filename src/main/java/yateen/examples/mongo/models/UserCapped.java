package yateen.examples.mongo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userCapped")
public class UserCapped {


	private String id;
	private String userName;
	private int age;
	
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", age=" + age + "]";
	}
	
	
}
