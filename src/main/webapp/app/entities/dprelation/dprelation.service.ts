import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDprelation } from 'app/shared/model/dprelation.model';

type EntityResponseType = HttpResponse<IDprelation>;
type EntityArrayResponseType = HttpResponse<IDprelation[]>;

@Injectable({ providedIn: 'root' })
export class DprelationService {
  public resourceUrl = SERVER_API_URL + 'api/dprelations';

  constructor(protected http: HttpClient) {}

  create(dprelation: IDprelation): Observable<EntityResponseType> {
    return this.http.post<IDprelation>(this.resourceUrl, dprelation, { observe: 'response' });
  }

  update(dprelation: IDprelation): Observable<EntityResponseType> {
    return this.http.put<IDprelation>(this.resourceUrl, dprelation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDprelation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDprelation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
