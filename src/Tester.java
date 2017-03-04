import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Tester {
	public static void main(String[] args) throws Exception {
		Classroom[] classrooms = new Classroom[5];
		classrooms[2] = new Classroom(103, 60);
		classrooms[4] = new Classroom(104, 28);
		classrooms[1] = new Classroom(106, 65);
		classrooms[3] = new Classroom(108, 60);
		classrooms[0] = new Classroom(110, 98);
		
		Classroom[] classrooms2 = new Classroom[5];
		classrooms2[2] = new Classroom(103, 60);
		classrooms2[4] = new Classroom(104, 28);
		classrooms2[1] = new Classroom(106, 65);
		classrooms2[3] = new Classroom(108, 60);
		classrooms2[0] = new Classroom(110, 98);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] students = new int[n];
		String[] vals = br.readLine().split(" ");
		for (int i = 0; i < students.length; i++) {
			students[i] = Integer.parseInt(vals[i]);
		}
		Arrays.sort(students);
		arrangeStudents(classrooms, students);
		arrangeStudents(classrooms2, students);
		System.out.println(Arrays.toString(classrooms));		
		System.out.println(Arrays.toString(classrooms2));
	}

	public static void arrangeStudents(Classroom[] classrooms, int[] students) {
		for (int i = students.length - 1; i >= 0; i--) {
			if (students[i] > 0) {
				int initialStudents = students[i];
				int classroom = 0;
				ArrayList<Integer> usedClasses = new ArrayList<Integer>(classrooms.length);
				ArrayList<Character> usedSide = new ArrayList<Character>(classrooms.length);
				ArrayList<Integer> occupiedSeats = new ArrayList<Integer>(classrooms.length);
				while (students[i] > 0 && (classroom = checkClassAvailability(classrooms, usedClasses)) >= 0) {
					usedClasses.add(classroom);
					int leftCapacity = classrooms[classroom].getLCapacity();
					int rightCapacity = classrooms[classroom].getRCapacity();
					if (leftCapacity >= rightCapacity) {
						usedSide.add('L');
						classrooms[classroom].setLeftOccupiedBy(i, Math.min(students[i], leftCapacity));
						occupiedSeats.add(Math.min(students[i], leftCapacity));
						students[i] -= Math.min(students[i], leftCapacity);
					} else {
						usedSide.add('R');
						classrooms[classroom].setRightOccupiedBy(i, Math.min(students[i], rightCapacity));
						occupiedSeats.add(Math.min(students[i], rightCapacity));
						students[i] -= Math.min(students[i], rightCapacity);
					}
				}
				if (students[i] != 0) {
					System.out.println("We can't accomodate " + initialStudents + " in this slot.");
					for (int j = 0; j < usedSide.size(); j++) {
						char side = usedSide.remove(0);
						int classUsed = usedClasses.remove(0);
						int occupiedSeatsCnt = occupiedSeats.remove(0);
						if (side == 'L')
							classrooms[classUsed].releseLeftOccupied(occupiedSeatsCnt);
						else
							classrooms[classUsed].releseRightOccupied(occupiedSeatsCnt);
					}
					students[i] = initialStudents;
				}
			}
		}
	}

	private static int checkClassAvailability(Classroom[] classrooms, ArrayList<Integer> usedClasses) {
		for (int i = 0; i < classrooms.length; i++) {
			if ((classrooms[i].getLCapacity() > 0 || classrooms[i].getRCapacity() > 0) && !usedClasses.contains(i))
				return i;
		}
		return -1;
	}
}
