package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

public class Junction {
	private String junctionName;
	private Point location;// location of the junction on the map
	private int delay;// delay time in seconds
	private boolean hasLight=false;// checks if the junction has traffic lights.
	private ArrayList<Road> enteringRoads = new ArrayList<>(); // holds the list of the roads that enter to the junction.
	private ArrayList<Road> exitingRoads = new ArrayList<>(); // holds the list of the roads that exitthe junction.
	private ArrayList<Road> vehicles = new ArrayList<>(); //list of entering roads with cars waiting on the junction
	public int nextEnterRoad=0;
	
	public Junction (String name, Point loc)
	{
		junctionName=name;
		location=loc;
		
		Random rand = new Random();
		delay = 1 + rand.nextInt(10);
		System.out.println(toString() + " has been created.");
	}

	public double getX() {
		return location.getX();
	}

	public double getY() {
		return location.getY();
	}
	
	//set&get to JunctionName()
	public String getJunctionName() {
		return junctionName;
	}
	
	public void setJunctionName(String junctionName) {
		this.junctionName = junctionName;
	}

	
	//set&get to junctionName()
	public Point getLocation()
	{
		return location;
	}

	public void setLocation(Point p )
	{
		location=p;
	}
	
	//set&get to delay
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public boolean getHasLight() {
		return hasLight;
	}

	public void setHasLight(boolean hasLight) {
		this.hasLight = hasLight;
	}

	public void setLightsOn(){ setHasLight(true); }

	public int getNextEnterRoad() {
		return nextEnterRoad;
	}

	public void getExitingRoads(ArrayList<Road> exitingRoads) {
		this.exitingRoads = exitingRoads;
	}
	public ArrayList<Road> getExitingRoads() {
		return exitingRoads;
	}

	public ArrayList<Road> getEnteringRoads() {
		return enteringRoads;
	}
	public void setenteringRoads(ArrayList<Road> enteringRoads) {
		this.enteringRoads = enteringRoads;
	}
	public ArrayList<Road> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Road> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void changeLights()//make the next entering road in the list green (open) and all the others (exiting only) red (closed).
	{
		enteringRoads.get(nextEnterRoad).setLight(true);
		for(int i=0;i<exitingRoads.size();i++)
				exitingRoads.get(i).setLight(false);
				
		if(nextEnterRoad + 1 < enteringRoads.size())
			nextEnterRoad++;
		else
			nextEnterRoad = 0;
	}

	public boolean checkAvailability(Road r){ //make the next entering road in the list green (open)and all the others (exiting only) red (closed).
		if(hasLight)
			if(r.getLight() == true)
				return false;
			else
				return true;
		else if(vehicles.indexOf(r) == 0)
			return false;
		else return true;
	}
	
	public String toString()
	{
		return "Junction " + junctionName;
	}

	public boolean equals(Junction J){
		boolean roadsIsEqual = true;
		if(enteringRoads.size() == J.enteringRoads.size() && exitingRoads.size() == J.exitingRoads.size() && vehicles.size() == J.vehicles.size())
		{
			for(int i=0;i<enteringRoads.size();i++)
				if(!enteringRoads.get(i).equals(J.enteringRoads.get(i))){
					roadsIsEqual = false;
					break;
				}
			if(roadsIsEqual){
				for(int i=0;i<exitingRoads.size();i++)
					if(!exitingRoads.get(i).equals(J.exitingRoads.get(i))){
						roadsIsEqual = false;
						break;
					}
				if(roadsIsEqual)
					for(int i=0;i<vehicles.size();i++)
						if(!vehicles.get(i).equals(J.vehicles.get(i))){
							roadsIsEqual = false;
							break;
						}
			}
		}else return false;

		if(junctionName == J.junctionName && location.equals(J.location) && delay == J.delay && hasLight == J.hasLight && roadsIsEqual)
			return true;
		
		return false;
	};

	public void addEnteringRoads(Road R){ enteringRoads.add(new Road(R)); }
	public void addExitingRoads(Road R){ exitingRoads.add(new Road(R)); }
}
