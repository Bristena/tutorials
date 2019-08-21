package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {
        //map => object to object
        //flat map => object to multiple object/none

        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        foos.forEach(f ->
                IntStream.range(1, 4)
                        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

        System.out.println("**********************************************");

        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

        System.out.println("**********************************************");

        Outer outer = new Outer();
        if (outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }

        System.out.println("**********************************************");

        Optional.of(new Outer()).flatMap(o->Optional.ofNullable(o.nested))
                .flatMap(o->Optional.ofNullable(o.inner))
                .flatMap(o->Optional.ofNullable(o.foo))
                .ifPresent(System.out::println);
    }
}
