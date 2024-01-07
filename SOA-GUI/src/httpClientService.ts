// data.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class httpClientService {
  private baseUrl = 'http://localhost:'; // Replace with your API endpoint

  constructor(private http: HttpClient) {}

  getAllAides(): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/getAll';
    console.log(url);
    return this.http.get<any>(url);
  }
  postAide(nom: string, prenom: string): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/add';
    console.log(url);
    return this.http.post<any>(url, { nom: nom, prenom: prenom });
  }

  deleteAide(id: number): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/delete/' + id;
    console.log(url);
    return this.http.delete<any>(url);
  }

  getAllBenevoles(): Observable<any> {
    let port = '8090';
    let url = this.baseUrl + port + '/benevoles/getAll';
    console.log(url);
    return this.http.get<any>(url);
  }

  getBenevolById(id: number): Observable<any> {
    let port = '8090';
    let url = this.baseUrl + port + '/benevoles/' + id;
    console.log(url);
    return this.http.get<any>(url);
  }
  postBenevole(nom: string, prenom: string): Observable<any> {
    let port = '8090';
    let url = this.baseUrl + port + '/benevoles/add';
    console.log(url);
    const user = {
      nom: nom,
      prenom: prenom
    };
    return this.http.post<any>(url,user );  }

  deleteBenevole(id: number): Observable<any> {
    let port = '8090';
    let url = this.baseUrl + port + '/benevoles/' + id;
    console.log(url);
    return this.http.delete<any>(url);
  }

  getAllBeneficiaires(): Observable<any> {
    let port = '8093';
    let url = this.baseUrl + port + '/beneficiaire/getAll';
    console.log(url);
    return this.http.get<any>(url);
  }

  postBeneficiaire(nom: string, prenom: string) {
    let port = '8093';
    console.log(nom);
    console.log(prenom);

    let url = this.baseUrl + port + '/beneficiaire/add';
    console.log(url);
    const user = {
      nom: nom,
      prenom: prenom
    };
    return this.http.post<any>(url,user );

}

  deleteBeneficiaire(id: number): Observable<any> {
    let port = '8093';
    let url = this.baseUrl + port + '/beneficiaire/' + id;
    console.log(url);
    return this.http.delete<any>(url);
  }
  
  



  getAllValideurs(): Observable<any> {
    let port = '8094';
    let url = this.baseUrl + port + '/valideurs/getAll';
    console.log(url);
    return this.http.get<any>(url);
  }
  postValideur(nom: string, prenom: string): Observable<any> {
    let port = '8094';
    let url = this.baseUrl + port + '/valideurs/add';
    console.log(url);
    return this.http.post<any>(url, { nom: nom, prenom: prenom });
  }

  deleteValideur(id: number): Observable<any> {
    let port = '8094';
    let url = this.baseUrl + port + '/valideurs/delete/' + id;
    console.log(url);
    return this.http.delete<any>(url);
  }


  validerAide(id: number,validator_id :number): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/valider';
    console.log(url);
    return this.http.post<any>(url, { id: id, traite_par: validator_id});
  }

  refuserAide(id:number,validator_id :number, motif:string): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/rejeter';
    console.log(url);
    return this.http.post<any>(url, { id: id, traite_par: validator_id, motif: motif });
  }

  affecterAide(id:number,validator_id :number,benevol_id :number): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/affecter/';
    console.log(url);
    return this.http.post<any>(url, { id: id, traite_par: validator_id, status: 'affecte' , benevol_id : benevol_id});
  }

  aidesByBeneficiaire(id:number): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/getByDemande/'+id;
    console.log(url);
    return this.http.get<any>(url);
  }

  aidesByBenevole(id:number): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/getByBenevol/'+id;
    console.log(url);
    return this.http.get<any>(url);
  }

  aidesByValideur(id:number): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/getByManager/'+id;
    console.log(url);
    return this.http.get<any>(url);
  }

}
