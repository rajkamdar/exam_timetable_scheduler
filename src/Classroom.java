import java.util.ArrayList;

public class Classroom {
	private int roomNo;
	private int lCapacity;
	private int rCapacity;
	private ArrayList<Integer> leftOccupiedBy;
	private ArrayList<Integer> rightOccupiedBy;

	public Classroom(int roomNo, int capacity) {
		this.roomNo = roomNo;
		this.rCapacity = this.lCapacity = capacity;
		this.leftOccupiedBy = new ArrayList<Integer>();
		this.rightOccupiedBy = new ArrayList<Integer>();
	}

	public void releseLeftOccupied(int occupiedSeats) {
		this.lCapacity += occupiedSeats;
		this.leftOccupiedBy.remove(this.leftOccupiedBy.size() - 1);
	}

	public void releseRightOccupied(int occupiedSeats) {
		this.rCapacity += occupiedSeats;
		this.rightOccupiedBy.remove(this.rightOccupiedBy.size() - 1);
	}

	public void setLeftOccupiedBy(int leftOccupiedBy, int seats) {
		this.leftOccupiedBy.add(leftOccupiedBy);
		this.lCapacity -= seats;
	}

	public void setRightOccupiedBy(int rightOccupiedBy, int seats) {
		this.rightOccupiedBy.add(rightOccupiedBy);
		this.rCapacity -= seats;		
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getLCapacity() {
		return lCapacity;
	}

	public int getRCapacity() {
		return this.rCapacity;
	}

	public int getRoomNo() {
		return this.roomNo;
	}

	public ArrayList<Integer> getLeftOccupiedBy() {
		return leftOccupiedBy;
	}

	public ArrayList<Integer> getRightOccupiedBy() {
		return rightOccupiedBy;
	}

	@Override
	public String toString() {
		return "Room No : " + getRoomNo() + "\nLCapacity : " + getLCapacity() + "\nRCapacity : " + getRCapacity()
				+ "\nLeft side Occupied by : " + getLeftOccupiedBy() + "\nRight side Occupied by : "
				+ getRightOccupiedBy()+"\n\n";
	}
}
