package bean;

public class ShopCart {

	// ��Ӧ�ֶ� gname
	String gname;
	// ��Ӧ�ֶ� gprice
	float gprice;
	// ��Ӧ�ֶ� cnum
	int cnum;
	// ��Ӧ�ֶ� cprice
	float cprice;
	// ��Ӧ�ֶ� uname
	String uname;

	public ShopCart( String uname, String gname, float gprice,int cnum, float cprice) {
		super();
		this.gname = gname;
		this.gprice = gprice;
		this.cnum = cnum;
		this.cprice = cprice;
		this.uname = uname;
	}

	public ShopCart() {
		super();
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the gname
	 */
	public String getGname() {
		return gname;
	}

	/**
	 * @return the gprice
	 */
	public float getGprice() {
		return gprice;
	}

	/**
	 * @return the cnum
	 */
	public int getCnum() {
		return cnum;
	}

	/**
	 * @return the cprice
	 */
	public float getCprice() {
		return cprice;
	}

	/**
	 * @param gname the gname to set
	 */
	public void setGname(String gname) {
		this.gname = gname;
	}

	/**
	 * @param gprice the gprice to set
	 */
	public void setGprice(float gprice) {
		this.gprice = gprice;
	}

	/**
	 * @param cnum the cnum to set
	 */
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	/**
	 * @param cprice the cprice to set
	 */
	public void setCprice(float cprice) {
		this.cprice = cprice;
	}

}