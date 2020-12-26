import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDprelation, Dprelation } from 'app/shared/model/dprelation.model';
import { DprelationService } from './dprelation.service';

@Component({
  selector: 'jhi-dprelation-update',
  templateUrl: './dprelation-update.component.html',
})
export class DprelationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected dprelationService: DprelationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dprelation }) => {
      this.updateForm(dprelation);
    });
  }

  updateForm(dprelation: IDprelation): void {
    this.editForm.patchValue({
      id: dprelation.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dprelation = this.createFromForm();
    if (dprelation.id !== undefined) {
      this.subscribeToSaveResponse(this.dprelationService.update(dprelation));
    } else {
      this.subscribeToSaveResponse(this.dprelationService.create(dprelation));
    }
  }

  private createFromForm(): IDprelation {
    return {
      ...new Dprelation(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDprelation>>): void {
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
