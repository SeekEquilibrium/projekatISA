import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../../service/auth.service";
import { UserService } from "../../service/user.service";

@Component({
    selector: "app-user-menu",
    templateUrl: "./user-menu.component.html",
    styleUrls: ["./user-menu.component.css"],
})
export class UserMenuComponent implements OnInit {
    user: any;

    constructor(
        private authService: AuthService,
        private userService: UserService,
        private router: Router
    ) {}

    ngOnInit() {
        this.user = this.userService.currentUser;
    }

    logout() {
        this.authService.logout();
    }

    goToEdit() {
        this.router.navigate(["edit-profile"]);
    }
}
