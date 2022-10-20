# $Id: 007ee4442e1f422952c2b6d2dfa9e15f1ad1a057 $

ANTLR=java -jar /home/e20160005796/Documents/M1-GL/HAI705I_Compilation/antlr-4.11.1-complete.jar
JAVAC= javac -classpath '/home/e20160005796/Documents/M1-GL/HAI705I_Compilation/antlr-4.11.1-complete.jar'
G4=Hello.g4 ExprArith.g4 ExprArithEval.g4 ExprArithAST.g4 ExprArithList.g4

bin:
	for i in $(G4); do $(ANTLR) $$i; done; \
        for i in $(G4); do tmp=`basename $$i .g4`; $(JAVAC) $$tmp*.java; done

clean:
	rm -f *~ *.class *.tokens *Lexer.java *Parser.java *Listener.java
