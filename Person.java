package fitnessCalculator;

public class Person {
	private UnitType e;
	private String firstName;
	private String lastName;
	private int age;
	private int gender;
	private int weightLbs;
	private double weightKg;
	private int heightFt;
	private int heightInch;
	private double heightCm; 
	private int bustSizeInch;
	private int waistSizeInch;
	private int hipHeightInch;
	private int hipSizeInch;
	private double bustSizeCm;
	private double waistSizeCm;
	private double hipHeightCm;
	private double hipSizeCm;
	
	/**
	 * This is the constructor for US unit type
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param weightLbs
	 * @param heightFt
	 * @param heightInch
	 * @param bustSizeInch
	 * @param waistSizeInch
	 * @param hipHeightInch
	 * @param hipSizeInch
	 */
	public Person(String firstName, String lastName, int age, int gender, int weightLbs,
			int heightFt, int heightInch, int bustSizeInch, int waistSizeInch, int hipHeightInch, int hipSizeInch) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.weightLbs = weightLbs;
		this.heightFt = heightFt;
		this.heightInch = heightInch;
		this.bustSizeInch = bustSizeInch;
		this.waistSizeInch = waistSizeInch;
		this.hipHeightInch = hipHeightInch;
		this.hipSizeInch = hipSizeInch;
		this.e = UnitType.US;
		
	}
	
	/**
	 * This is the constructor for the Metric unit type
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param weightKg
	 * @param heightCm
	 * @param bustSizeCm
	 * @param waistSizeCm
	 * @param hipHeightCm
	 * @param hipSizeCm
	 */
	public Person(String firstName, String lastName, int age, int gender, double weightKg,
			double heightCm, double bustSizeCm, double waistSizeCm, double hipHeightCm, double hipSizeCm) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.weightKg = weightKg;
		this.heightCm = heightCm;
		this.bustSizeCm = bustSizeCm;
		this.waistSizeCm = waistSizeCm;
		this.hipHeightCm = hipHeightCm;
		this.hipSizeCm = hipSizeCm;
		this.e = UnitType.METRIC;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * @return the weightLbs
	 */
	public int getWeightLbs() {
		return weightLbs;
	}
	/**
	 * @return the weightKg
	 */
	public double getWeightKg() {
		return weightKg;
	}
	/**
	 * @return the heightFt
	 */
	public int getHeightFt() {
		return heightFt;
	}
	/**
	 * @return the heightInch
	 */
	public int getHeightInch() {
		return heightInch;
	}
	/**
	 * @return the heightCm
	 */
	public double getHeightCm() {
		return heightCm;
	}

	/**
	 * @return the bustSizeInch
	 */
	public int getBustSizeInch() {
		return bustSizeInch;
	}

	/**
	 * @return the waistSizeInch
	 */
	public int getWaistSizeInch() {
		return waistSizeInch;
	}

	/**
	 * @return the hipHeightInch
	 */
	public int getHipHeightInch() {
		return hipHeightInch;
	}

	/**
	 * @return the hipSizeInch
	 */
	public int getHipSizeInch() {
		return hipSizeInch;
	}

	/**
	 * @return the bustSizeCm
	 */
	public double getBustSizeCm() {
		return bustSizeCm;
	}

	/**
	 * @return the waistSizeCm
	 */
	public double getWaistSizeCm() {
		return waistSizeCm;
	}

	/**
	 * @return the hipHeightCm
	 */
	public double getHipHeightCm() {
		return hipHeightCm;
	}

	/**
	 * @return the hipSizeCm
	 */
	public double getHipSizeCm() {
		return hipSizeCm;
	}

	/**
	 * @param bustSizeInch the bustSizeInch to set
	 */
	public void setBustSizeInch(int bustSizeInch) {
		this.bustSizeInch = bustSizeInch;
	}

	/**
	 * @param waistSizeInch the waistSizeInch to set
	 */
	public void setWaistSizeInch(int waistSizeInch) {
		this.waistSizeInch = waistSizeInch;
	}

	/**
	 * @param hipHeightInch the hipHeightInch to set
	 */
	public void setHipHeightInch(int hipHeightInch) {
		this.hipHeightInch = hipHeightInch;
	}

	/**
	 * @param hipSizeInch the hipSizeInch to set
	 */
	public void setHipSizeInch(int hipSizeInch) {
		this.hipSizeInch = hipSizeInch;
	}

	/**
	 * @param bustSizeCm the bustSizeCm to set
	 */
	public void setBustSizeCm(double bustSizeCm) {
		this.bustSizeCm = bustSizeCm;
	}

	/**
	 * @param waistSizeCm the waistSizeCm to set
	 */
	public void setWaistSizeCm(double waistSizeCm) {
		this.waistSizeCm = waistSizeCm;
	}

	/**
	 * @param hipHeightCm the hipHeightCm to set
	 */
	public void setHipHeightCm(double hipHeightCm) {
		this.hipHeightCm = hipHeightCm;
	}

	/**
	 * @param hipSizeCm the hipSizeCm to set
	 */
	public void setHipSizeCm(double hipSizeCm) {
		this.hipSizeCm = hipSizeCm;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * @param weightLbs the weightLbs to set
	 */
	public void setWeightLbs(int weightLbs) {
		this.weightLbs = weightLbs;
	}
	/**
	 * @param weightKg the weightKg to set
	 */
	public void setWeightKg(double weightKg) {
		this.weightKg = weightKg;
	}
	/**
	 * @param heightFt the heightFt to set
	 */
	public void setHeightFt(int heightFt) {
		this.heightFt = heightFt;
	}
	/**
	 * @param heightInch the heightInch to set
	 */
	public void setHeightInch(int heightInch) {
		this.heightInch = heightInch;
	}
	/**
	 * @param heightCm the heightCm to set
	 */
	public void setHeightCm(double heightCm) {
		this.heightCm = heightCm;
	}
	
	/**
	 * @return the e
	 */
	public UnitType getUnitType() {
		return e;
	}
	

}
