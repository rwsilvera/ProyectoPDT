package com.entitiesProyecto;

public enum Semestre {

	SEMESTRE_1(1),
	SEMESTRE_2(2),
	SEMESTRE_3(3),
	SEMESTRE_4(4),
	SEMESTRE_5(5),
	SEMESTRE_6(6),
	SEMESTRE_7(7),
	SEMESTRE_8(8);

	private int numeroSemestre;

	private Semestre(int numeroSemestre) {
		this.numeroSemestre = numeroSemestre;
	}

	public int getNumeroSemestre() {
		return numeroSemestre;
	}
	
}
