// data.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class httpClientService {
  private baseUrl = 'http:/localhost:'; // Replace with your API endpoint

  constructor(private http: HttpClient) {}

  getAllAides(): Observable<any> {
    let port = '8092';
    let url = this.baseUrl + port + '/aide/getAll';
    return this.http.get<any>(url);
  }
}
