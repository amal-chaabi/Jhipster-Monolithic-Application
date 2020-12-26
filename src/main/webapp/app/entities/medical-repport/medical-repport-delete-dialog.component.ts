import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicalRepport } from 'app/shared/model/medical-repport.model';
import { MedicalRepportService } from './medical-repport.service';

@Component({
  templateUrl: './medical-repport-delete-dialog.component.html',
})
export class MedicalRepportDeleteDialogComponent {
  medicalRepport?: IMedicalRepport;

  constructor(
    protected medicalRepportService: MedicalRepportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medicalRepportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medicalRepportListModification');
      this.activeModal.close();
    });
  }
}
