package bean;

public class Goods {
	// ��Ӧ�ֶ� gname
	String gname;
	// ��Ӧ�ֶ� gprice
	int gprice;
	// ��Ӧ�ֶ� gunmber
	int gnumber;

	/**
	 * @return the gname
	 */
	public String getGname() {
		return gname;
	}

	/**
	 * @param gname the gname to set
	 */
	public void setGname(String gname) {
		this.gname = gname;
	}

	/**
	 * @return the gprice
	 */
	public int getGprice() {
		return gprice;
	}

	/**
	 * @param gprice the gprice to set
	 */
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}

	/**
	 * @return the gnumber
	 */
	public int getGnumber() {
		return gnumber;
	}

	/**
	 * @param gnumber the gnumber to set
	 */
	public void setGnumber(int gnumber) {
		this.gnumber = gnumber;
	}

	public Goods(String gname, int gprice, int gnumber) {
		super();
		this.gname = gname;
		this.gprice = gprice;
		this.gnumber = gnumber;
	}

	public Goods() {
		super();
	}

}
