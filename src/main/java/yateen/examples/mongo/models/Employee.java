package yateen.examples.mongo.models;

public class Employee {

	int id;
	int age;
	
	public Employee()
	{
		
	}
	
	public Employee(int id)
	{
		this.id = id;
	}
	
	public Employee(int id, int age)
	{
		this.id = id;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", age=" + age + "]";
	}
}

