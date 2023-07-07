package org.example;

public abstract class Visitor
{
    public abstract void visitBeforeDirectory(Directory d);
    public abstract void visitAfterDirectory();
    public abstract void visitFile(File f);
    public abstract void visitLink(Link l);
}
