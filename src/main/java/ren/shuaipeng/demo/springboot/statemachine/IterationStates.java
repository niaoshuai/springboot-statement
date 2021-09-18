package ren.shuaipeng.demo.springboot.statemachine;

public enum IterationStates {
    Create(1,"CREATE","迭代创建"),
    Test(2,"TEST","提测"),
    Uat(3,"UAT","UAT"),
    Prod(4,"PROD","生产"),
    File(5,"FILE","归档");

    private Integer code;
    private String msg;
    private String description;

    IterationStates(Integer code, String msg,String description) {
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
