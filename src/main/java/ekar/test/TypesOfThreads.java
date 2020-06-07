package ekar.test;

public enum TypesOfThreads {

    PRODUCER_THREAD ("PRODUCER"),
    CONSUMER_THREAD("CONSUMER");


    private String code;

    TypesOfThreads(String code){
        this.code = code ;
    }

    public String getCode(){
        return code;
    }
}
