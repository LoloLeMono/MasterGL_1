package org.example;

import java.util.ArrayList;

public class Directory extends ElementStockage
{
    protected ArrayList<ElementStockage> elements;

    public Directory(String n){
        super(n);
        elements = new ArrayList<>();
    }

    public void add(ElementStockage e)
    {
        elements.add(e);
    }

    public void accept(Visitor pp) {
        pp.visitBeforeDirectory(this);

        for(ElementStockage e : this.elements)
        {
            e.accept(pp);
        }

        pp.visitAfterDirectory();
    }
}
