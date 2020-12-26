import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicalRepport } from 'app/shared/model/medical-repport.model';

@Component({
  selector: 'jhi-medical-repport-detail',
  templateUrl: './medical-repport-detail.component.html',
})
export class MedicalRepportDetailComponent implements OnInit {
  medicalRepport: IMedicalRepport | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalRepport }) => (this.medicalRepport = medicalRepport));
  }

  previousState(): void {
    window.history.back();
  }
}
