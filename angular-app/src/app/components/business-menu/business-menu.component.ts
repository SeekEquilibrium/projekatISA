import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { UserService } from "src/app/service";

@Component({
    selector: "app-business-menu",
    templateUrl: "./business-menu.component.html",
    styleUrls: ["./business-menu.component.css"],
})
export class BusinessMenuComponent implements OnInit {
    currentUser: Observable<any>;
    constructor(private userService: UserService, private router: Router) {
        this.userService.currentUserSubject.subscribe((value) => {
            this.currentUser = value;
        });
    }
    ngOnInit() {}

    goToCottages() {
        this.router.navigate(["my-cottages"]);
    }
}
