import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material";
import { ActivatedRoute } from "@angular/router";
import { NgbDate, NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";
import { BoatService } from "src/app/service/boat.service";
import { BasicUserInfoComponent } from "../basic-user-info/basic-user-info.component";
import { CreateReservationOwnerComponent } from "../create-reservation-owner/create-reservation-owner.component";
import { OwnerReviewComponent } from "../owner-review/owner-review.component";

@Component({
    selector: "app-boats-reservations",
    templateUrl: "./boats-reservations.component.html",
    styleUrls: ["./boats-reservations.component.css"],
})
export class BoatsReservationsComponent implements OnInit {
    stats;
    now = new Date();
    today: NgbDateStruct = {
        year: this.now.getFullYear(),
        month: this.now.getMonth() + 1,
        day: this.now.getDate(),
    };
    reservations: any[];
    dataSource: any;
    displayedColumns: String[] = [
        "Boat",
        "Date Start",
        "Date End",
        "Customer",
        "Status",
        "Review",
        "New Reservation",
    ];
    changeColor = false;
    constructor(
        private boatService: BoatService,
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
            element.endDate[0],
            element.endDate[1],
            element.endDate[2]
        );
        if (date.before(this.today)) {
            return true;
        }
        return false;
    }

    reviewClick(element) {
        console.log(element);
        this.dialog.open(OwnerReviewComponent, {
            data: { element: element, type: "boat" },
        });
    }

    newReservationClick(element) {
        this.dialog.open(CreateReservationOwnerComponent, {
            data: { element: element, type: "boat" },
        });
    }

    ngOnInit() {
        this.boatService
            .getBoatReservations(this.route.snapshot.paramMap.get("boatId"))
            .subscribe((response) => {
                console.log(response);
                this.reservations = response;
                this.dataSource = this.reservations;
            });

        this.boatService
            .getBoatStats(this.route.snapshot.paramMap.get("boatId"))
            .subscribe((response) => {
                console.log(response);
                this.stats = response;
            });
    }
}
