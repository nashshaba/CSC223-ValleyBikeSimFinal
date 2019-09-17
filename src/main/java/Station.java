import java.util.ArrayList;

public class Station {
	public int id;
	public String name;
	public int bikes;
	public int pedelecs;
	public int availableDocks;
	public int mReq;
	public int capacity;
	public boolean kiosk;
	public String address;
	
	public Station(int id, String name, int bikes, int pedelecs, int avDocks, int mReq, int capacity,
			boolean kiosk, String address) {
		this.id = id; 
		this.name = name;
		this.bikes = bikes; 
		this.pedelecs = pedelecs;
		this.availableDocks = avDocks;
		this.mReq = mReq;
		this.capacity = capacity;
		this.kiosk = kiosk;
		this.address = address;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBikes(int bikes) {
		this.bikes = bikes;
	}
	
	public void setPedelecs(int pedelecs) {
		this.pedelecs = pedelecs;
	}
	
	public void setAvailableDocks(int availableDocks) {
		this.availableDocks = availableDocks;
	}
	
	public void setMaintenanceRequests(int mReq) {
		this.mReq = mReq;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void setHasKiosk(boolean kiosk) {
		this.kiosk = kiosk;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean checkBikes(ArrayList<Station> list, int b) {
		for(Station station: list) {
			if(station.bikes == b) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkPeds(ArrayList<Station> list, int p) {
		for(Station station: list) {
			if(station.pedelecs == p) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkCap(ArrayList<Station> list, int c) {
		for(Station station: list) {
			if(station.capacity == c) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkAvDocs(ArrayList<Station> list, int a) {
		for(Station station: list) {
			if(station.availableDocks == a) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkmReq(ArrayList<Station> list, int m) {
		for(Station station: list) {
			if(station.mReq == m) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkName(ArrayList<Station> list, String n) {
		for(Station station: list) {
			if(station.name.equals(n)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkAddress(ArrayList<Station> list, int a) {
		for(Station station: list) {
			if(station.address.equals(a)) {
				return false;
			}
		}
		return true;
	}
	
	


	
	
	
	
 
}
