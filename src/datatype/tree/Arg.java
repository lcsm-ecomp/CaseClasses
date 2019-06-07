package datatype.tree;

public class Arg {
	public boolean isConst;
	public String name, type;
	public Arg(boolean isConst, String type, String name) {
		this.isConst = isConst;
		this.name = name;
		this.type = type;
	}
	public String toString() {
		if (!isConst)
		    return type + " " + name;
		else
			return "const " + type + " " + name;
	}

}
