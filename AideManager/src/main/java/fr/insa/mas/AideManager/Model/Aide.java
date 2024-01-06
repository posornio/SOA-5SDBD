package fr.insa.mas.AideManager.Model;

public class Aide {
	private int id;
	private String type;
	
	private String status;

	private String motif_rejet;
	private int benevol_id;

	private int traite_par;

	private int demande_par;
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

	public void setTraite_par(int traite_par) {
		this.traite_par = traite_par;
	}
	public int getTraite_par() {
		return traite_par;
	}

	public int getDemande_par() {
		return demande_par;
	}
	public void setDemande_par(int demande_par) {
		this.demande_par = demande_par;
	}
	public void setBenevol_id(int benevol_id) {
		this.benevol_id = benevol_id;
	}
	public Aide(int id, String status, String type, String motif_rejet, int benevol_id, int traite_par, int demande_par) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;

		this.motif_rejet = motif_rejet;
		this.benevol_id = benevol_id;
		this.traite_par = traite_par;
		this.demande_par = demande_par;
	}

	public Aide() {
		super();
	}

	public Aide(String status, String type, String motif_rejet, int benevol_id,int demande_par) {
		super();
		this.status = status;
		this.type = type;
		this.motif_rejet = motif_rejet;
		this.benevol_id = benevol_id;
		this.demande_par = demande_par;
	}

	public Aide(int id, String status, String motif_rejet, int traite_par){
		super();
		this.id = id;
		this.status = status;
		this.motif_rejet = motif_rejet;
		this.traite_par = traite_par;
	}
}
