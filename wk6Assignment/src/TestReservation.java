import java.text.SimpleDateFormat;
import java.util.Date;

public class TestReservation {

	private static String datePattern = "MMM dd, yyyy";
	private static SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
	
	public static void main(String[] args) {

		testConstructorAndGetters();
		testSettersAndGetters();
		testCalculateReservationNumberOfDays();
		testCalculateReservationBillAmount();
		
	}

	public static void testConstructorAndGetters() {
		System.out.println();
		System.out.println("Testing Constructor and Getters");
		System.out.println("--------------------------------");
		
		Reservation r1 =  new Reservation(1, "RoomWBath", "Feb 18, 2025", "Feb 21, 2025");
		Reservation r2 = new Reservation(7, "RoomWBath", "Feb 18, 2025", "Feb 21, 2025");
		
		Assert.assertNotEqualsUUID(r1.getReservationID(), r2.getReservationID());
		Assert.assertEqualsDate(r1.getReservationDate(), new Date());
		Assert.assertNotEqualsInt(r1.getGuestID(), r2.getGuestID());
		Assert.assertNotEqualsString(r2.getReservationStartDate(), r2.getReservationEndDate());
		Assert.assertEqualsString(r1.getRoomType(), r2.getRoomType());
	}

	public static void testSettersAndGetters() {
		System.out.println();
		System.out.println("Testing Setters and Getters");
		System.out.println("----------------------------");
		
		Reservation r1 = new Reservation(7, "RoomWBath", "Feb 16, 2025", "Feb 19, 2025");
		
		// Old Reservation Information
		int previousGuestID = r1.getGuestID();
		String previousRoomType = r1.getRoomType();
		Date previousReservationDate = r1.getReservationDate();
		String previousReservationStartDate = r1.getReservationStartDate();
		String previousReservationEndDate = r1.getReservationEndDate();

		// New Reservation Information
        r1.setGuestID(3);
        r1.setRoom("NormalRoom");
        r1.setReservationStartDate("Mar 01, 2025");
        r1.setReservationEndDate("Mar 25, 2025");

        // Test if changes are made
        Assert.assertNotEqualsInt(r1.getGuestID(), previousGuestID); // 
        Assert.assertNotEqualsString(r1.getRoomType(), previousRoomType);
        Assert.assertNotEqualsDate(r1.getReservationDate(), previousReservationDate);
        Assert.assertNotEqualsString(r1.getReservationStartDate(), previousReservationStartDate);
        Assert.assertNotEqualsString(r1.getReservationEndDate(), previousReservationEndDate);
		
	}

	public static void testCalculateReservationNumberOfDays() {
		System.out.println();
		System.out.println("Testing Calculate Reservation Number of Days");
		System.out.println("---------------------------------------------");
		
		long zeroDayStay = 0;
		long threeDayStay = 3;
		try {
			// Test zero day stay
			Reservation r1 =  new Reservation(1, "NormalRoom", "Feb 16, 2025", "Feb 16, 2025");
			Assert.assertEqualsLong(r1.calculateReversationNumberOfDays(), zeroDayStay);
			
			// Test three day stay
			Reservation r2 =  new Reservation(2, "RoomWBath", "Feb 16, 2025", "Feb 19, 2025");
			Assert.assertEqualsLong(r2.calculateReversationNumberOfDays(), threeDayStay);

			// Test different number of days with other reservation
			Reservation r3 = new Reservation(3, "RoomWView", "Apr 1, 2025", "Apr 5, 2025");
			Assert.assertNotEqualsLong(r2.calculateReversationNumberOfDays(), r3.calculateReversationNumberOfDays());
			
			Reservation r4 = new Reservation(4, "NormalRoom", "Feb 1, 2025", "Feb 1, 2026");
			Assert.assertNotEqualsLong(r4.calculateReversationNumberOfDays(), (long)365);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void testCalculateReservationBillAmount() {
		System.out.println();
		System.out.println("Testing Calculate Reservation Bill Amount");
		System.out.println("------------------------------------------");
		
		double normalRoomPrice = 125.0;
		double roomWViewPrice = 175.0;
		double roomWBathPrice = 200.0;
		
		try {
			// Normal room bill for three days
			Reservation r4 = new Reservation(4, "NormalRoom", "Feb 17, 2025", "Feb 20, 2025");
			Assert.assertEqualsDouble(r4.calculateReservationBillAmount(), r4.calculateReversationNumberOfDays() * normalRoomPrice);
			
			// Normal room bill for two weeks
			r4.setReservationEndDate("Mar 03, 2025");
			Assert.assertEqualsDouble(r4.calculateReservationBillAmount(), r4.calculateReversationNumberOfDays() * normalRoomPrice);
			
			// Normal room bill for one month (30 days)
			r4.setReservationEndDate("Mar 19, 2025");
			Assert.assertEqualsDouble(r4.calculateReservationBillAmount(), r4.calculateReversationNumberOfDays() * normalRoomPrice);
			
			// Test Room with View Bill
			Reservation r5 = new Reservation(5, "RoomWView", "Feb 17, 2025", "Feb 20, 2025");
			Assert.assertEqualsDouble(r5.calculateReservationBillAmount(), r5.calculateReversationNumberOfDays() * roomWViewPrice);
			
			// Room with view bill for two weeks
			r5.setReservationEndDate("Mar 03, 2025");
			Assert.assertEqualsDouble(r5.calculateReservationBillAmount(), r5.calculateReversationNumberOfDays() * roomWViewPrice);
			
			// Room with view bill for one month (30 days)
			r5.setReservationEndDate("Mar 19, 2025");
			Assert.assertEqualsDouble(r5.calculateReservationBillAmount(), r5.calculateReversationNumberOfDays() * roomWViewPrice);
			
			// Test Room with Bath Bill
			Reservation r6 = new Reservation(6, "RoomWBath", "Feb 17, 2025", "Feb 20, 2025");
			Assert.assertEqualsDouble(r6.calculateReservationBillAmount(), r6.calculateReversationNumberOfDays() * roomWBathPrice);
			
			// Room with bath bill for two weeks
			r6.setReservationEndDate("Mar 03, 2025");
			Assert.assertEqualsDouble(r6.calculateReservationBillAmount(), r6.calculateReversationNumberOfDays() * roomWBathPrice);
			
			// Room with bath bill for one month (30 days)
			r6.setReservationEndDate("Mar 19, 2025");
			Assert.assertEqualsDouble(r6.calculateReservationBillAmount(), r6.calculateReversationNumberOfDays() * roomWBathPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
