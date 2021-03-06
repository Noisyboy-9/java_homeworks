public class ComplexNumber {
    private double real;
    private double imaginary;

    /**
     * setup a complex number
     * 
     * @param real
     * @param imaginary
     */
    public ComplexNumber(double real, double imaginary) {
        setReal(real);
        setImaginary(imaginary);
    }

    /**
     * get real part of the complex number
     * 
     * @return int
     */
    public double getReal() {
        return real;
    }

    /**
     * get the imaginary part of the complex number
     * 
     * @return double
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * set imaginary part of the complex number
     * 
     * @param imaginary
     */
    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    /**
     * set real part of the complex number
     * 
     * @param real
     */
    public void setReal(double real) {
        this.real = real;
    }

}
