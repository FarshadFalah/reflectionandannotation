package annotation.generalperpos;

public class AnnotationMain {
    public static void main(String[] args) {
        overrideFunction();
        suppressedFunction();
    }

    public static void overrideFunction() {
        parent sampleClass = new SampleClass();
        sampleClass.function1();
        System.out.println(sampleClass.function2());
        System.out.println(sampleClass.function3("sampleClass"));
    }

    public static void suppressedFunction() {
        @SuppressWarnings("unused")
        int a =10;

        @SuppressWarnings({"removal","unused","boxing"})
        Integer i = new Integer(2);


    }



}
