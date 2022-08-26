import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";

@Component({
    selector: "app-basic-user-info",
    templateUrl: "./basic-user-info.component.html",
    styleUrls: ["./basic-user-info.component.css"],
})
export class BasicUserInfoComponent implements OnInit {
    user: any;
    constructor(
        public dialogRef: MatDialogRef<BasicUserInfoComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {
        this.user = data.user;
    }

    ngOnInit() {}
}
