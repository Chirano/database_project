package pt.org.upskill.dto;

public class KeyValueDTO<T> {
    public T key;
    public String value;

    public KeyValueDTO(T key, String value) {
        this.key = key;
        this.value = value;
    }
}
