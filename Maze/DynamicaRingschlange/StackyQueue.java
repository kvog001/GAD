package DynamicaRingschlange;

import java.util.Arrays;

/**
 * Die Klasse StackyQueue soll eine Warteschlange auf
 * Basis der Klasse {@link DynamicStack} implementieren. Es
 * soll ausschließlich die Klasse {@link DynamicStack} zur
 * Datenspeicherung verwendet werden.
 */
public class StackyQueue {
  /*
   * Todo
   */
  private DynamicStack enqueueStack, dequeueStack;

  private int lengthOfEStack,lengthOfDStack;
  /**
   * Diese Methode ermittelt die Länge der Warteschlange.
   * @return die Länge der Warteschlange
   */
  public int getLength() {
    /*
     * Todo
     */
    return lengthOfEStack;
  }
  
  /**
   * Dieser Kontruktor initialisiert eine neue Schlange.
   * 
   * @param growthFactor
   * @param maxOverhead
   */
  public StackyQueue (int growthFactor, int maxOverhead) {
    /*
     * Todo
     */
    enqueueStack = new DynamicStack(growthFactor,maxOverhead);
    dequeueStack = new DynamicStack(growthFactor,maxOverhead);
    lengthOfEStack = 0;
    lengthOfDStack = 0;
  }
  
  /**
   * Diese Methode reiht ein Element in die Schlange ein.
   * 
   * @param value der einzufügende Wert
   */
  public void enqueue (int value) {
    /*
     * Todo
     */
    enqueueStack.pushBack(value);
    lengthOfEStack++;

  }
  
  /**
   * Diese Methode entfernt ein Element aus der Warteschlange.
   * 
   * @return das entfernte Element
   */
  public int dequeue () {
    /*
     * Todo
     */
    int result = 0;
    if (dequeueStackEmpty() && !enqueueStackEmpty()){
      passValuesToDequeueStack();
      result = this.dequeueStack.popBack();
      lengthOfDStack--;

    } else if(!dequeueStackEmpty()){

      result = this.dequeueStack.popBack();
      lengthOfDStack--;

    } else{
      System.out.println("Both stacks empty!");
    }

  return result;
  }

  private void passValuesToDequeueStack(){
    for (int i= lengthOfEStack-1; i >= 0; i--){
      int value = this.enqueueStack.getElementAtIndex(i);
      dequeueStack.pushBack(value);
      enqueueStack.popBack();
      lengthOfDStack++;
      lengthOfEStack--;
    }
  }

  private boolean dequeueStackEmpty(){
    return lengthOfDStack == 0;
  }

  private boolean enqueueStackEmpty(){
    return lengthOfEStack == 0;
  }

  @Override
  public String toString(){
    String output = "[";

    for (int i=0; i < enqueueStack.getSize(); i++){
      if (i != enqueueStack.getSize()-1)
        output += enqueueStack.getElementAtIndex(i)+", ";
      else
        output += enqueueStack.getElementAtIndex(i);
    }
    output += "], length: "+lengthOfEStack+", [";

    for (int i=0; i < dequeueStack.getSize(); i++){
      if (i != dequeueStack.getSize()-1)
        output += dequeueStack.getElementAtIndex(i)+", ";
      else
        output += dequeueStack.getElementAtIndex(i);
    }
    output += "], length: "+lengthOfDStack;

    return output;
  }
}
