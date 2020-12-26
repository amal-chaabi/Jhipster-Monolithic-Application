import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMedicalRepport } from 'app/shared/model/medical-repport.model';
import { MedicalRepportService } from './medical-repport.service';
import { MedicalRepportDeleteDialogComponent } from './medical-repport-delete-dialog.component';

@Component({
  selector: 'jhi-medical-repport',
  templateUrl: './medical-repport.component.html',
})
export class MedicalRepportComponent implements OnInit, OnDestroy {
  medicalRepports?: IMedicalRepport[];
  eventSubscriber?: Subscription;

  constructor(
    protected medicalRepportService: MedicalRepportService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.medicalRepportService.query().subscribe((res: HttpResponse<IMedicalRepport[]>) => (this.medicalRepports = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMedicalRepports();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMedicalRepport): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMedicalRepports(): void {
    this.eventSubscriber = this.eventManager.subscribe('medicalRepportListModification', () => this.loadAll());
  }

  delete(medicalRepport: IMedicalRepport): void {
    const modalRef = this.modalService.open(MedicalRepportDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.medicalRepport = medicalRepport;
  }
}
