package org.example;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");

        Directory d = new Directory("UnProgramme");
        Directory d2 = new Directory("arc");
        Directory d3 = new Directory("bin");
        d.add(d2);
        d.add(d3);
        d2.add(new File("F1.java", "..."));
        d2.add(new File("F2.java", "..."));
        d3.add(new File("F1.class", "..."));
        d3.add(new Link("source", d2));

        Visitor pp = new PrettyPrintVisitor();
        d.accept(pp);
    }
}