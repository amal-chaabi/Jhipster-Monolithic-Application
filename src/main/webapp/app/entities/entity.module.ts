import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'doctor',
        loadChildren: () => import('./doctor/doctor.module').then(m => m.EHealthDoctorModule),
      },
      {
        path: 'patient',
        loadChildren: () => import('./patient/patient.module').then(m => m.EHealthPatientModule),
      },
      {
        path: 'dprelation',
        loadChildren: () => import('./dprelation/dprelation.module').then(m => m.EHealthDprelationModule),
      },
      {
        path: 'medical-repport',
        loadChildren: () => import('./medical-repport/medical-repport.module').then(m => m.EHealthMedicalRepportModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EHealthEntityModule {}
