import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EHealthTestModule } from '../../../test.module';
import { DprelationUpdateComponent } from 'app/entities/dprelation/dprelation-update.component';
import { DprelationService } from 'app/entities/dprelation/dprelation.service';
import { Dprelation } from 'app/shared/model/dprelation.model';

describe('Component Tests', () => {
  describe('Dprelation Management Update Component', () => {
    let comp: DprelationUpdateComponent;
    let fixture: ComponentFixture<DprelationUpdateComponent>;
    let service: DprelationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EHealthTestModule],
        declarations: [DprelationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DprelationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DprelationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DprelationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Dprelation(123);
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
        const entity = new Dprelation();
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
