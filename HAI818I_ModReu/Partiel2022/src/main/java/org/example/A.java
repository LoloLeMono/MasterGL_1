package org.example;

public class A
{
    IB b;
    public void setB(IB b)
    {
        this.b = b;
    }

    public int ma()
    {
        return (1 + b.mb());
    }
}
