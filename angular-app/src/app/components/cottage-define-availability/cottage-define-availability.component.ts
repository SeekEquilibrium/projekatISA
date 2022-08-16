import { Component, Input, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import {
    NgbDate,
    NgbCalendar,
    NgbDateStruct,
} from "@ng-bootstrap/ng-bootstrap";
import { CottageService } from "src/app/service/cottage.service";
import { MAT_DIALOG_DATA } from "@angular/material";
import { Inject } from "@angular/core";

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
    availability: [];
    availability_dates: any = [];
    fromDate: NgbDate | null = null;
    toDate: NgbDate | null = null;
    existingAvailabilityStart: NgbDate;
    existingAvailabilityEnd: NgbDate;

    constructor(
        calendar: NgbCalendar,
        private cottageService: CottageService,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {
        // this.toDate = calendar.getNext(calendar.getToday(), "d", 10);
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
        // this.availability_dates.forEach((day: any) => {
        for (let i = 0; i <= this.availability_dates.length; i++) {
            if (date.equals(this.availability_dates[i])) {
                return true;
            }
        }
        return false;
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

    ngOnInit() {
        console.log(this.data.cottageId);
        this.cottageService
            .getCottageAvailability(this.data.cottageId)
            .subscribe(
                (response) => {
                    this.availability = response;
                    this.availability.forEach((element: any) => {
                        let date = new NgbDate(
                            element.date[0],
                            element.date[1],
                            element.date[2]
                        );
                        this.availability_dates.push(date);
                    });
                    console.log(response);
                },
                (error) => {
                    console.log(error);
                }
            );
    }

    saveButtonValidation() {
        return !this.fromDate || !this.toDate;
    }
}
