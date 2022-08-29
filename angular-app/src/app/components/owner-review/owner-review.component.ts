import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-owner-review",
    templateUrl: "./owner-review.component.html",
    styleUrls: ["./owner-review.component.css"],
})
export class OwnerReviewComponent implements OnInit {
    form: FormGroup;
    user_fullname = "";
    constructor(
        public dialogRef: MatDialogRef<OwnerReviewComponent>,
        private formBuilder: FormBuilder,
        private cottageService: CottageService,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {}

    closeButton() {
        this.dialogRef.close();
    }

    submit() {
        const report = {
            cottageId: this.data.element.cottageId,
            clientId: this.data.element.user.id,
            description: this.form.value.description,
            reportClient: this.form.value.report,
            didNotShowUp: this.form.value.didNotShowUp,
        };
        this.cottageService.submitReport(report).subscribe(
            (response) => {
                this.dialogRef.close();
            },
            (error) => {
                console.log(error);
            }
        );
    }

    ngOnInit() {
        console.log(this.data.element);
        this.user_fullname =
            this.data.element.user.name + " " + this.data.element.user.surname;
        this.form = this.formBuilder.group({
            description: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(5),
                ]),
            ],
            report: [false],
            didNotShowUp: [false],
        });
    }
}
