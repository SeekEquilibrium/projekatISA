import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { UserService } from "src/app/service";

@Component({
    selector: "app-delete-account-dialog",
    templateUrl: "./delete-account-dialog.component.html",
    styleUrls: ["./delete-account-dialog.component.css"],
})
export class DeleteAccountDialogComponent implements OnInit {
    form: FormGroup;

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private formBuilder: FormBuilder,
        public dialogRef: MatDialogRef<DeleteAccountDialogComponent>,
        private userService: UserService
    ) {}

    cancleClick() {
        this.dialogRef.close();
    }

    submitClick() {
        this.userService
            .deleteRequest(this.form.value.reason)
            .subscribe((response) => {
                this, this.dialogRef.close();
                alert("You have successfully submited request for deletion");
            });
    }

    ngOnInit() {
        this.form = this.formBuilder.group({
            reason: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(3),
                    Validators.maxLength(300),
                ]),
            ],
        });
    }
}
