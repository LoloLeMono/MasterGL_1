package org.example;

public abstract class ElementStockage
{
    private String name;

    public ElementStockage(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public abstract void accept(Visitor v);
}
