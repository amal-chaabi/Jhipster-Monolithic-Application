import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPatient, Patient } from 'app/shared/model/patient.model';
import { PatientService } from './patient.service';

@Component({
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html',
})
export class PatientUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lastname: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(25)]],
    firstname: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(25)]],
    confidentialityAgreement: [null, [Validators.required]],
    age: [null, [Validators.required]],
    hometown: [null, [Validators.required]],
    adress: [null, [Validators.required]],
    socialSecurityNumber: [null, [Validators.required]],
    phonenumber: [null, [Validators.required]],
  });

  constructor(protected patientService: PatientService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patient }) => {
      this.updateForm(patient);
    });
  }

  updateForm(patient: IPatient): void {
    this.editForm.patchValue({
      id: patient.id,
      lastname: patient.lastname,
      firstname: patient.firstname,
      confidentialityAgreement: patient.confidentialityAgreement,
      age: patient.age,
      hometown: patient.hometown,
      adress: patient.adress,
      socialSecurityNumber: patient.socialSecurityNumber,
      phonenumber: patient.phonenumber,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const patient = this.createFromForm();
    if (patient.id !== undefined) {
      this.subscribeToSaveResponse(this.patientService.update(patient));
    } else {
      this.subscribeToSaveResponse(this.patientService.create(patient));
    }
  }

  private createFromForm(): IPatient {
    return {
      ...new Patient(),
      id: this.editForm.get(['id'])!.value,
      lastname: this.editForm.get(['lastname'])!.value,
      firstname: this.editForm.get(['firstname'])!.value,
      confidentialityAgreement: this.editForm.get(['confidentialityAgreement'])!.value,
      age: this.editForm.get(['age'])!.value,
      hometown: this.editForm.get(['hometown'])!.value,
      adress: this.editForm.get(['adress'])!.value,
      socialSecurityNumber: this.editForm.get(['socialSecurityNumber'])!.value,
      phonenumber: this.editForm.get(['phonenumber'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>): void {
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
}
