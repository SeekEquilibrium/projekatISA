import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-cottage-horizontal-card",
    templateUrl: "./cottage-horizontal-card.component.html",
    styleUrls: ["./cottage-horizontal-card.component.css"],
})
export class CottageHorizontalCardComponent implements OnInit {
    @Input() cottage;
    constructor(
        private router: Router,
        private cottageService: CottageService
    ) {}

    ngOnInit() {
        console.log(this.cottage);
    }

    editClick() {
        this.router.navigate(["/cottage/" + this.cottage.name + "/edit"]);
    }

    statsClick() {
        this.router.navigate(["reservations/cottage/" + this.cottage.id]);
    }

    navigateToCottage() {
        this.router.navigate(["/cottage/" + this.cottage.name]);
    }

    deleteClick(event) {
        event.stopPropagation();
        this.cottageService.delete(this.cottage.id).subscribe(
            (res) => {
                location.reload();
            },
            (error) => {
                alert(
                    "You can't delete cottage which has reservations in the future."
                );
            }
        );
    }
}
