import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EHealthTestModule } from '../../../test.module';
import { MedicalRepportUpdateComponent } from 'app/entities/medical-repport/medical-repport-update.component';
import { MedicalRepportService } from 'app/entities/medical-repport/medical-repport.service';
import { MedicalRepport } from 'app/shared/model/medical-repport.model';

describe('Component Tests', () => {
  describe('MedicalRepport Management Update Component', () => {
    let comp: MedicalRepportUpdateComponent;
    let fixture: ComponentFixture<MedicalRepportUpdateComponent>;
    let service: MedicalRepportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EHealthTestModule],
        declarations: [MedicalRepportUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedicalRepportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalRepportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalRepportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalRepport(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalRepport();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
