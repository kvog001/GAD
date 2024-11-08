package DoubleHashie;
/**
 * Dieses Interface beschreibt eine Fabrik, die aus
 * einer Größe ein Hashable<K>-Objekt erzeugt.
 */
public interface HashableFactory<K> {
  DoubleHashable<K> create(int size);
}
