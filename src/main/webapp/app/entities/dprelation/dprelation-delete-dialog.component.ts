import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDprelation } from 'app/shared/model/dprelation.model';
import { DprelationService } from './dprelation.service';

@Component({
  templateUrl: './dprelation-delete-dialog.component.html',
})
export class DprelationDeleteDialogComponent {
  dprelation?: IDprelation;

  constructor(
    protected dprelationService: DprelationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dprelationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dprelationListModification');
      this.activeModal.close();
    });
  }
}
