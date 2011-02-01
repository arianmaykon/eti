package br.ufc.eti.poo.exercicios.exercicio3;

//Demonstrate various Vector operations. 
import java.util.*;

class VectorDemo {

	public static void main(String args[]) {
		// initial size is 3, increment is 2
		Vector v = new Vector(3, 2);
		System.out.println("Initial size: " + v.size());
		System.out.println("Initial capacity: " + v.capacity());
		AcaoON acao = new AcaoON();
		acao.setCodigo(1);
		acao.setDescricao("teste");
		v.addElement(acao);

		AcaoON a2 = new AcaoON();
		a2.setCodigo(1);
		a2.setDescricao("teste");
		if (v.contains(a2))
			System.out.println("Vector contains 3.");


/*		// initial size is 3, increment is 2
		Vector v = new Vector(3, 2);
		System.out.println("Initial size: " + v.size());
		System.out.println("Initial capacity: " + v.capacity());
		v.addElement(new Integer(1));
		v.addElement(new Integer(2));
		v.addElement(new Integer(3));
		v.addElement(new Integer(4));
		System.out.println("Capacity after four additions: " + v.capacity());
		v.addElement(new Double(5.45));
		System.out.println("Current capacity: " + v.capacity());
		v.addElement(new Double(6.08));
		v.addElement(new Integer(7));
		System.out.println("Current capacity: " + v.capacity());
		v.addElement(new Float(9.4));
		v.addElement(new Integer(10));
		System.out.println("Current capacity: " + v.capacity());
		v.addElement(new Integer(11));
		v.addElement(new Integer(12));
		System.out.println("First element: " + (Integer) v.firstElement());
		System.out.println("Last element: " + (Integer) v.lastElement());
		if (v.contains(new Integer(3)))
			System.out.println("Vector contains 3.");
		// enumerate the elements in the vector.
		Enumeration vEnum = v.elements();
		System.out.println("\\nElements in vector:");
		while (vEnum.hasMoreElements())
			System.out.print(vEnum.nextElement() + " ");
		System.out.println();*/
	}
}