package org.example;

public class Main
{
    public static void main(String[] args)
    {
        A a = new A();
        a.setB(new B2());
        System.out.println("a.ma() => " + a.ma());
    }
}