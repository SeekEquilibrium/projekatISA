import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { CottageDefineAvailabilityComponent } from "./cottage-define-availability.component";

describe("CottageDefineAvailabilityComponent", () => {
    let component: CottageDefineAvailabilityComponent;
    let fixture: ComponentFixture<CottageDefineAvailabilityComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CottageDefineAvailabilityComponent],
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CottageDefineAvailabilityComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
