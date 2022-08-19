import { Component, OnInit } from "@angular/core";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-cottages-list",
    templateUrl: "./cottages-list.component.html",
    styleUrls: ["./cottages-list.component.css"],
})
export class CottagesListComponent implements OnInit {
    cottages: [];

    constructor(private cottageService: CottageService) {}

    ngOnInit() {
        this.cottageService.getOwnerCottages().subscribe((response) => {
            this.cottages = response;
        });
    }
}
