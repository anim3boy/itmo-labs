package me.emokid.json;

public interface JSONContainableObject<E, K> {
    E getData();
    JSONMap getJSONMap(K key);
    String getString(K key);
    JSONNumber getNumber(K key);
    JSONList getJSONList(K key);
    Boolean getBoolean(K key);
}
