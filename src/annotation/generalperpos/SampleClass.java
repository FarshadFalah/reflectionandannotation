package annotation.generalperpos;

class parent{
    public void function1(){
        System.out.println("this is function1");
    }
    public String function2(){
        return "this is function2";
    }
    public String function3(String s){
        return s;
    }
}


public class SampleClass extends parent {

    @Override
    public void function1() {
        System.out.println("this is function1 Override");
    }

    @Override
    public String function2() {
        return "this is function2 Override";
    }

    @Override
    public String function3(String s) {
        return s + " Override";
    }
}
