import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector: "app-boat-horizontal-card",
    templateUrl: "./boat-horizontal-card.component.html",
    styleUrls: ["./boat-horizontal-card.component.css"],
})
export class BoatHorizontalCardComponent implements OnInit {
    @Input() boat;
    constructor(private router: Router) {}

    ngOnInit() {}

    editClick() {
        this.router.navigate(["/boat/" + this.boat.name + "/edit"]);
    }

    statsClick() {
        this.router.navigate(["reservations/" + this.boat.id]);
    }

    navigateToBoat() {
        this.router.navigate(["/boat/" + this.boat.name]);
    }
}
