import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedicalRepport, MedicalRepport } from 'app/shared/model/medical-repport.model';
import { MedicalRepportService } from './medical-repport.service';
import { MedicalRepportComponent } from './medical-repport.component';
import { MedicalRepportDetailComponent } from './medical-repport-detail.component';
import { MedicalRepportUpdateComponent } from './medical-repport-update.component';

@Injectable({ providedIn: 'root' })
export class MedicalRepportResolve implements Resolve<IMedicalRepport> {
  constructor(private service: MedicalRepportService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedicalRepport> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medicalRepport: HttpResponse<MedicalRepport>) => {
          if (medicalRepport.body) {
            return of(medicalRepport.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedicalRepport());
  }
}

export const medicalRepportRoute: Routes = [
  {
    path: '',
    component: MedicalRepportComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedicalRepports',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MedicalRepportDetailComponent,
    resolve: {
      medicalRepport: MedicalRepportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedicalRepports',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MedicalRepportUpdateComponent,
    resolve: {
      medicalRepport: MedicalRepportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedicalRepports',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MedicalRepportUpdateComponent,
    resolve: {
      medicalRepport: MedicalRepportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedicalRepports',
    },
    canActivate: [UserRouteAccessService],
  },
];
