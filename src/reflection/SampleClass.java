package reflection;

public class SampleClass {
    private String privateVariable = "privateValue";
    public String publicVariable = "publicValue";

    public SampleClass(String privateVariable, String publicVariable) {
        System.out.println("I am THE Public Constructor");
        this.privateVariable = privateVariable;
        this.publicVariable = publicVariable;
    }

    private SampleClass(){
        System.out.println("I am THE Private Constructor");
    }

    public String getPrivateVariable() {
        return privateVariable;
    }

    public void setPrivateVariable(String privateVariable) {
        this.privateVariable = privateVariable;
    }

    @Override
    public String toString() {
        return "SampleClass{" +
                "privateVariable='" + privateVariable + '\'' +
                ", publicVariable='" + publicVariable + '\'' +
                '}';
    }
}
