import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EHealthSharedModule } from 'app/shared/shared.module';
import { DprelationComponent } from './dprelation.component';
import { DprelationDetailComponent } from './dprelation-detail.component';
import { DprelationUpdateComponent } from './dprelation-update.component';
import { DprelationDeleteDialogComponent } from './dprelation-delete-dialog.component';
import { dprelationRoute } from './dprelation.route';

@NgModule({
  imports: [EHealthSharedModule, RouterModule.forChild(dprelationRoute)],
  declarations: [DprelationComponent, DprelationDetailComponent, DprelationUpdateComponent, DprelationDeleteDialogComponent],
  entryComponents: [DprelationDeleteDialogComponent],
})
export class EHealthDprelationModule {}
