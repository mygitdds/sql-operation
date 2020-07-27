package com.dds.dome.sql.operation.common;

/**
 * Epiphany
 *
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
public enum Epiphany {
    ONE(1,"a"),
    TWO(2,"b"),
    THREE(3,"c"),
    FOUR(4,"d"),
    FIVE(5,"e");
    private int tableNum;
    private String as;

    Epiphany(int tableNum, String as) {
        this.tableNum = tableNum;
        this.as = as;
    }

    //通过tableNum拿到对应的as
    public static String getAs(int tableNum){
        for(Epiphany epiphany : Epiphany.values()){
            if(epiphany.getTableNum() == tableNum){
                return epiphany.getAs();
            }
        }
        return null;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }
}
