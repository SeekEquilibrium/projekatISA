import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { NgbDate, NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";
import { element } from "protractor";
import { BoatService } from "src/app/service/boat.service";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-create-reservation-owner",
    templateUrl: "./create-reservation-owner.component.html",
    styleUrls: ["./create-reservation-owner.component.css"],
})
export class CreateReservationOwnerComponent implements OnInit {
    hoveredDate: NgbDate | null = null;
    now = new Date();
    minDate: NgbDateStruct = {
        year: this.now.getFullYear(),
        month: this.now.getMonth() + 1,
        day: this.now.getDate(),
    };
    isDisabled;
    user_fullname = "";
    availability: [];
    availability_dates: any = [];
    fromDate: NgbDate | null = null;
    toDate: NgbDate | null = null;

    constructor(
        public dialogRef: MatDialogRef<CreateReservationOwnerComponent>,
        private cottageService: CottageService,
        private boatService: BoatService,
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

    disableDate(date: NgbDate) {
        for (let i = 0; i <= this.availability_dates.length; i++) {
            if (date.equals(this.availability_dates[i])) {
                return false;
            }
        }
        return true;
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

    closeButton() {
        this.dialogRef.close();
    }

    submitValidation() {
        if (!this.fromDate || !this.toDate) {
            return true;
        }
        return false;
    }

    submit() {
        if (this.data.type === "cottage") {
            const reservation = {
                cottageId: this.data.element.cottageId,
                clientId: this.data.element.user.id,
                startDate: this.transformDate(this.fromDate),
                endDate: this.transformDate(this.toDate),
            };
            console.log(reservation);
            this.cottageService
                .createReservationForClient(reservation)
                .subscribe(
                    (response) => {
                        this.dialogRef.close();
                        location.reload();
                    },
                    (error) => {
                        console.log(error);
                    }
                );
        } else if (this.data.type === "boat") {
            const reservation = {
                boatId: this.data.element.cottageId,
                clientId: this.data.element.user.id,
                startDate: this.transformDate(this.fromDate),
                endDate: this.transformDate(this.toDate),
            };
            console.log(reservation);
            this.boatService.createReservationForClient(reservation).subscribe(
                (response) => {
                    this.dialogRef.close();
                    location.reload();
                },
                (error) => {
                    console.log(error);
                }
            );
        }
    }

    //NgbDate je u json formatu, pretvaram ga u listu
    transformDate(date: NgbDate) {
        let newDate = [];
        newDate.push(date.year);
        newDate.push(date.month);
        newDate.push(date.day);
        return newDate;
    }
    ngOnInit() {
        console.log(this.data);
        this.user_fullname =
            this.data.element.user.name + " " + this.data.element.user.surname;

        if (this.data.type === "cottage") {
            this.cottageService
                .getAvailability(this.data.element.cottageId)
                .subscribe(
                    (response) => {
                        console.log(response);
                        this.availability = response;
                        //pravim pomocnu listu koja ce sadrzati samo datume
                        this.availability.forEach((element: any) => {
                            let date = new NgbDate(
                                element.date[0],
                                element.date[1],
                                element.date[2]
                            );
                            this.availability_dates.push(date);
                            this.disableUnavailableDates();
                        });
                    },
                    (error) => {
                        console.log(error);
                    }
                );
        } else if (this.data.type === "boat") {
            this.boatService
                .getAvailability(this.data.element.cottageId)
                .subscribe(
                    (response) => {
                        console.log(response);
                        this.availability = response;
                        //pravim pomocnu listu koja ce sadrzati samo datume
                        this.availability.forEach((element: any) => {
                            let date = new NgbDate(
                                element.date[0],
                                element.date[1],
                                element.date[2]
                            );
                            this.availability_dates.push(date);
                            this.disableUnavailableDates();
                        });
                    },
                    (error) => {
                        console.log(error);
                    }
                );
        }
    }

    private disableUnavailableDates() {
        this.isDisabled = (date: NgbDate, current: { month: number }) => {
            for (let i = 0; i <= this.availability_dates.length; i++) {
                if (date.equals(this.availability_dates[i])) {
                    return false;
                }
            }
            return true;
        };
    }
}
