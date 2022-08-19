import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector: "app-cottage-horizontal-card",
    templateUrl: "./cottage-horizontal-card.component.html",
    styleUrls: ["./cottage-horizontal-card.component.css"],
})
export class CottageHorizontalCardComponent implements OnInit {
    @Input() cottage;
    constructor(private router: Router) {}

    ngOnInit() {
        console.log(this.cottage);
    }

    editClick() {
        this.router.navigate(["/cottage/" + this.cottage.name + "/edit"]);
    }

    navigateToCottage() {
        this.router.navigate(["/cottage/" + this.cottage.name]);
    }
}
