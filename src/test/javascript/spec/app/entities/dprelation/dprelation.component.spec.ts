import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EHealthTestModule } from '../../../test.module';
import { DprelationComponent } from 'app/entities/dprelation/dprelation.component';
import { DprelationService } from 'app/entities/dprelation/dprelation.service';
import { Dprelation } from 'app/shared/model/dprelation.model';

describe('Component Tests', () => {
  describe('Dprelation Management Component', () => {
    let comp: DprelationComponent;
    let fixture: ComponentFixture<DprelationComponent>;
    let service: DprelationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EHealthTestModule],
        declarations: [DprelationComponent],
      })
        .overrideTemplate(DprelationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DprelationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DprelationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Dprelation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dprelations && comp.dprelations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
