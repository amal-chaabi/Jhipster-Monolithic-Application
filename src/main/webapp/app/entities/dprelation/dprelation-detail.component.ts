import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDprelation } from 'app/shared/model/dprelation.model';

@Component({
  selector: 'jhi-dprelation-detail',
  templateUrl: './dprelation-detail.component.html',
})
export class DprelationDetailComponent implements OnInit {
  dprelation: IDprelation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dprelation }) => (this.dprelation = dprelation));
  }

  previousState(): void {
    window.history.back();
  }
}
