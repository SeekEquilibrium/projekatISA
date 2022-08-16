import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import {
    NgbDate,
    NgbCalendar,
    NgbDateStruct,
} from "@ng-bootstrap/ng-bootstrap";

@Component({
    selector: "app-cottage-define-availability",
    templateUrl: "./cottage-define-availability.component.html",
    styleUrls: ["./cottage-define-availability.component.css"],
})
export class CottageDefineAvailabilityComponent implements OnInit {
    hoveredDate: NgbDate | null = null;
    now = new Date();
    minDate: NgbDateStruct = {
        year: this.now.getFullYear(),
        month: this.now.getMonth() + 1,
        day: this.now.getDate(),
    };
    fromDate: NgbDate;
    toDate: NgbDate | null = null;
    existingAvailabilityStart: NgbDate;
    existingAvailabilityEnd: NgbDate;

    constructor(calendar: NgbCalendar) {
        this.fromDate = calendar.getToday();
        this.toDate = calendar.getNext(calendar.getToday(), "d", 10);
        this.existingAvailabilityStart = new NgbDate(2022, 8, 3);
        this.existingAvailabilityEnd = new NgbDate(2022, 9, 3);
    }

    onDateSelection(date: NgbDate) {
        if (!this.fromDate && !this.toDate) {
            this.fromDate = date;
        } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
            this.toDate = date;
        } else {
            this.toDate = null;
            this.fromDate = date;
        }
    }

    markDate(date: NgbDate) {
        return (
            date.before(this.existingAvailabilityEnd) &&
            date.after(this.existingAvailabilityStart)
        );
    }

    isHovered(date: NgbDate) {
        return (
            this.fromDate &&
            !this.toDate &&
            this.hoveredDate &&
            date.after(this.fromDate) &&
            date.before(this.hoveredDate)
        );
    }

    isInside(date: NgbDate) {
        return (
            this.toDate && date.after(this.fromDate) && date.before(this.toDate)
        );
    }

    isRange(date: NgbDate) {
        return (
            date.equals(this.fromDate) ||
            (this.toDate && date.equals(this.toDate)) ||
            this.isInside(date) ||
            this.isHovered(date)
        );
    }

    ngOnInit() {}
}
