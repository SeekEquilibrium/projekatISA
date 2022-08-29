import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogRef } from "@angular/material";
import { ActivatedRoute } from "@angular/router";
import { NgbDate, NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";
import { CottageService } from "src/app/service/cottage.service";
import { BasicUserInfoComponent } from "../basic-user-info/basic-user-info.component";
import { OwnerReviewComponent } from "../owner-review/owner-review.component";

@Component({
    selector: "app-cottages-reservations",
    templateUrl: "./cottages-reservations.component.html",
    styleUrls: ["./cottages-reservations.component.css"],
})
export class CottagesReservationsComponent implements OnInit {
    now = new Date();
    today: NgbDateStruct = {
        year: this.now.getFullYear(),
        month: this.now.getMonth() + 1,
        day: this.now.getDate(),
    };
    reservations: any[];
    dataSource: any;
    displayedColumns: String[] = [
        "Cottage",
        "Date Start",
        "Date End",
        "Customer",
        "Status",
        "Review",
    ];
    changeColor = false;
    constructor(
        private cottageService: CottageService,
        private route: ActivatedRoute,
        public dialog: MatDialog
    ) {}

    changeColorFunc(id) {
        if (id == 4) {
            this.changeColor = true;
        } else {
            this.changeColor = false;
        }
    }

    openUserInfo(user) {
        console.log(user);
        const dialogRef = this.dialog.open(BasicUserInfoComponent, {
            width: "300px",
            data: { user: user },
        });
    }

    showReviewButton(element) {
        let date = new NgbDate(
            element.startDate[0],
            element.startDate[1],
            element.startDate[2]
        );
        if (date.before(this.today)) {
            return true;
        }
        return false;
    }

    reviewClick(element) {
        console.log(element);
        this.dialog.open(OwnerReviewComponent, {
            data: { element: element },
        });
    }

    ngOnInit() {
        this.cottageService
            .getCottageReservations(
                this.route.snapshot.paramMap.get("cottageId")
            )
            .subscribe((response) => {
                console.log(response);
                this.reservations = response;
                this.dataSource = this.reservations;
            });
    }
}
