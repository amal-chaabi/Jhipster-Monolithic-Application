import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EHealthSharedModule } from 'app/shared/shared.module';
import { MedicalRepportComponent } from './medical-repport.component';
import { MedicalRepportDetailComponent } from './medical-repport-detail.component';
import { MedicalRepportUpdateComponent } from './medical-repport-update.component';
import { MedicalRepportDeleteDialogComponent } from './medical-repport-delete-dialog.component';
import { medicalRepportRoute } from './medical-repport.route';

@NgModule({
  imports: [EHealthSharedModule, RouterModule.forChild(medicalRepportRoute)],
  declarations: [
    MedicalRepportComponent,
    MedicalRepportDetailComponent,
    MedicalRepportUpdateComponent,
    MedicalRepportDeleteDialogComponent,
  ],
  entryComponents: [MedicalRepportDeleteDialogComponent],
})
export class EHealthMedicalRepportModule {}
