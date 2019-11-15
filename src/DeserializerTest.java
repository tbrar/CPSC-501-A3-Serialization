import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Assert;
import org.junit.Test;

public class DeserializerTest {

	@Test
	public void ATest() {
		Serializer serial = new Serializer();
		Deserializer deserial = new Deserializer();
		int par1 = 5;
		double par2 = 7.89;
		char par3 = 'd';
		boolean par4 = false;
		A a = new A(par1, par2, par3, par4);
		serial.serializeClass(a);
		deserial.deserialize(serial.getDocument());
		Assert.assertTrue(((A) deserial.objects.get(0)).prim1 == par1 
				&& ((A) deserial.objects.get(0)).prim2 == par2 
				&& ((A) deserial.objects.get(0)).prim3 == par3 
				&& ((A) deserial.objects.get(0)).prim4 == par4);
	}
	
	@Test
	public void BTest() {
		Serializer serial = new Serializer();
		Deserializer deserial = new Deserializer();
		int par1 = 8;
		double par2 = 99.2;
		char par3 = 'e';
		boolean par4 = true;
		A a = new A(par1, par2, par3, par4);
		C c= new C(3);
		c.add('f', 0);
		c.add('g', 1);
		c.add('u', 2);
		B b = new B(a,c);
		serial.serializeClass(b);
		deserial.deserialize(serial.getDocument());
		Assert.assertTrue( ( (B) deserial.objects.get(0) ).obj1.prim1 == par1 
				&& ( (B) deserial.objects.get(0) ).obj1.prim2 == par2 
				&& ( (B) deserial.objects.get(0) ).obj1.prim3 == par3 
				&& ( (B) deserial.objects.get(0) ).obj1.prim4 == par4
				&& ( (B) deserial.objects.get(0) ).obj2.array[0] == 'f'
				&& ( (B) deserial.objects.get(0) ).obj2.array[1] == 'g'
				&& ( (B) deserial.objects.get(0) ).obj2.array[2] == 'u');
	}
	
	@Test
	public void CTest() {
		Serializer serial = new Serializer();
		Deserializer deserial = new Deserializer();
		C c= new C(3);
		c.add('a', 0);
		c.add('s', 1);
		c.add('z', 2);
		serial.serializeClass(c);
		deserial.deserialize(serial.getDocument());
		Assert.assertTrue(( (C) deserial.objects.get(0)).array[0] == 'a'
				&& ( (C) deserial.objects.get(0)).array[1] == 's' 
				&& ( (C) deserial.objects.get(0)).array[2] == 'z');
	}

}
