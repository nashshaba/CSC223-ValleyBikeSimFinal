import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ValleyBikeSim {
	
	/**
	 * Fields related to stations.
	 */
	private ArrayList<Station> stationList;
	public static Station newStation;
	
	public ValleyBikeSim() {
		stationList = new ArrayList<Station>();
	}
	
	public static void main(String[] args) {
		ValleyBikeSim mySim = new ValleyBikeSim();
		mySim.read();
		
		System.out.println("Welcome to the ValleyBike Simulator.");
		Scanner scanner = new Scanner(System.in);
		
		try {while(true) {
			printMenu();
			System.out.println("\nPlease enter a number (0-6): ");
			
			int answer = scanner.nextInt();

			if(answer == 0) {
				System.out.println("\nThank you for using Valley Bike Simulator!");
				break;
			} else if(answer == 1) {
				mySim.printStation();
			} else if(answer==2) {
				mySim.addStation();
				//continue;
			} else if(answer==3) {
				mySim.save();
				
			} else if(answer ==4) {
				
			} else if(answer ==5) {
//				System.out.println("\nEnter the file name (including extension) of the file located in data-files:");
//				String rideFile = userInput.nextLine();
				
			} else if(answer == 6) {
				
			} else {
				System.out.println("\nInvalid input, please select a number within the 0-6 range.\n");
			}
			
		}
		scanner.close();
	
			
		}catch(Exception ex)
		{
		    // do something smarter here!
		    ex.printStackTrace();
		}
		
			
	}
	
	public void read() {
		File path = new File("data-files/station-data.csv");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			br.readLine();
			
			while((line= br.readLine()) !=null) {
				String [] details = line.split(",");
				String name = details[1];
				int bikes = Integer.valueOf(details[2]);
				int peds = Integer.valueOf(details[3]);
				int avDoc = Integer.valueOf(details[4]);
				int mReq = Integer.valueOf(details[5]);
				int cap = Integer.valueOf(details[6]);
				String address = details[8];
				boolean kiosk = toBool(details[7]);
	             
	            int id = Integer.valueOf(details[0]);
	            stationList.add(new Station(id, name,bikes,peds,avDoc,mReq,cap,kiosk,address));
			}
		}catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	public void printStation() {
		System.out.println("ID\tBikes\tPedelec AvDocs\tMainReq\tCap\tKiosk\tName - Address");
		Collections.sort(stationList, new idCompare());
		for (Station stat : stationList) {
			String longName = stat.id + "\t"+stat.bikes+"\t"+stat.pedelecs+"\t"+stat.availableDocks+"\t"+stat.mReq+"\t"+stat.capacity+"\t"+stat.kiosk+"\t"+stat.name+" - "+stat.address;
			System.out.println(longName);
		}
		
	}
	
	public void addStation() {
		
		newStation = new Station(0, null, 0, 0, 0, 0, 0, false, null);
		System.out.println("Please enter the following information:");
		while(true) {
			System.out.println("Enter Station ID: ");
			Scanner userInput = new Scanner(System.in);
			int inputID = userInput.nextInt();	
			try {
				if(checkID(stationList,inputID)) {
					System.out.println("A station with that ID already exists. Please enter another ID.");
					continue;
					} else {
						newStation.setID(inputID);
					}
				} catch(NumberFormatException e) {
				System.out.println("Invalid input. Please start over.");
			}
			
			System.out.println("Station Name: ");
			String inputName = userInput.next();
			newStation.setName(inputName);
			
			System.out.println("What is the capacity for the new station (0-25):");
			int inputCapacity = userInput.nextInt();
			try {
				if(inputCapacity > 25 || inputCapacity < 0) {
					System.out.println("Invalid capacity. Restart.");
					continue;
				} else {
					newStation.setCapacity(inputCapacity);
				}
			} catch(NumberFormatException e) {
				System.out.println("Invalid input. Restart.");
			}
			
			System.out.println("Enter the number of total bikes at this station (range: 0-" + newStation.capacity + "): ");
			int inputBikes = userInput.nextInt();
			try {
				
				if(inputBikes > newStation.capacity) {
					System.out.println("The number of bikes specified exceeds the capacity of the station. Please start over.\n");
					continue;
				} else if(inputBikes < 0) {
					System.out.println("Invalid number of bikes entered. Please start over.\n");
					continue;
				} else {
					newStation.setBikes(inputBikes);
				}
			} catch(NumberFormatException e) {
				System.out.println("Invalid input. Please start over.");
			}
			
			
			System.out.println("Enter the number of pedelecs (range: 0-" + (newStation.capacity - newStation.bikes) +"): ");
			int inputPedelecs = userInput.nextInt();
			try {
				if(inputPedelecs > newStation.capacity - newStation.bikes) {
					System.out.println("The number of pedelecs specified exceeds the available docks. Please start over.\n");
					continue;
				} else if(inputPedelecs < 0) {
					System.out.println("Invalid number of pedelecs entered. Please start over.\n");
					continue;
				} else {
					newStation.setPedelecs(inputPedelecs);
				}
			} catch(NumberFormatException e) {
				System.out.println("Invalid input. Please start over.");
			}
			
			
			newStation.setAvailableDocks(newStation.capacity - (newStation.bikes + newStation.pedelecs));
			
			
			System.out.println("\nDoes the station have a kiosk? (true/false)");
			String inputKiosk = userInput.next();
			if(inputKiosk.equals("true") || inputKiosk.equals("T") || inputKiosk.equals("True") || inputKiosk.equals("t")) {
				newStation.setHasKiosk(true);
			} else if(inputKiosk.equals("false") || inputKiosk.equals("F") || inputKiosk.equals("False") || inputKiosk.equals("f")) {
				newStation.setHasKiosk(false);
			} else {
				System.out.println("\nInvalid input. Please start over.");
				continue;
			}
			
			System.out.println("Enter the address of the station: ");
			String inputAddress = userInput.next();
			newStation.setAddress(inputAddress);
			
			/*
			 * Printing all the specifications of the station designed by the user:
			 */
			System.out.println("This is the new station you will be adding to the list:\n" + "\nID: " + newStation.id + "\nName: " + 
			newStation.name + "\nCapacity: " + newStation.capacity + 
				"\nNumber of Bikes: " + newStation.bikes + "\nNumber of Pedelecs: " + newStation.pedelecs + "\nNumber of Available Docks: " + 
			newStation.availableDocks + "\nNumber of Maintenance Requests: " + newStation.mReq + "\nHas a kiosk: " + 
				newStation.kiosk + "\nAddress: " + newStation.address + "\n");
			
			stationList.add(newStation);
			
			break;
		}
		return;
			
			
		}
		
		
	
	
	public void save() {
		try
		{
			FileWriter writer = new FileWriter("data-files/station-data.csv");
			
			//ArrayList<String> repts2 = new ArrayList<String>();
			
			StringBuilder builder2 = new StringBuilder();
			String ColumnNamesList = "ID,Name,Bikes,Pedelec,AvDocs,MainReq,Cap,Kiosk,Address";
			// No need give the headers Like: id, Name on builder.append
			builder2.append(ColumnNamesList +"\n");
			writer.write(builder2.toString());
		
			for(Station stat : stationList){
				//out.write(stat.id + ","+stat.b+","+stat.p+","+stat.a+","+stat.m+","+stat.c+","+stat.kiosk+","+stat.name+","+stat.address);
				//System.out.println(stat.id);
				StringBuilder builder = new StringBuilder();
				builder.append(stat.id+",");
				builder.append(stat.name+",");
				builder.append(stat.bikes+",");
				builder.append(stat.pedelecs+",");
				builder.append(stat.availableDocks+",");
				builder.append(stat.mReq+",");
				builder.append(stat.capacity+",");
				builder.append(toInt(stat.kiosk)+",");
				builder.append(stat.address);
				
				builder.append('\n');
				//if(! repts2.contains(builder.toString())) {
					//repts2.add(builder.toString());
					writer.write(builder.toString());
					
				//}
//				writer.append(stat.id + ","+stat.b+","+stat.p+","+stat.a+","+stat.m+","+stat.c+","+stat.kiosk+","+stat.name+","+stat.address);
//				writer.append("/n");
				
					//writer.write(stat.id + "\t"+stat.b+"\t"+stat.p+"\t"+stat.a+"\t"+stat.m+"\t"+stat.c+"\t"+stat.kiosk+"\t"+stat.name+" - "+stat.address);
				
				//out.write("\n");
				

			}
			

			
			writer.close();

		}catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	
		
		/**
		 * Prints the menu for the Valley Bike Simulator.
		 */
		public static void printMenu() {
			System.out.println("Please choose from one of the following menu options:\n"
					+ "0. Quit Program.\n1. View station list.\n2. Add station list.\n3. Save station list.\n"
					+ "4. Record ride.\n5. Resolve ride data.\n6. Equalize stations.");
		}
		
		/**
		 * Helper function to pass the String values of "0" and "1" as arguments
		 * and return boolean values of true and false respectively. 
		 */
		private static boolean toBool(String s) {
			if(s.equals("0")) {
				return false;
			} else {
				return true;
			}
		}
		
		private static int toInt(boolean x) {
			if(x==true) {
				return 1;
			}else {
				return 0;
			}
		}
		
		class idCompare implements Comparator<Station>{
			@Override
		    public int compare(Station s1, Station s2)
		    {
		        return s1.id -s2.id;
		    }
		}
		
		
		
		public boolean checkID(ArrayList<Station> list, int i) {
			for(Station station: list) {
				if(i == station.id) {
					return true;
				}
			}
			return false;
		}
		
		
		

	}



	




