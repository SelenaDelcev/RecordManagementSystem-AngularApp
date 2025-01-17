import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NACIONALNOST_URL } from '../app.constants';
import { Nacionalnost } from '../models/nacionalnost';

@Injectable({
  providedIn: 'root'
})
export class NacionalnostService {

  constructor(private httpClient: HttpClient) { }

  public getAllNacionalnosts(): Observable<any> {
    return this.httpClient.get(`${NACIONALNOST_URL}`);
  }

  public addNacionalnost(nacionalnost: Nacionalnost): Observable<any> {
    nacionalnost.id = 350;
    return this.httpClient.post(`${NACIONALNOST_URL}`, nacionalnost);
  }

  public updateNacionalnost(nacionalnost: Nacionalnost): Observable<any> {
    return this.httpClient.put(`${NACIONALNOST_URL}`, nacionalnost);
  }

  public deleteNacionalnost(id: number): Observable<any> {
    return this.httpClient.delete(`${NACIONALNOST_URL}/${id}`);
  }
}
