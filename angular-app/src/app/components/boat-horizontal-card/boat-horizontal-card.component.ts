import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { BoatService } from "src/app/service/boat.service";

@Component({
    selector: "app-boat-horizontal-card",
    templateUrl: "./boat-horizontal-card.component.html",
    styleUrls: ["./boat-horizontal-card.component.css"],
})
export class BoatHorizontalCardComponent implements OnInit {
    @Input() boat;
    constructor(private router: Router, private boatService: BoatService) {}

    ngOnInit() {}

    editClick() {
        this.router.navigate(["/boat/" + this.boat.name + "/edit"]);
    }

    statsClick() {
        this.router.navigate(["reservations/boat/" + this.boat.id]);
    }

    navigateToBoat() {
        this.router.navigate(["/boat/" + this.boat.name]);
    }

    deleteClick(event) {
        event.stopPropagation();
        this.boatService.delete(this.boat.id).subscribe(
            (res) => {
                location.reload();
            },
            (error) => {
                alert(
                    "You can't delete boat which has reservations in the future."
                );
            }
        );
    }
}
