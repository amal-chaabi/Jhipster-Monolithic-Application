import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDoctor, Doctor } from 'app/shared/model/doctor.model';
import { DoctorService } from './doctor.service';

@Component({
  selector: 'jhi-doctor-update',
  templateUrl: './doctor-update.component.html',
})
export class DoctorUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lastname: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    firstname: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    title: [],
    experience: [],
    age: [null, [Validators.min(20)]],
    typeOfSpeciality: [null, [Validators.required]],
  });

  constructor(protected doctorService: DoctorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ doctor }) => {
      this.updateForm(doctor);
    });
  }

  updateForm(doctor: IDoctor): void {
    this.editForm.patchValue({
      id: doctor.id,
      lastname: doctor.lastname,
      firstname: doctor.firstname,
      title: doctor.title,
      experience: doctor.experience,
      age: doctor.age,
      typeOfSpeciality: doctor.typeOfSpeciality,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const doctor = this.createFromForm();
    if (doctor.id !== undefined) {
      this.subscribeToSaveResponse(this.doctorService.update(doctor));
    } else {
      this.subscribeToSaveResponse(this.doctorService.create(doctor));
    }
  }

  private createFromForm(): IDoctor {
    return {
      ...new Doctor(),
      id: this.editForm.get(['id'])!.value,
      lastname: this.editForm.get(['lastname'])!.value,
      firstname: this.editForm.get(['firstname'])!.value,
      title: this.editForm.get(['title'])!.value,
      experience: this.editForm.get(['experience'])!.value,
      age: this.editForm.get(['age'])!.value,
      typeOfSpeciality: this.editForm.get(['typeOfSpeciality'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDoctor>>): void {
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
