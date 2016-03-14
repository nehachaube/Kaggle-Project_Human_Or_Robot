/**
 * 
 * @author iamneha
 *
 */
//Discrete and Continuous Attributes
public class Attribute {
    
String name;
double continuousValue;
boolean discreteValue;
String value;
double thresholdValue;
public static int NUM_ATTRIBUTES=9;

public Attribute(String name,double continuousValue)
{
    this.name=name;
    this.continuousValue = continuousValue;
}

public Attribute(String name,boolean discreteValue)
{
    this.name=name;
    this.discreteValue = discreteValue;
}
public Attribute(String name,String value)
{
    this.name=name;
    this.value = value;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public double getContinuousValue() {
    return continuousValue;
}

public void setContinuousValue(double continuousValue) {
    this.continuousValue = continuousValue;
}

public boolean isDiscreteValue() {
    return discreteValue;
}

public void setDiscreteValue(boolean discreteValue) {
    this.discreteValue = discreteValue;
}

public String getValue() {
    return value;
}

public void setValue(String value) {
    this.value = value;
}

public double getThresholdValue() {
    return thresholdValue;
}

public void setThresholdValue(double thresholdValue) {
    this.thresholdValue = thresholdValue;
}

}
