import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { BoatService } from "src/app/service/boat.service";

@Component({
    selector: "app-boats-list",
    templateUrl: "./boats-list.component.html",
    styleUrls: ["./boats-list.component.css"],
})
export class BoatsListComponent implements OnInit {
    boats: [];
    constructor(private boatService: BoatService, private router: Router) {}

    ngOnInit() {
        this.boatService.getOwnerBoats().subscribe((response) => {
            this.boats = response;
        });
    }

    goToRegisterBoat() {
        this.router.navigate(["register/boat"]);
    }
}
