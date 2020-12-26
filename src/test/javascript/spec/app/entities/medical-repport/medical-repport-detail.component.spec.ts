import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EHealthTestModule } from '../../../test.module';
import { MedicalRepportDetailComponent } from 'app/entities/medical-repport/medical-repport-detail.component';
import { MedicalRepport } from 'app/shared/model/medical-repport.model';

describe('Component Tests', () => {
  describe('MedicalRepport Management Detail Component', () => {
    let comp: MedicalRepportDetailComponent;
    let fixture: ComponentFixture<MedicalRepportDetailComponent>;
    const route = ({ data: of({ medicalRepport: new MedicalRepport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EHealthTestModule],
        declarations: [MedicalRepportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedicalRepportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalRepportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medicalRepport on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medicalRepport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
