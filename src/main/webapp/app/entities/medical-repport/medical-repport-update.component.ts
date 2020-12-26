import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedicalRepport, MedicalRepport } from 'app/shared/model/medical-repport.model';
import { MedicalRepportService } from './medical-repport.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-medical-repport-update',
  templateUrl: './medical-repport-update.component.html',
})
export class MedicalRepportUpdateComponent implements OnInit {
  isSaving = false;
  patients: IPatient[] = [];

  editForm = this.fb.group({
    id: [],
    typeOfRepport: [null, [Validators.required]],
    result: [null, [Validators.required]],
    medicalRepportPatient: [],
  });

  constructor(
    protected medicalRepportService: MedicalRepportService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalRepport }) => {
      this.updateForm(medicalRepport);

      this.patientService.query().subscribe((res: HttpResponse<IPatient[]>) => (this.patients = res.body || []));
    });
  }

  updateForm(medicalRepport: IMedicalRepport): void {
    this.editForm.patchValue({
      id: medicalRepport.id,
      typeOfRepport: medicalRepport.typeOfRepport,
      result: medicalRepport.result,
      medicalRepportPatient: medicalRepport.medicalRepportPatient,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalRepport = this.createFromForm();
    if (medicalRepport.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalRepportService.update(medicalRepport));
    } else {
      this.subscribeToSaveResponse(this.medicalRepportService.create(medicalRepport));
    }
  }

  private createFromForm(): IMedicalRepport {
    return {
      ...new MedicalRepport(),
      id: this.editForm.get(['id'])!.value,
      typeOfRepport: this.editForm.get(['typeOfRepport'])!.value,
      result: this.editForm.get(['result'])!.value,
      medicalRepportPatient: this.editForm.get(['medicalRepportPatient'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalRepport>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IPatient): any {
    return item.id;
  }
}
