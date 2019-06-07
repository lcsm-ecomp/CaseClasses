package datatype.tree;

public class Classe {
	public boolean eAbstrata;
	public String name,superClasse;
	public Lista<Arg> arguments;
	
	public Classe(boolean abs, String name, Lista<Arg> arg, String sup) {
		this.eAbstrata = abs;
		this.name = name;
		this.superClasse = sup;
		this.arguments = arg;
	}
	
	public String toString() {
			return (eAbstrata?"abstract ":"")+"class " + name + "(" + arguments + ")" + " extends " + superClasse;
	}
	

}
