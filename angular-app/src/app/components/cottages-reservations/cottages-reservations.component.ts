import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material";
import { ActivatedRoute } from "@angular/router";
import { CottageService } from "src/app/service/cottage.service";
import { BasicUserInfoComponent } from "../basic-user-info/basic-user-info.component";

@Component({
    selector: "app-cottages-reservations",
    templateUrl: "./cottages-reservations.component.html",
    styleUrls: ["./cottages-reservations.component.css"],
})
export class CottagesReservationsComponent implements OnInit {
    reservations: any[];
    dataSource: any;
    displayedColumns: String[] = [
        "Id",
        "Date Start",
        "Date End",
        "Cottage",
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
