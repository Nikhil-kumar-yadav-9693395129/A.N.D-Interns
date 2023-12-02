import java.util.ArrayList;
import java.util.List;

    // Room class represents a hotel room
    class Room {
        private int roomNumber;
        private boolean isOccupied;
        private String guestName;

        public Room(int roomNumber) {
            this.roomNumber = roomNumber;
            this.isOccupied = false;
            this.guestName = null;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public void setOccupied(boolean occupied) {
            isOccupied = occupied;
        }

        public String getGuestName() {
            return guestName;
        }

        public void setGuestName(String guestName) {
            this.guestName = guestName;
        }
    }

    // Hotel class represents a hotel with rooms
    class Hotel {
        private List<Room> rooms;

        public Hotel(int numRooms) {
            rooms = new ArrayList<>();
            for (int i = 1; i <= numRooms; i++) {
                rooms.add(new Room(i));
            }
        }

        public void showRoomStatus() {
            System.out.println("Room Status:");
            for (Room room : rooms) {
                System.out.println("Room " + room.getRoomNumber() + ": " +
                        (room.isOccupied() ? "Occupied by " + room.getGuestName() : "Available"));
            }
        }

        public boolean reserveRoom(int roomNumber, String guestName) {
            Room room = findRoom(roomNumber);
            if (room != null && !room.isOccupied()) {
                room.setOccupied(true);
                room.setGuestName(guestName);
                System.out.println("Room " + roomNumber + " reserved for " + guestName);
                return true;
            } else {
                System.out.println("Room " + roomNumber + " is not available for reservation.");
                return false;
            }
        }

        private Room findRoom(int roomNumber) {
            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber) {
                    return room;
                }
            }
            System.out.println("Room " + roomNumber + " not found.");
            return null;
        }
    }

    // Main class to test the Hotel Reservation System
    public class HotelReservationSystem {
        public static void main(String[] args) {
            // Create a hotel with 5 rooms
            Hotel hotel = new Hotel(5);

            // Show initial room status
            hotel.showRoomStatus();

            // Reserve rooms
            hotel.reserveRoom(3, "Nikhil Kumar Yadav");
            hotel.reserveRoom(1, "Anindita Mahata");

            // Show updated room status
            hotel.showRoomStatus();
        }
    }



