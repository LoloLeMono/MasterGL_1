package org.example;

public class PrettyPrintVisitor extends Visitor
{
    private int indent = 0;
    public void printIndent()
    {
        for (int i=0; i<indent; i++){
            System.out.print("  ");
        }
    }

    public void visitFile(File f)
    {
        printIndent();
        System.out.println("File "+f.getName());
    }
    public void visitBeforeDirectory(Directory d)
    {
        printIndent();
        System.out.println("Directory "+d.getName());
        indent++;
    }

    public void visitAfterDirectory()
    {
        indent--;
    }
    public void visitLink(Link l)
    {
        printIndent();
        System.out.println("Link "+l.getName());
    }
}
