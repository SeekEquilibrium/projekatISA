import { Component, OnDestroy, OnInit } from "@angular/core";
import {
    FormBuilder,
    FormGroup,
    ValidatorFn,
    Validators,
} from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthService, UserService } from "../../service";
import { Subject } from "rxjs/Subject";
import { takeUntil } from "rxjs/operators";
import {
    MatSnackBar,
    MatSnackBarHorizontalPosition,
    MatSnackBarVerticalPosition,
} from "@angular/material/snack-bar";
interface DisplayMessage {
    msgType: string;
    msgBody: string;
}

@Component({
    selector: "app-sign-up",
    templateUrl: "./sign-up.component.html",
    styleUrls: ["./sign-up.component.css"],
})
export class SignUpComponent implements OnInit {
    title = "Sign up";
    form: FormGroup;
    types = ["Client", "Cottage Owner", "Boat Owner", "Fishing Instructor"];
    typess: { [key: string]: string } = {
        CLIENT: "Client",
        COTTAGE_OWNER: "Cottage Owner",
        BOAT_OWNER: "Boat Owner",
        FISHING_INSTRUCTOR: "Fishing Instructor",
    };
    selectedType = "Client";

    /**
     * Boolean used in telling the UI
     * that the form has been submitted
     * and is awaiting a response
     */
    submitted = false;
    /**
     * Notification message from received
     * form request or router
     */
    notification: DisplayMessage;

    returnUrl: string;
    private ngUnsubscribe: Subject<void> = new Subject<void>();

    constructor(
        private userService: UserService,
        private authService: AuthService,
        private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private _snackBar: MatSnackBar
    ) {}

    ngOnInit() {
        this.route.params
            .pipe(takeUntil(this.ngUnsubscribe))
            .subscribe((params: DisplayMessage) => {
                this.notification = params;
            });
        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams["returnUrl"] || "/";
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
            repeatPassword: [
                "",
                Validators.compose([
                    Validators.required,
                    Validators.minLength(3),
                    Validators.maxLength(32),
                ]),
            ],
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
            role: ["CLIENT"],
            reasoning: [""],
        });
    }

    onChange() {
        if (this.form.controls.role.value != "CLIENT") {
            this.form.controls["reasoning"].setValidators([
                Validators.required,
                Validators.minLength(10),
            ]);
            this.form.controls["reasoning"].updateValueAndValidity();
        } else {
            this.form.controls.reasoning.clearValidators();
            this.form.controls["reasoning"].updateValueAndValidity();
        }
    }

    ngOnDestroy() {
        this.ngUnsubscribe.next();
        this.ngUnsubscribe.complete();
    }

    passwordsMatch() {
        if (
            this.form.controls.password.value ==
            this.form.controls.repeatPassword.value
        )
            return true;
        return false;
    }

    onSubmit() {
        /**
         * Innocent until proven guilty
         */
        // if(this.form.value.password != this.form.value.repeatPassword){
        //   return;
        // }
        this.notification = undefined;
        this.submitted = true;

        this.authService.signup(this.form.value).subscribe(
            (data) => {
                console.log(data);
                // this.authService.login(this.form.value).subscribe(() => {
                // this.userService.getMyInfo().subscribe();
                this.router.navigate([this.returnUrl]);
            },
            (error) => {
                this.submitted = false;
                console.log("Sign up error");
                if (error.status == 409) {
                    this._snackBar.open("Username is already taken.", "Close", {
                        duration: 5000,
                        panelClass: "notif-success",
                    });
                }
            }
        );
    }
}
