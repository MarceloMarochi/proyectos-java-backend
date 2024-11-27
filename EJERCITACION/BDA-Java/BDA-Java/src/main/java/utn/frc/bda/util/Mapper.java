package utn.frc.bda.util;

@FunctionalInterface
public interface Mapper<T, R> {
    R map(T t);
}
