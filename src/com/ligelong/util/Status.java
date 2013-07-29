/**
 * Create on 2011-10-28
 */
package com.ligelong.util;

/**
 * 
 * <code>Status</code>
 *
 * @author David Gong
 */
public enum Status {
	/**
	 *
	 */
	ON(0),
	/**
	 *
	 */
	OFF(1);

    //
    private Integer statusValue;
    /*
     *
     * @param statusValue
     */
    Status(Integer statusValue) {
        this.statusValue = statusValue;
    }
    /**
     *
     * @return
     */
    public Integer getValue() {
        return this.statusValue;
    }
}