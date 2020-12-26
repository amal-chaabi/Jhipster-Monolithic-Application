import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDprelation, Dprelation } from 'app/shared/model/dprelation.model';
import { DprelationService } from './dprelation.service';
import { DprelationComponent } from './dprelation.component';
import { DprelationDetailComponent } from './dprelation-detail.component';
import { DprelationUpdateComponent } from './dprelation-update.component';

@Injectable({ providedIn: 'root' })
export class DprelationResolve implements Resolve<IDprelation> {
  constructor(private service: DprelationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDprelation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dprelation: HttpResponse<Dprelation>) => {
          if (dprelation.body) {
            return of(dprelation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Dprelation());
  }
}

export const dprelationRoute: Routes = [
  {
    path: '',
    component: DprelationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dprelations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DprelationDetailComponent,
    resolve: {
      dprelation: DprelationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dprelations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DprelationUpdateComponent,
    resolve: {
      dprelation: DprelationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dprelations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DprelationUpdateComponent,
    resolve: {
      dprelation: DprelationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dprelations',
    },
    canActivate: [UserRouteAccessService],
  },
];
