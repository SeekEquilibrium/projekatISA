import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialog } from "@angular/material";
import { Router } from "@angular/router";
import { AuthService, UserService } from "src/app/service";
import { DeleteAccountDialogComponent } from "../delete-account-dialog/delete-account-dialog.component";

@Component({
    selector: "app-edit-profile",
    templateUrl: "./edit-profile.component.html",
    styleUrls: ["./edit-profile.component.css"],
})
export class EditProfileComponent implements OnInit {
    constructor(
        private userService: UserService,
        private formBuilder: FormBuilder,
        private authService: AuthService,
        private router: Router,
        public dialog: MatDialog
    ) {}
    user = null;
    form: FormGroup;
    ngOnInit() {
        this.userService.getMyInfo().subscribe((res) => {
            this.user = res;
            console.log(this.user);
        });
        this.form = this.formBuilder.group({
            username: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(3),
                    Validators.maxLength(32),
                ]),
            ],
            password: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(3),
                    Validators.maxLength(32),
                ]),
            ],
            newPassword: [
                "",
                Validators.compose([
                    Validators.minLength(3),
                    Validators.maxLength(32),
                ]),
            ],
            repeatNewPassword: [""],
            name: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(3),
                    Validators.maxLength(20),
                ]),
            ],
            surname: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(3),
                    Validators.maxLength(20),
                ]),
            ],
            email: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(3),
                ]),
            ],
            phoneNumber: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.pattern("[- +()0-9]{6,}"),
                ]),
            ],
        });
    }

    onChange() {
        if (!!this.form.controls.newPassword.value) {
            this.form.controls["repeatNewPassword"].setValidators([
                Validators.required,
                Validators.minLength(3),
                Validators.maxLength(32),
            ]);
        } else {
            console.log("UPAD");
            debugger;
            this.form.controls["repeatNewPassword"].clearValidators();
        }
        this.form.controls["repeatNewPassword"].updateValueAndValidity;
    }

    passwordsMatch() {
        if (
            this.form.controls.newPassword.value ==
            this.form.controls.repeatNewPassword.value
        )
            return true;
        return false;
    }

    onSubmit() {
        this.authService.editProfile(this.form.value).subscribe(
            (data) => {
                this.router.navigate(["/"]);
            },
            (error) => {
                console.log("Error");
                if (error.status == 401) {
                    alert("Wrong password, try again!");
                }
            }
        );
    }

    deleteAccountClick() {
        this.dialog.open(DeleteAccountDialogComponent, {
            data: { userId: this.user.id },
        });
    }
}
