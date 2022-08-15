import { Component, OnInit } from "@angular/core";
import { UserService } from "../../service/user.service";

@Component({
    selector: "app-header",
    templateUrl: "./header.component.html",
    styleUrls: ["./header.component.css"],
})
export class HeaderComponent implements OnInit {
    constructor(private userService: UserService) {}

    ngOnInit() {
        this.userService.getMyInfo().subscribe();
    }

    hasSignedIn() {
        return !!this.userService.currentUser;
    }

    userName() {
        const user = this.userService.currentUser;
        return user.name + " " + user.surname;
    }
}
