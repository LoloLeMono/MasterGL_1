package visitors;

import stockage.File;

public class RAZVisitor extends Visitor
{
	public void visitFile(File file)
	{
		file.setContents("");
	}
}
