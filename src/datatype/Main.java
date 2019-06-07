package datatype;

import java.io.*;
import java.util.function.*;

import java_cup.runtime.*;
import datatype.tree.*;

public class Main {

	<C> void forEach(Lista<C> l, Consumer<C> fun) {
		while (l!=null) {
			if (l.val!=null) fun.accept(l.val);
			l = l.prox;
		}
		
	}
	
	<C> void join(Lista<C> l, String sep, Function<C,String> fun) {
		String result = "";
		while (l!=null) {
			result += fun.apply(l.val);
			if (l.prox!=null) result += sep;
			l = l.prox;
		}
		codigo.append(result);
	}
	StringBuffer codigo = new StringBuffer(); 
	
	Consumer<Classe> geraArgumentos = c -> {
	   join(c.arguments,",", arg -> arg.type + " " + arg.name);
	};
	
	public void geraCodigo(Lista<Classe> cl) {
		
		forEach(cl, c -> {
			codigo.append((c.eAbstrata?"abstract ":"")+"class " + c.name + " extends " + c.superClasse + "{\n");
			
			forEach(c.arguments, arg -> {
				codigo.append("   " + arg.type + " " + arg.name + ";\n");
				codigo.append("   " + arg.type + " get" + arg.name + "() { return " + arg.name + ";}\n");
				if (!arg.isConst)
					codigo.append("   " + arg.type + " set" + arg.name + "("+ arg.type + " _v) { " + arg.name + " = _v;}\n");
				codigo.append("\n");	
			});
			
			codigo.append("   " + c.name + "(");
			geraArgumentos.accept(c);

			codigo.append(") {\n");
			Lista<Arg> args = c.arguments;
			while (args!=null) {
				Arg arg = args.val;
				codigo.append("      this." + arg.name + " = " + arg.name + ";\n");
				args = args.prox;
			}
		
			codigo.append("   }\n");
			codigo.append("}\n\n");
		});
		
	    codigo.append("interface AbstractFactory {\n");
	    forEach(cl, c ->{
	    	codigo.append("   "+c.name + " create"+c.name+"(");
	    	geraArgumentos.accept(c);
	    	codigo.append(");\n");
	    });
	    codigo.append("}\n");

	    codigo.append("class ConcreteFactory implements AbstractFactory {\n");
	    forEach(cl, c ->{
	    	codigo.append("   "+c.name + " create"+c.name+"(");
	    	geraArgumentos.accept(c);
	    	codigo.append(") {\n");
	    	codigo.append("      return new "+c.name+"(");
	    	join(c.arguments," , ", a -> a.name);
	    	codigo.append(");\n");
	    	codigo.append("   }\n");
	    
	    });
	    codigo.append("}\n");
	    
	    
	}
	
	public static void main(String[] args) throws Exception {
		InputStream stream = new FileInputStream("teste.tree");
		Reader reader = new InputStreamReader(stream);
		Scanner scanner = new Scanner(reader);
		Parser parser = new Parser(scanner);
		
		Symbol s = parser.parse();
		Lista<Classe> result = (Lista<Classe>)s.value;
		Main m = new Main();
		m.geraCodigo(result);
		
		System.out.println("codigo gerado = \n" + m.codigo);
		
	}

}
