/**
 * Create on 2011-10-18
 */
package com.ligelong.util;

/**
 * <code>AclEnties</code>
 *
 * @author David Gong(gongjian@xuehuile.cn)
 */
public enum AclEnties {
    EnterManage(            0, "管理后台"),
    ManageUser(        		1, "用户管理"),
    ManagePost(             2, "帖子管理"),
    ViewStatistics(         3, "查看统计");    
    private Integer id;  //
    private String desc; //
    private AclEnties(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    /**
     * get id
     * @return id
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * get description
     * @return description
     */
    public String getDesc() {
        return this.desc;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.getClass().getName() + "[id=" + this.id + ", description=" + this.desc + "]";
    }
}