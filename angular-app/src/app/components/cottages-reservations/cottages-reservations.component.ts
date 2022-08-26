import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-cottages-reservations",
    templateUrl: "./cottages-reservations.component.html",
    styleUrls: ["./cottages-reservations.component.css"],
})
export class CottagesReservationsComponent implements OnInit {
    reservations: any[];
    dataSource: any;
    displayedColumns: String[] = ["Id", "Date", "Price", "Customer", "Action"];
    constructor(
        private cottageService: CottageService,
        private route: ActivatedRoute
    ) {}

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
