import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";

const today = new Date();
const month = today.getMonth();
const year = today.getFullYear();

@Component({
    selector: "app-cottage-define-availability",
    templateUrl: "./cottage-define-availability.component.html",
    styleUrls: ["./cottage-define-availability.component.css"],
})
export class CottageDefineAvailabilityComponent implements OnInit {
    constructor() {}

    campaignOne = new FormGroup({
        start: new FormControl(new Date(year, month, 13)),
        end: new FormControl(new Date(year, month, 16)),
    });
    campaignTwo = new FormGroup({
        start: new FormControl(new Date(year, month, 15)),
        end: new FormControl(new Date(year, month, 19)),
    });

    ngOnInit() {}
}
