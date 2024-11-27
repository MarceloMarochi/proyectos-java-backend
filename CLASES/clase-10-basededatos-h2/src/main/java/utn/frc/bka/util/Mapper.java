package utn.frc.bka.util;

@FunctionalInterface
public interface Mapper<T, R> {
    R map(T t);
}
