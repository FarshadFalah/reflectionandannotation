package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionMain {

    public static void main(String[] args) throws Exception{
        serializationDeserialization();
    }

    public static void callPrivateConstructor() {
        try {
            Class<?> cls = Class.forName("reflection.SampleClass");
            Constructor<?> constructor = cls.getDeclaredConstructor();
            constructor.setAccessible(true);
            SampleClass instance = (SampleClass) constructor.newInstance();
            System.out.println(instance);

        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void callPublicConstructor() {
        try {
            Class<?> cls = Class.forName("reflection.SampleClass");
            Constructor<?> constructor = cls.getConstructor(String.class,String.class);
            SampleClass instance = (SampleClass) constructor.newInstance("Hello", "World");
            System.out.println(instance);

        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void playWithFields() {
        try {
            Class<?> cls = SampleClass.class;
            //prints only Public Fields
            for (Field field : cls.getFields()) {
                System.out.println("Field: " + field.getType() + " " + field.getName());
            }

            System.out.println("*************************************************");

            //Prints All Fields
            for (Field field : cls.getDeclaredFields()) {
                System.out.println("Field: " + field.getType() + " " + field.getName());
            }

            System.out.println("*************************************************");

            //Building Object with private Constructor
            Constructor<?> constructor = cls.getDeclaredConstructor();
            constructor.setAccessible(true);

            SampleClass sampleClass = (SampleClass) constructor.newInstance();
            //changing Value of public Variable
            Field publicField = sampleClass.getClass().getField("publicVariable");
            System.out.println("publicField: " + publicField.getType() + " " + publicField.getName() + " = " + publicField.getModifiers()+sampleClass);
            publicField.set(sampleClass,"changedPublicValue");
            System.out.println("publicField: " + publicField.getType() + " " + publicField.getName() + " = " + publicField.getModifiers()+sampleClass);

            System.out.println("*************************************************");

            //changing Value of Private Variable
            Field privateField = sampleClass.getClass().getDeclaredField("privateVariable");
            System.out.println("privateField: " + privateField.getType() + " " + privateField.getName() + " = " + privateField.getModifiers()+sampleClass);
            privateField.setAccessible(true);
            privateField.set(sampleClass,"changedPrivateValue");
            System.out.println("privateField: " + privateField.getType() + " " + privateField.getName() + " = " + privateField.getModifiers()+sampleClass);

        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void playWithMethods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> cls = SampleClass.class;
        for (Method method : cls.getMethods()) {
            System.out.println(method.getName() + " " + method.getReturnType() + " " + method.getParameterCount() + " " + Arrays.toString(method.getParameters()) + " " + Arrays.toString(method.getParameterTypes()));
        }
        System.out.println("***********************************************");

        Constructor<?> constructor = cls.getDeclaredConstructor();
        constructor.setAccessible(true);
        SampleClass sampleClass = (SampleClass) constructor.newInstance();
        Method getPrivateVariable = cls.getMethod("getPrivateVariable");
        System.out.println(getPrivateVariable.invoke(sampleClass));
        System.out.println("***********************************************");

        Method setPrivateVariable = cls.getMethod("setPrivateVariable", String.class);
        setPrivateVariable.invoke(sampleClass,"changedPrivateValue");
        System.out.println(getPrivateVariable.invoke(sampleClass));

    }


    public static void serializationDeserialization() throws Exception {
        Person person = new Person("John Doe", 30);
        String jsonString = JsonUtil.serialize(person);
        System.out.println("\nSerialized JSON: " + jsonString);

        // Deserialization: Convert JSON to Object
        Person deserializedPerson = JsonUtil.deserialize(jsonString, Person.class);
        System.out.println("\nDeserialized Person: Name = " + deserializedPerson.getName() + ", Age = " + deserializedPerson.getAge());
    }


}
