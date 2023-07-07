package org.example;

public class File extends ElementStockage
{
    private String content;
    public File(String n, String c)
    {
        super(n);
        this.content = c;
    }

    public void accept(Visitor pp) {
        pp.visitFile(this);
    }
}
