import java.util.Arrays;


public class Polinomio {
    //Atributos
    private int[] coefficients;
    private int grade;
    
    //Métodos
    //Constructor por omisión
    public Polinomio() {
        coefficients = new int[]{0};
        grade = 0;
    }
    
    //Constructor al que se le pasa v de int
    public Polinomio(int[] arg) {
        coefficients = Arrays.copyOf(arg, arg.length);
        this.calcGrade(coefficients);
    }
    
    //Método que devuelve el grado
    public int grado() {
        return grade;
    }
    
    //Método que devuelve el coeficiente en la posición dada
    public int coeficiente(int index) {
        return (index >= 0 && index <= grade) ? coefficients[index] : 0;
    }

    //Método que cambia el valor del coeficiente en la posición dada    
    public void coeficiente(int index, int value) {
        
        if (index > grade) {
            coefficients = Arrays.copyOf(coefficients, index + 1);
            coefficients[index] = value; 
        }
        
        coefficients[index] = value;
        
        this.calcGrade(coefficients);
    }
    
    //Método que devuelve los coeficientes del polinomio
    public int[] coeficientes() {
        return Arrays.copyOf(coefficients, this.grado() + 1);
    }
    
    @Override
    public String toString() {
        String rs = new String();
        
        for (int i = grade; i >= 0; i--) {
            int item = coefficients[i];
        
            if (item != 0) {
                
                if (item > 0 && i != grade) {
                    rs += "+";
                }
                
                if (i > 0) {
                    
                    rs += (
                        (Math.abs(item) != 1) ? item + "x" :
                        (item < 0) ? "-x" : "x"
                    );
                    
                    if (i > 1) {
                        rs += ("^" + i);
                    }
                    
                } else {
                    rs += item;
                }
            }
        }
        
        if (rs.length() == 0) {
            return "0";
        } else {
            return rs;
        }
    }
    
    //Método que devuelve el valor del polinomio donde x = v
    public float valor(float value) {
        float rs = 0F;
        
        for (int i = 0; i <= grade; i++) {
            rs += coefficients[i] * Math.pow(value, i);
        }
        
        return rs;
    }
    
    //Método que suma dos polinomios
    public Polinomio suma(Polinomio pol) {
        int[] polCoeff = pol.coeficientes();
        
        int maxLen = Math.max(polCoeff.length, coefficients.length);
        int minLen = Math.min(polCoeff.length, coefficients.length);
        
        int[] rs = Arrays.copyOf(
            (coefficients.length == maxLen) ? coefficients : polCoeff,
            maxLen
        );
        
        for (int i = 0; i < minLen; i++) {
            rs[i] = coefficients[i] + polCoeff[i];
        }
        
        return new Polinomio(rs);
    }
    
    //Método que resta dos polinomios
    public Polinomio resta(Polinomio pol) {
        int[] polCoeff = pol.coeficientes();
        
        int maxLen = Math.max(polCoeff.length, coefficients.length);
        int[] rs = new int[maxLen];
        
        int[] actualPol = Arrays.copyOf(coefficients, maxLen);
        int[] otherPol = Arrays.copyOf(polCoeff, maxLen);
        
        for (int i = 0; i < maxLen; i++) {
            rs[i] = actualPol[i] - otherPol[i];
        }
        
        System.out.println(Arrays.toString(rs));
        return new Polinomio(rs);
    }
    
    private void viewPolynomial() {
        for (int i = 0; i < grade; i++) {
            System.out.print(coefficients[i] + " ");
        }
    }
    
    //Método que calcula el valor del polinomio
    private void calcGrade(int[] pol) {
        grade = pol.length - 1;

        for (int i = pol.length - 1; i >= 0; i--) {
            if (pol[i] != 0) {
                break;
            }
            grade--;
        }
        
        if (grade < 0) {
            grade = 0;
        }
    }
}
