import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EHealthTestModule } from '../../../test.module';
import { MedicalRepportComponent } from 'app/entities/medical-repport/medical-repport.component';
import { MedicalRepportService } from 'app/entities/medical-repport/medical-repport.service';
import { MedicalRepport } from 'app/shared/model/medical-repport.model';

describe('Component Tests', () => {
  describe('MedicalRepport Management Component', () => {
    let comp: MedicalRepportComponent;
    let fixture: ComponentFixture<MedicalRepportComponent>;
    let service: MedicalRepportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EHealthTestModule],
        declarations: [MedicalRepportComponent],
      })
        .overrideTemplate(MedicalRepportComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalRepportComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalRepportService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedicalRepport(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medicalRepports && comp.medicalRepports[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
