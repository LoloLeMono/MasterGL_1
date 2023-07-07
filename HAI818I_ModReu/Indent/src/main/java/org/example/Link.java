package org.example;

public class Link extends ElementStockage {
    private ElementStockage ref;
    public Link(String n, Directory d)
    {
        super(n);
        this.ref=d;
    }

    public void accept(Visitor pp) {
        pp.visitLink(this);
    }
}
