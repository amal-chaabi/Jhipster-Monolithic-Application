import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDprelation } from 'app/shared/model/dprelation.model';
import { DprelationService } from './dprelation.service';
import { DprelationDeleteDialogComponent } from './dprelation-delete-dialog.component';

@Component({
  selector: 'jhi-dprelation',
  templateUrl: './dprelation.component.html',
})
export class DprelationComponent implements OnInit, OnDestroy {
  dprelations?: IDprelation[];
  eventSubscriber?: Subscription;

  constructor(protected dprelationService: DprelationService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.dprelationService.query().subscribe((res: HttpResponse<IDprelation[]>) => (this.dprelations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDprelations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDprelation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDprelations(): void {
    this.eventSubscriber = this.eventManager.subscribe('dprelationListModification', () => this.loadAll());
  }

  delete(dprelation: IDprelation): void {
    const modalRef = this.modalService.open(DprelationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dprelation = dprelation;
  }
}
