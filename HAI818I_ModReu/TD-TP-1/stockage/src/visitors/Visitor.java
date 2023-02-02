package visitors;

import stockage.*;

public class Visitor
{
	public void visitFile(File file)
	{
		// Je suis dans un file
	}
	
	public void visitLink(Link file)
	{
		// Je suis dans un link
	}

	public void visitDirectory(Directory dir)
	{
		// Je suis dans un directory
	}
}
