package vista;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EjemploBindingProperties {

        public static void main(String[] args) {

           IntegerProperty sumando1= (IntegerProperty) new SimpleIntegerProperty(0);
           IntegerProperty sumando2= (IntegerProperty) new SimpleIntegerProperty(0);
           //Forma1
           NumberBinding sum = sumando1.add(sumando2);
           //Ejemplo binding enteros
            IntegerProperty numero3= (IntegerProperty) new SimpleIntegerProperty(0);
            numero3.bindBidirectional(sumando1);

            sumando1.addListener(new ChangeListener(){
                @Override public void changed(ObservableValue o,Object oldVal,
                                              Object newVal){
                    System.out.println("Sumando 1 ha cambiado. el valor de entero 3 es: " + numero3.getValue());
                    System.out.println("Sumando 1 ha cambiado. La nueva suma es: " + sum.getValue());
                }
            });

            sumando2.addListener(new ChangeListener(){
                @Override public void changed(ObservableValue o,Object oldVal, Object newVal){
                    System.out.println("Sumando 2 ha cambiado. La nueva suma es: "+sum.getValue());
                }
            });

            sumando1.setValue(3);
            sumando2.setValue(5);
        }

}
