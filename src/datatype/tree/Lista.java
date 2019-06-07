package datatype.tree;

import java.util.function.Consumer;

public class Lista<C> {
	public C val;
	public Lista<C> prox;
	public Lista(C v, Lista<C> p) { val = v; prox = p; }
	public String toString() {
		return val + " , " + prox;
	}
	
	public void forEach(Consumer<C> fun) {
		if (val!=null)
			fun.accept(val);
		if (prox!=null)
			prox.forEach(fun);
	}
}
