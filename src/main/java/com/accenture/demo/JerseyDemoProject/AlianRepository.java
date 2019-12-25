package com.accenture.demo.JerseyDemoProject;

import java.util.ArrayList;
import java.util.List;

public class AlianRepository {
	public List<Alian> alian;

	public AlianRepository() {
		alian = new ArrayList<Alian>();

		Alian a1 = new Alian();
		a1.setName("Dinesh");
		a1.setPoints(10);

		Alian a2 = new Alian();
		a2.setName("Tharun");
		a2.setPoints(5);

		alian.add(a1);
		alian.add(a2);
	}

	public List<Alian> getAlians() {
		return alian;
	}

	public Alian getAlian(String name) {
		for (Alian alian : alian) {
			if (alian.getName().equalsIgnoreCase(name)) {
				return alian;
			}
		}
		return new Alian();

	}

	public void createAlian(Alian alian2) {
		alian.add(alian2);
	}
}
