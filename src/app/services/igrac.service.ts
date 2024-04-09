import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IGRAC_URL, IGRAC_URL_ZATIM_URL } from '../app.constants';
import { Igrac } from '../models/igrac';

@Injectable({
  providedIn: 'root'
})
export class IgracService {

  constructor(private httpClient: HttpClient) { }

  public getIgracZaTimID(id: number): Observable<any> {
    return this.httpClient.get(`${IGRAC_URL_ZATIM_URL}/${id}`);
  }

  public addIgrac(igrac: Igrac): Observable<any> {
    igrac.id = 150;
    return this.httpClient.post(`${IGRAC_URL}`, igrac);
  }

  public updateIgrac(igrac: Igrac): Observable<any> {
    return this.httpClient.put(`${IGRAC_URL}`, igrac);
  }

  public deleteIgrac(id: number): Observable<any> {
    return this.httpClient.delete(`${IGRAC_URL}/${id}`);
  }
}
