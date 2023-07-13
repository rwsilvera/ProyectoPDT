package com.entitiesProyecto;

public enum Generacion {
	GEN_2015(2015),
	GEN_2016(2016),
	GEN_2017(2017),
	GEN_2018(2018),
	GEN_2019(2019),
	GEN_2020(2020),
	GEN_2021(2021),
	GEN_2022(2022),
	GEN_2023(2023),
	GEN_2024(2024),
	GEN_2025(2025),
	GEN_2026(2026),
	GEN_2027(2027),
	GEN_2028(2028),
	GEN_2029(2029),
	GEN_2030(2030);

	private int year;

	Generacion(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}
}
