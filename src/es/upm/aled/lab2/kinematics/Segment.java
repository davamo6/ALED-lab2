package es.upm.aled.lab2.kinematics;

import java.util.List;

public class Segment {
	
	private double length;
	private double angle;
	private List<Segment> children;
	
	/**
	 * Builds a new Node from its length and angle.
	 * 
	 * @param length The length.
	 * @param angle The angle.
	 */
	public Segment(double length, double angle) {
		this.length = length;
		this.angle = angle;
	}
	
	/**
	 * Returns the length.
	 * 
	 * @return The length.
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Returns the angle.
	 * 
	 * @return The angle.
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * Sets the angle.
	 * 
	 * @param The angle to be set.
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Returns the Segments this one is connected to. The children Segments don't have a
	 * reference to the parent Segment, so the connection is one-way.
	 * 
	 * @return A List of all the children Segments.
	 */
	public List<Segment> getChildren(){
		return children;
	}
	
	/**
	 * Adds a new Segment to the List of Segments this one is connected to. Each Segment can
	 * only appear as a child once.
	 * 
	 * @param measurement The Segment to be added.
	 */
	public void addChild(Segment child) {
		if(!children.contains(child)) {
			children.add(child);
		}
	}

}
