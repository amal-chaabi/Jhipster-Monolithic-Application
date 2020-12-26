import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedicalRepport } from 'app/shared/model/medical-repport.model';

type EntityResponseType = HttpResponse<IMedicalRepport>;
type EntityArrayResponseType = HttpResponse<IMedicalRepport[]>;

@Injectable({ providedIn: 'root' })
export class MedicalRepportService {
  public resourceUrl = SERVER_API_URL + 'api/medical-repports';

  constructor(protected http: HttpClient) {}

  create(medicalRepport: IMedicalRepport): Observable<EntityResponseType> {
    return this.http.post<IMedicalRepport>(this.resourceUrl, medicalRepport, { observe: 'response' });
  }

  update(medicalRepport: IMedicalRepport): Observable<EntityResponseType> {
    return this.http.put<IMedicalRepport>(this.resourceUrl, medicalRepport, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedicalRepport>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedicalRepport[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
