import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { NgbDate, NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";
import { BoatService } from "src/app/service/boat.service";

@Component({
    selector: "app-boat-define-availability",
    templateUrl: "./boat-define-availability.component.html",
    styleUrls: ["./boat-define-availability.component.css"],
})
export class BoatDefineAvailabilityComponent implements OnInit {
    hoveredDate: NgbDate | null = null;
    now = new Date();
    //minDate sluzi kao referenca danasnjeg dana, disabluj selektovanje svih dana pre danasnjeg
    minDate: NgbDateStruct = {
        year: this.now.getFullYear(),
        month: this.now.getMonth() + 1,
        day: this.now.getDate(),
    };

    availability: [];
    availability_dates: any = [];

    fromDate: NgbDate | null = null;
    toDate: NgbDate | null = null;
    price: number;

    constructor(
        public dialogRef: MatDialogRef<BoatDefineAvailabilityComponent>,
        private boatService: BoatService,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {}

    //==================== Metoda za oznacavenje zauzetih termina ====================
    markDate(date: NgbDate) {
        for (let i = 0; i <= this.availability_dates.length; i++) {
            if (date.equals(this.availability_dates[i])) {
                return true;
            }
        }
        return false;
    }

    hasAction(date: NgbDate) {
        for (let i = 0; i <= this.availability.length; i++) {
            if (date.equals(this.availability_dates[i])) {
                return true;
            }
        }
        return false;
    }

    //==================== Angular bootstrap datepicker default methods ====================
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

    //====================================================================================

    ngOnInit() {
        this.boatService.getBoatAvailability(this.data.boatId).subscribe(
            (response) => {
                this.availability = response;
                //pravim pomocnu listu koja ce sadrzati samo datume
                this.availability.forEach((element: any) => {
                    let date = new NgbDate(
                        element.date[0],
                        element.date[1],
                        element.date[2]
                    );
                    this.availability_dates.push(date);
                });
            },
            (error) => {
                console.log(error);
            }
        );
    }

    saveButtonValidation() {
        return !this.fromDate || !this.toDate || !this.price;
    }

    closeButton() {
        this.dialogRef.close();
    }

    saveChanges() {
        const hasAction = false;
        this.boatService
            .defineBoatAvailability(
                this.data.boatId,
                this.transformDate(this.fromDate),
                this.transformDate(this.toDate),
                this.price,
                hasAction
            )
            .subscribe(
                (response) => {
                    this.dialogRef.close();
                },
                (error) => {
                    console.log(error);
                }
            );
    }

    //NgbDate je u json formatu, pretvaram ga u listu
    transformDate(date: NgbDate) {
        let newDate = [];
        newDate.push(date.year);
        newDate.push(date.month);
        newDate.push(date.day);
        return newDate;
    }
}
