import java.lang.annotation.*;
import java.lang.reflect.Method;


@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotayion1 {
    String str() default "My Annotation Class";
    int n() default 10;
}



@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotayion2 {
    String str();
    int val();

}

class NoAnno {

}

@MyAnnotayion1
class Meta {

    @MyAnnotayion2(str = "My Annotation meth()", val = 100)
    public static void myMeth() {

        Meta оЬ = new Meta();

        try {
            Class<?> с = оЬ.getClass();
            Method m = с.getMethod("myMeth");
            MyAnnotayion2 anno = m.getAnnotation(MyAnnotayion2.class);

            System.out.println(anno.str() + " " + anno.val());
        } catch (NoSuchMethodException ехс) {
            System.out.println("Meтoд не найден.");
        }
    }

    public static void main(String[] args) {
        myMeth();
        inspectorAnno(Meta.class);
        inspectorAnno(NoAnno.class);

    }

    private static void inspectorAnno(Class<?> a) {
        if (a.isAnnotationPresent(MyAnnotayion1.class))
            System.out.println(a.getAnnotation(MyAnnotayion1.class));
        else
            System.out.println("No Annotation1");
    }

}
