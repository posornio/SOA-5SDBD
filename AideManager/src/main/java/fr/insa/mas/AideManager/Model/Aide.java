package fr.insa.mas.AideManager.Model;

public class Aide {
	private int id;
	private String status;
	private String type;
	private String motif_rejet;
	private int benevol_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMotif_rejet() {
		return motif_rejet;
	}
	public void setMotif_rejet(String motif_rejet) {
		this.motif_rejet = motif_rejet;
	}
	public int getBenevol_id() {
		return benevol_id;
	}
	public void setBenevol_id(int benevol_id) {
		this.benevol_id = benevol_id;
	}
	public Aide(int id, String status, String type, String motif_rejet, int benevol_id) {
		super();
		this.id = id;
		this.status = status;
		this.type = type;
		this.motif_rejet = motif_rejet;
		this.benevol_id = benevol_id;
	}

}
