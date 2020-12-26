import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EHealthTestModule } from '../../../test.module';
import { DprelationDetailComponent } from 'app/entities/dprelation/dprelation-detail.component';
import { Dprelation } from 'app/shared/model/dprelation.model';

describe('Component Tests', () => {
  describe('Dprelation Management Detail Component', () => {
    let comp: DprelationDetailComponent;
    let fixture: ComponentFixture<DprelationDetailComponent>;
    const route = ({ data: of({ dprelation: new Dprelation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EHealthTestModule],
        declarations: [DprelationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DprelationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DprelationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dprelation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dprelation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
