import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { NgbDate, NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-cottage-define-actions",
    templateUrl: "./cottage-define-actions.component.html",
    styleUrls: ["./cottage-define-actions.component.css"],
})
export class CottageDefineActionsComponent implements OnInit {
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
        public dialogRef: MatDialogRef<CottageDefineActionsComponent>,
        private cottageService: CottageService,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {}

    markDate(date: NgbDate) {
        for (let i = 0; i <= this.availability_dates.length; i++) {
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

    saveButtonValidation() {
        console.log(this.price);
        return !this.fromDate || !this.toDate || !this.price;
    }

    saveChanges() {
        const hasAction = true;
        this.cottageService
            .defineCottageAvailability(
                this.data.cottageId,
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

    closeButton() {
        this.dialogRef.close();
    }

    transformDate(date: NgbDate) {
        let newDate = [];
        newDate.push(date.year);
        newDate.push(date.month);
        newDate.push(date.day);
        return newDate;
    }
    ngOnInit() {
        this.cottageService.getCottageActions(this.data.cottageId).subscribe(
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
                console.log(response);
            },
            (error) => {
                console.log(error);
            }
        );
    }
}
