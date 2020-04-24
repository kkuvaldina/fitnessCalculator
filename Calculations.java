package fitnessCalculator;

public class Calculations {

	private static int heightToTotalInches(int feet, int inches) {
		return (feet * 12) + inches;
	}

	/**
	 * Converts Centimeters to Meters for equations
	 * 
	 * @param CM
	 * @return Meters
	 */
	private static double convertCmToM(double CM) {
		return CM / 100;
	}

	/**
	 * This will calculate the users Body mass index
	 * 
	 * @param person this will get what is needed from the person in the equation
	 * @return BMI of person
	 */
	public static double bmiCalculator(Person person) {

		if (person.getUnitType() == UnitType.US) {
			double totalInches = heightToTotalInches(person.getHeightFt(), person.getHeightInch());
			double weight = (person.getWeightLbs() * 703);
			double height = totalInches * totalInches;
			double bmiUs = weight / height;
			return Math.round(bmiUs * 100) / 100.0;

		} else {
			double meters = convertCmToM(person.getHeightCm());
			double meterSquare = meters * meters;
			double bmiMetric = (person.getWeightKg() / (meterSquare));
			return Math.round(bmiMetric * 100) / 100.0;
		}

	}

	/**
	 * This will calculate the users Basal metabolic rate
	 * 
	 * @return
	 */
	public static int bmrCalculator(Person person) {
		int bmr = 0;

		if (person.getUnitType() == UnitType.US) {
			// Checking woman BMR in US unit type
			if (person.getGender() == 0) {
				bmr = (int) ((4.536 * person.getWeightLbs())
						+ (15.88 * heightToTotalInches(person.getHeightFt(), person.getHeightInch()))
						- (5 * person.getAge()) - 161);
			}
			// Checking men BMR in US unit type
			else {

				bmr = (int) ((4.536 * person.getWeightLbs())
						+ (15.88 * heightToTotalInches(person.getHeightFt(), person.getHeightInch()))
						- (5 * person.getAge()) + 5);

			}

		}

		else {
			// Checking woman BMR in Metric unit type
			if (person.getGender() == 0) {
				bmr = (int) ((10 * person.getWeightKg()) + (6.25 * person.getHeightCm()) - (5 * person.getAge()) - 161);
			}
			// Checking men BMR in Metric unit type
			else {
				bmr = (int) ((10 * person.getWeightKg()) + (6.25 * person.getHeightCm()) - (5 * person.getAge()) + 5);
			}

		}

		return bmr;
	}

	/**
	 * This will generate the body type of the user
	 * 
	 * @return String of what the body type category is
	 */
	public static String bodyTypeCalculator(Person person) {
		String bodyType = null;
		if (person.getUnitType() == UnitType.US) {
			if ((person.getHipSizeInch() - person.getBustSizeInch()) >= 3.6
					&& (person.getHipSizeInch() - person.getWaistSizeInch()) < 9) {
				bodyType = "Triangle";
			} else if ((person.getBustSizeInch() - person.getHipSizeInch()) >= 3.6
					&& (person.getBustSizeInch() - person.getWaistSizeInch()) < 9) {
				bodyType = "Inverted Triangle";
			} else if ((person.getBustSizeInch() - person.getHipSizeInch()) > 1
					&& (person.getBustSizeInch() - person.getHipSizeInch()) < 10
					&& (person.getBustSizeInch() - person.getWaistSizeInch()) >= 9) {
				bodyType = "Top Hourglass";
			} else if ((person.getHipSizeInch() - person.getBustSizeInch()) >= 3.6
					&& (person.getHipSizeInch() - person.getBustSizeInch()) < 10
					&& (person.getHipSizeInch() - person.getWaistSizeInch()) >= 9
					&& (person.getHipHeightInch() / person.getWaistSizeInch()) < 1.193) {
				bodyType = "Bottom Hourglass";
			} else if ((person.getBustSizeInch() - person.getHipSizeInch()) <= 1
					&& (person.getHipSizeInch() - person.getBustSizeInch()) < 3.6
					&& (person.getBustSizeInch() - person.getWaistSizeInch()) >= 9
					|| (person.getHipSizeInch() - person.getWaistSizeInch()) >= 10) {
				bodyType = "HourGlass";
			} else if ((person.getHipSizeInch() - person.getBustSizeInch()) > 2
					&& (person.getHipSizeInch() - person.getWaistSizeInch()) >= 7
					&& (person.getHipHeightInch() / person.getWaistSizeInch()) >= 1.193) {
				bodyType = "Spoon";
			} else if ((person.getHipSizeInch() - person.getBustSizeInch()) < 3.6
					&& (person.getBustSizeInch() - person.getHipSizeInch()) < 3.6
					&& (person.getBustSizeInch() - person.getWaistSizeInch()) < 9
					&& (person.getHipSizeInch() - person.getWaistSizeInch()) < 10) {
				bodyType = "Rectangle";
			}
		} else {

			if ((person.getHipSizeCm() - person.getBustSizeCm()) >= 9.144
					&& (person.getHipSizeCm() - person.getWaistSizeCm()) < 22.86) {
				bodyType = "Triangle";
			} else if ((person.getBustSizeCm() - person.getHipSizeCm()) >= 9.144
					&& (person.getBustSizeCm() - person.getWaistSizeCm()) < 9) {
				bodyType = "Inverted Triangle";
			} else if ((person.getBustSizeCm() - person.getHipSizeCm()) > 2.54
					&& (person.getBustSizeCm() - person.getHipSizeCm()) < 25.4
					&& (person.getBustSizeCm() - person.getWaistSizeCm()) >= 22.86) {
				bodyType = "Top Hourglass";
			} else if ((person.getHipSizeCm() - person.getBustSizeCm()) >= 9.144
					&& (person.getHipSizeCm() - person.getBustSizeCm()) < 25.4
					&& (person.getHipSizeCm() - person.getWaistSizeCm()) >= 22.86
					&& (person.getHipHeightCm() / person.getWaistSizeCm()) < 3.03) {
				bodyType = "Bottom Hourglass";
			} else if ((person.getBustSizeCm() - person.getHipSizeCm()) <= 2.54
					&& (person.getHipSizeCm() - person.getBustSizeCm()) < 9.144
					&& (person.getBustSizeCm() - person.getWaistSizeCm()) >= 22.86
					|| (person.getHipSizeCm() - person.getWaistSizeCm()) >= 25.4) {
				bodyType = "HourGlass";
			} else if ((person.getHipSizeCm() - person.getBustSizeCm()) > 5.08
					&& (person.getHipSizeCm() - person.getWaistSizeCm()) >= 17.78
					&& (person.getHipHeightCm() / person.getWaistSizeCm()) >= 3.03) {
				bodyType = "Spoon";
			} else if ((person.getHipSizeCm() - person.getBustSizeCm()) < 9.144
					&& (person.getBustSizeCm() - person.getHipSizeCm()) < 9.144
					&& (person.getBustSizeCm() - person.getWaistSizeCm()) < 22.86
					&& (person.getHipSizeCm() - person.getWaistSizeCm()) < 25.4) {
				bodyType = "Rectangle";
			}
		}

		return bodyType;
	}

	/**
	 * This will generate a healthy weight based off the users height at weight
	 * 
	 * @return Healthy weight for the user to aim for
	 */
	public static String healthyWeightCalculator(Person person) {
		// This takes the persons current bmi and subtracts the lowest and highest bmi
		// divided by 100 so it can be a percentage
		double lowBmi = (Calculations.bmiCalculator(person) - 18.5) / 100;
		double highBmi = (Calculations.bmiCalculator(person) - 25) / 100;

		//Us calculator
		if (person.getUnitType() == UnitType.US) {
			if (highBmi < 0 || lowBmi < 0) {
				// This takes into account if the person's bmi is below the highest bmi
				// percentage
				if (highBmi < 0) {
					double lowWeight1 = person.getWeightLbs() * lowBmi;
					double highWeight1 = person.getWeightLbs() * Math.abs(highBmi);

					double lowWeight2 = person.getWeightLbs() - (lowWeight1 * 3);
					double highWeight2 = person.getWeightLbs() + (highWeight1 * 4);
					return Math.round(lowWeight2 * 100) / 100 + "lbs - " + Math.round(highWeight2 * 100) / 100 + "lbs";
				}
				// This takes into account if the person's bmi is below the lowest bmi
				// percentage
				if (lowBmi < 0) {
					double lowWeight1 = person.getWeightLbs() * Math.abs(lowBmi);
					double highWeight1 = person.getWeightLbs() * highBmi;

					double lowWeight2 = person.getWeightLbs() + (lowWeight1 * 3);
					double highWeight2 = person.getWeightLbs() - (highWeight1 * 4);
					return Math.round(lowWeight2 * 100) / 100 + "lbs - " + Math.round(highWeight2 * 100) / 100 + "lbs";
				}

			}
			// This takes into account for the person's bmi is above the healthy bmi
			// percentages
			else {
				double lowWeight1 = person.getWeightLbs() * lowBmi;
				double highWeight1 = person.getWeightLbs() * highBmi;

				double lowWeight2 = person.getWeightLbs() - (lowWeight1 * 3);
				double highWeight2 = person.getWeightLbs() - (highWeight1 * 4);

				return Math.round(lowWeight2 * 100) / 100 + "Lbs - " + Math.round(highWeight2 * 100) / 100 + "Lbs";
			}
			// Metric calculator
		} else {
			if (highBmi < 0 || lowBmi < 0) {
				// This takes into account if the person's bmi is below the highest bmi
				// percentage
				if (highBmi < 0) {
					double lowWeight1 = person.getWeightKg() * lowBmi;
					double highWeight1 = person.getWeightKg() * Math.abs(highBmi);

					double lowWeight2 = person.getWeightKg() - (lowWeight1 * 3);
					double highWeight2 = person.getWeightKg() + (highWeight1 * 4);
					return Math.round(lowWeight2 * 100) / 100 + "Kg - " + Math.round(highWeight2 * 100) / 100 + "Kg";
				}
				// This takes into account if the person's bmi is below the lowest bmi
				// percentage
				if (lowBmi < 0) {
					double lowWeight1 = person.getWeightKg() * Math.abs(lowBmi);
					double highWeight1 = person.getWeightKg() * highBmi;

					double lowWeight2 = person.getWeightKg() + (lowWeight1 * 3);
					double highWeight2 = person.getWeightKg() - (highWeight1 * 4);
					return Math.round(lowWeight2 * 100) / 100 + "Kg - " + Math.round(highWeight2 * 100) / 100 + "Kg";
				}

			}
			// This takes into account for the person's bmi is above the healthy bmi
			// percentages
			else {
				double lowWeight1 = person.getWeightKg() * lowBmi;
				double highWeight1 = person.getWeightKg() * highBmi;

				double lowWeight2 = person.getWeightKg() - (lowWeight1 * 3);
				double highWeight2 = person.getWeightKg() - (highWeight1 * 4);
				return Math.round(lowWeight2 * 100) / 100 + "Kg - " + Math.round(highWeight2 * 100) / 100 + "Kg";
			}
		}

		return null;

	}

}