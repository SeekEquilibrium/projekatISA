import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/service";

@Component({
    selector: "app-business-menu",
    templateUrl: "./business-menu.component.html",
    styleUrls: ["./business-menu.component.css"],
})
export class BusinessMenuComponent implements OnInit {
    userRole: any;

    constructor(private userService: UserService) {}
    ngOnInit() {
        console.log(this.userService.currentUser.role);
    }
}
