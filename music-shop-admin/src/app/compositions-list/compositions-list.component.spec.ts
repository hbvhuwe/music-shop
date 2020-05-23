import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositionsListComponent } from './compositions-list.component';

describe('CompositionsListComponent', () => {
  let component: CompositionsListComponent;
  let fixture: ComponentFixture<CompositionsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositionsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
