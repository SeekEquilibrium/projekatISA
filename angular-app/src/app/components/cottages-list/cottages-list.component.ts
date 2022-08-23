import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-cottages-list",
    templateUrl: "./cottages-list.component.html",
    styleUrls: ["./cottages-list.component.css"],
})
export class CottagesListComponent implements OnInit {
    cottages: [];

    constructor(
        private cottageService: CottageService,
        private router: Router
    ) {}

    ngOnInit() {
        this.cottageService.getOwnerCottages().subscribe((response) => {
            this.cottages = response;
        });
    }

    goToRegisterCottage() {
        this.router.navigate(["register/cottage"]);
    }
}
