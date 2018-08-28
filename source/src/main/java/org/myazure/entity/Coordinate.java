package org.myazure.entity;

import java.io.Serializable;

public class Coordinate implements Serializable{
	private int id;
	private String coordinatename;
	private double a;
	private double f;
	private double L_mid;
	private double B0;
	private double dN;
	private double dE;
	private double dX;
	private double dY;
	private double dZ;
	private double RX;
	private double RY;
	private double RZ;
	private double K;
	private int is_del;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the coordinatename
	 */
	public String getCoordinatename() {
		return coordinatename;
	}
	/**
	 * @param coordinatename the coordinatename to set
	 */
	public void setCoordinatename(String coordinatename) {
		this.coordinatename = coordinatename;
	}
	/**
	 * @return the a
	 */
	public double getA() {
		return a;
	}
	/**
	 * @param a the a to set
	 */
	public void setA(double a) {
		this.a = a;
	}
	/**
	 * @return the f
	 */
	public double getF() {
		return f;
	}
	/**
	 * @param f the f to set
	 */
	public void setF(double f) {
		this.f = f;
	}
	/**
	 * @return the l_mid
	 */
	public double getL_mid() {
		return L_mid;
	}
	/**
	 * @param l_mid the l_mid to set
	 */
	public void setL_mid(double l_mid) {
		L_mid = l_mid;
	}
	/**
	 * @return the b0
	 */
	public double getB0() {
		return B0;
	}
	/**
	 * @param b0 the b0 to set
	 */
	public void setB0(double b0) {
		B0 = b0;
	}
	/**
	 * @return the dN
	 */
	public double getdN() {
		return dN;
	}
	/**
	 * @param dN the dN to set
	 */
	public void setdN(double dN) {
		this.dN = dN;
	}
	/**
	 * @return the dE
	 */
	public double getdE() {
		return dE;
	}
	/**
	 * @param dE the dE to set
	 */
	public void setdE(double dE) {
		this.dE = dE;
	}
	/**
	 * @return the dX
	 */
	public double getdX() {
		return dX;
	}
	/**
	 * @param dX the dX to set
	 */
	public void setdX(double dX) {
		this.dX = dX;
	}
	/**
	 * @return the dY
	 */
	public double getdY() {
		return dY;
	}
	/**
	 * @param dY the dY to set
	 */
	public void setdY(double dY) {
		this.dY = dY;
	}
	/**
	 * @return the dZ
	 */
	public double getdZ() {
		return dZ;
	}
	/**
	 * @param dZ the dZ to set
	 */
	public void setdZ(double dZ) {
		this.dZ = dZ;
	}
	/**
	 * @return the rX
	 */
	public double getRX() {
		return RX;
	}
	/**
	 * @param rX the rX to set
	 */
	public void setRX(double rX) {
		RX = rX;
	}
	/**
	 * @return the rY
	 */
	public double getRY() {
		return RY;
	}
	/**
	 * @param rY the rY to set
	 */
	public void setRY(double rY) {
		RY = rY;
	}
	/**
	 * @return the rZ
	 */
	public double getRZ() {
		return RZ;
	}
	/**
	 * @param rZ the rZ to set
	 */
	public void setRZ(double rZ) {
		RZ = rZ;
	}
	/**
	 * @return the k
	 */
	public double getK() {
		return K;
	}
	/**
	 * @param k the k to set
	 */
	public void setK(double k) {
		K = k;
	}
	/**
	 * @return the is_del
	 */
	public int getIs_del() {
		return is_del;
	}
	/**
	 * @param is_del the is_del to set
	 */
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinate [id=" + id + ", coordinatename=" + coordinatename + ", a=" + a + ", f=" + f + ", L_mid="
				+ L_mid + ", B0=" + B0 + ", dN=" + dN + ", dE=" + dE + ", dX=" + dX + ", dY=" + dY + ", dZ=" + dZ
				+ ", RX=" + RX + ", RY=" + RY + ", RZ=" + RZ + ", K=" + K + ", is_del=" + is_del + "]";
	}
	
	
	
	

}
